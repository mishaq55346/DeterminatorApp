package ru.mikhail.determinatorapp.util;

import java.sql.SQLException;

import ru.mikhail.determinatorapp.common.Book;
import ru.mikhail.determinatorapp.orm.BookDAO;
import ru.mikhail.determinatorapp.orm.BookDTO;
import ru.mikhail.determinatorapp.orm.HelperFactory;

public class LocalLibraryLoader {
    private BookDAO bookDAO;
    public LocalLibraryLoader() {
        try {
            bookDAO = HelperFactory.getHelper().getBookDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getBooksList(){
        try {
            bookDAO.getBooksList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Book getBook(int id){
        try {
            return bookDAO.getBook(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void addBook(Book book) {
        try {
            bookDAO.create(new BookDTO(book));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
