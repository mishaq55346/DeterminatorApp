package ru.mikhail.determinatorapp.activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.adapters.BooksAdapter;
import ru.mikhail.determinatorapp.common.Book;

public class LocalLibraryActivity extends AppCompatActivity {
    private ArrayList<Book> books = new ArrayList<>();
    private BooksAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_library);

        books.add(new Book("Краткий определитель хуйни 1", 228,
                new String[]{"М.В. Чертила1", "Е.С. Чертила"},
                new String[]{"MSU", "MPGU"}, ""));
        books.add(new Book("Краткий определитель хуйни 2", 228,
                new String[]{"М.В. Чертила2", "Е.С. Чертила"},
                new String[]{"MSU", "MPGU"}, ""));
        books.add(new Book("Краткий определитель хуйни 3", 228,
                new String[]{"М.В. Чертила3", "Е.С. Чертила"},
                new String[]{"MSU", "MPGU"}, ""));
        books.add(new Book("Краткий определитель хуйни 4", 228,
                new String[]{"М.В. Чертила4", "Е.С. Чертила"},
                new String[]{"MSU", "MPGU"}, ""));


        ListView bookList = findViewById(R.id.local_library_list);
        adapter = new BooksAdapter(this, R.layout.local_book, books);
        bookList.setAdapter(adapter);
    }
}