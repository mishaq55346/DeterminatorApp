package ru.mikhail.determinatorapp.activities.MainActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    public void goToGlobalLibrary(View view){
        Intent intent = new Intent(LocalLibraryActivity.this, GlobalLibraryActivity.class);
        LocalLibraryActivity.this.startActivity(intent);
    }
    public void goToProfile(View view){
        Intent intent = new Intent(LocalLibraryActivity.this, ProfileActivity.class);
        LocalLibraryActivity.this.startActivity(intent);
    }
}