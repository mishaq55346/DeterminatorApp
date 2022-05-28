package ru.mikhail.determinatorapp.activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.adapters.LocalLibraryAdapter;
import ru.mikhail.determinatorapp.common.Book;
import ru.mikhail.determinatorapp.util.LocalLibraryLoader;

public class LocalLibraryActivity extends AppCompatActivity {
    private List<Book> booksList = new ArrayList<>();
    private LocalLibraryAdapter adapter;
    private LocalLibraryLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_library);

        loader = new LocalLibraryLoader();
        booksList = loader.getBooksList();

        ListView bookList = findViewById(R.id.local_library_list);
        adapter = new LocalLibraryAdapter(this, R.layout.local_book, booksList);

        bookList.setAdapter(adapter);
    }
}