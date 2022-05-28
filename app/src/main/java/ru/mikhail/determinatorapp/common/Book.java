package ru.mikhail.determinatorapp.common;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    int id;
    String title;
    int year;
    String[] authors;
    String[] groups;
    String content;

    public Book() {}

    public Book(String title, int year, String[] authors, String[] groups, String content) {
        this.id = -1;
        this.title = title;
        this.year = year;
        this.authors = authors;
        this.groups = groups;
        this.content = content;
    }

    public Book(int id, String title, int year, String[] authors, String[] groups, String content) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.authors = authors;
        this.groups = groups;
        this.content = content;
    }

    public Book(Parcel parcel) {
        id = parcel.readInt();
        title = parcel.readString();
        year = parcel.readInt();
        authors = parcel.createStringArray();
        groups = parcel.createStringArray();
        content = parcel.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeInt(year);
        dest.writeStringArray(authors);
        dest.writeStringArray(groups);
        dest.writeString(content);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String[] getGroups() {
        return groups;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
