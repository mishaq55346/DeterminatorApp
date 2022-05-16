package ru.mikhail.determinatorapp.orm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import ru.mikhail.determinatorapp.common.Book;

@DatabaseTable(tableName = "books")
public class BookDTO {
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
    public Book toBook(){
        return new Book(name, year, authors, groups, content);
    }

}
