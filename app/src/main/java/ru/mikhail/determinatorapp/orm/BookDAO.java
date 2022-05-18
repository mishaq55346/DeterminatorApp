package ru.mikhail.determinatorapp.orm;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import ru.mikhail.determinatorapp.common.Book;
import ru.mikhail.determinatorapp.common.BookDTO;

public class BookDAO extends BaseDaoImpl<BookDTO, Integer> {

    protected BookDAO(ConnectionSource connectionSource,
                      Class<BookDTO> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Book> getBooksList() throws SQLException {
        List<BookDTO> list = query(queryBuilder()
                .selectColumns("id", "name", "year", "authors", "groups", "content")
                .prepare());
        return list
                .stream()
                .map(BookDTO::toBook)
                .collect(Collectors.toList());
    }

    public Book getBook(int id) throws SQLException {
        BookDTO bookDTO = queryForId(id);
        return bookDTO.toBook();
    }

}