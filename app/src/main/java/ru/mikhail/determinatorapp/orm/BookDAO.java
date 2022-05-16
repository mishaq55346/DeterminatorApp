package ru.mikhail.determinatorapp.orm;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.mikhail.determinatorapp.common.Book;

public class BookDAO extends BaseDaoImpl<BookDTO, Integer> {

    protected BookDAO(ConnectionSource connectionSource,
                      Class<BookDTO> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Book> getBooksList() throws SQLException {
        List<BookDTO> list = query(queryBuilder()
                .selectColumns("name", "year", "authors", "groups")
                .prepare());
        return list
                .stream()
                .map(BookDTO::toBook)
                .collect(Collectors.toList());
    }
}