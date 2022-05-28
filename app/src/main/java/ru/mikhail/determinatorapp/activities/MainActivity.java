package ru.mikhail.determinatorapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import ru.mikhail.determinatorapp.Determinator;
import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.common.Book;
import ru.mikhail.determinatorapp.util.LocalLibraryLoader;

public class MainActivity extends AppCompatActivity {
    LocalLibraryLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader = new LocalLibraryLoader();
    }

    public void click1(View view) {
        Intent intent = new Intent(MainActivity.this, GlobalLibraryActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void click2(View view) {
        Intent intent = new Intent(MainActivity.this, LocalLibraryActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void click3(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void click4(View view) {}

    public void click5(View view) throws JSONException {
        //reload books
        for (Book b : loader.getBooksList()) {
            loader.deleteBook(b.getId());
        }

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject auth = new JSONObject();
        auth.put("username", Determinator.globalUsername);
        auth.put("password", Determinator.globalPassword);

        JSONObject arguments = new JSONObject();
        arguments.put("authentication", auth);
        arguments.put("book_id", 1);

        String url = Determinator.URL + "/book/get";
        String emulatorUrl = "http://10.0.2.2:3000/book/get";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url,
                arguments,
                response -> {
                    Log.i("DET", "reload books: status 200");
                    Log.i(Determinator.TAG, "reload books: " + response.toString());
                    Book b = null;
                    try {
                        b = new Book();
                        b.setId(response.getInt("id"));
                        b.setTitle(response.getString("title"));
                        b.setYear(response.getInt("year"));
                        b.setAuthors(response.getString("author").split(","));
                        b.setGroups(response.getString("roles").split(","));
                        b.setContent(response.getString("content"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    loader.addBook(b);
                },
                error -> {
                    Log.e("DET", "reload books: " + error.getMessage());
                });
        queue.add(request);

    }
}