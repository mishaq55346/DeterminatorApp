package ru.mikhail.determinatorapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ru.mikhail.determinatorapp.Determinator;
import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.adapters.GlobalLibraryAdapter;
import ru.mikhail.determinatorapp.common.Book;
import ru.mikhail.determinatorapp.util.LocalLibraryLoader;
import ru.mikhail.determinatorapp.util.RequestHandler;

public class GlobalLibraryActivity extends AppCompatActivity {
    private List<Book> booksList = new ArrayList<>();
    private GlobalLibraryAdapter adapter;
    private LocalLibraryLoader loader;
    private ListView bookListView;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Determinator.globalUsername == null || Determinator.globalUsername.isEmpty()) {
            Intent intent = new Intent(GlobalLibraryActivity.this, GlobalLibraryActivity.class);
            GlobalLibraryActivity.this.startActivity(intent);
        }
        setContentView(R.layout.activity_global_library);
        loader = new LocalLibraryLoader();
        bookListView = findViewById(R.id.library_list);
        searchEditText = (EditText) findViewById(R.id.global_library_search_text);
        adapter = new GlobalLibraryAdapter(this, R.layout.global_book, booksList);
        bookListView.setAdapter(adapter);
        update();
    }

    public void update(View view) {
        update();
    }

    public void update() {
        String searchString = searchEditText.getText().toString().trim();
        booksList.clear();
        searchBooks(searchString);
    }

    public void searchBooks(String searchString) {
        JSONObject arguments = RequestHandler.getRequestJson("search_string", searchString);
        RequestHandler.sendRequest(RequestHandler.RequestType.SEARCH_BOOK, this, arguments,
                (JSONObject response) -> {
                    JSONArray books;
                    try {
                        books = response.getJSONArray("books");

                        for (int i = 0; i < books.length(); i++) {
                            JSONObject book = books.getJSONObject(i);
                            int id = book.getInt("id");
                            String author = book.getString("author");
                            String title = book.getString("title");
                            int year = book.getInt("year");
                            String roles = book.getString("roles");
                            booksList.add(new Book(id, title, year, author.split(","), roles.split(","), ""));
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Log.e(Determinator.TAG, "Error parsing response: ", e);
                    }
                },
                error -> {
                    Log.e(Determinator.TAG, "fillLibraryView: " + error.getMessage());
                    if (error.networkResponse != null) {
                        Log.e(Determinator.TAG, "fillLibraryView: " + new String(error.networkResponse.data,
                                StandardCharsets.UTF_8));
                        Toast.makeText(this, new String(error.networkResponse.data,
                                StandardCharsets.UTF_8), Toast.LENGTH_SHORT).show();
                    }
                    adapter.notifyDataSetChanged();
                });
    }
}
