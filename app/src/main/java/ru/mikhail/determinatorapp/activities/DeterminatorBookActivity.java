package ru.mikhail.determinatorapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.common.Book;

public class DeterminatorBookActivity extends AppCompatActivity {
    TextView name;
    TextView year;
    TextView authors;
    TextView groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_determinator_book);

        name = findViewById(R.id.determinatorName);
        year = findViewById(R.id.determinatorYear);
        authors = findViewById(R.id.determinatorAuthor);
        groups = findViewById(R.id.determinatorRoles);

        Intent intent = getIntent();
        Book book = intent.getParcelableExtra("book");


        authors.setText(String.join("\n", book.getAuthors()));
        groups.setText(String.join("\n", book.getGroups()));
        name.setText(book.getName());
        year.setText("" + book.getYear());
    }
}