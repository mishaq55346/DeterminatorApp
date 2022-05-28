package ru.mikhail.determinatorapp.common;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "books")
public class BookDTO {
    @DatabaseField(generatedId = true, columnName = "id")
    int id;
    @DatabaseField(columnName = "name")
    String name;
    @DatabaseField(columnName = "year")
    int year;
    @DatabaseField(columnName = "authors")
    String authors;
    @DatabaseField(columnName = "groups")
    String groups;
    @DatabaseField(columnName = "content")
    String content;

    public BookDTO() {}

    public BookDTO(Book book) {
        this(book.getId(), book.getTitle(), book.getYear(), book.getAuthors(), book.getGroups(), book.getContent());
    }

    public BookDTO(int id, String name, int year, String[] authors, String[] groups, String content) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.authors = String.join(",", authors);
        this.groups = String.join(",", groups);
        this.content = content;
    }

    public Book toBook(){
        return new Book(id, name, year, authors.split(","), groups.split(","), content);
    }

}
