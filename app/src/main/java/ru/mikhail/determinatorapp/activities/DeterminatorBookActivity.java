package ru.mikhail.determinatorapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.common.Book;

public class DeterminatorBookActivity extends AppCompatActivity {
    TextView name;
    TextView year;
    TextView authors;
    TextView groups;
    Button startButton;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_determinator_book);

        name = findViewById(R.id.determinatorName);
        year = findViewById(R.id.determinatorYear);
        authors = findViewById(R.id.determinatorAuthor);
        groups = findViewById(R.id.determinatorRoles);
        startButton = findViewById(R.id.begin_determination_button);

        Intent intent = getIntent();
        book = intent.getParcelableExtra("book");


        authors.setText(String.join("\n", book.getAuthors()));
        groups.setText(String.join("\n", book.getGroups()));
        name.setText(book.getTitle());
        year.setText(String.valueOf(book.getYear()));
    }

    public void beginDetermination(View view){
        Intent intent = new Intent(DeterminatorBookActivity.this, DeterminationActivity.class);
        intent.putExtra("book", book);
        DeterminatorBookActivity.this.startActivity(intent);
    }
}