package ru.mikhail.determinatorapp.orm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import ru.mikhail.determinatorapp.common.Book;

@DatabaseTable(tableName = "books")
public class BookDTO {
    @DatabaseField(generatedId = true, columnName = "id")
    int id;
    @DatabaseField(columnName = "name")
    String name;
    @DatabaseField(columnName = "year")
    int year;
    @DatabaseField(columnName = "authors")
    String[] authors;
    @DatabaseField(columnName = "groups")
    String[] groups;
    @DatabaseField(columnName = "content")
    String content;

    public BookDTO(Book book) {
        new BookDTO(book.getId(), book.getName(), book.getYear(), book.getAuthors(), book.getGroups(), book.getContent());
    }

    public BookDTO(int id, String name, int year, String[] authors, String[] groups, String content) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.authors = authors;
        this.groups = groups;
        this.content = content;
    }

    public Book toBook(){
        return new Book(id, name, year, authors, groups, content);
    }

}
