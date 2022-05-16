package ru.mikhail.determinatorapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.common.Book;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View view) {
        Intent intent = new Intent(MainActivity.this, DeterminatorBookActivity.class);
        Book book = new Book("Краткий определитель хуйни", 228,
                new String[]{"М.В. Чертила", "Е.С. Чертила"},
                new String[]{"MSU", "MPGU"}, "");
        intent.putExtra("book", book);
        MainActivity.this.startActivity(intent);
    }

    public void click2(View view) {
        Intent intent = new Intent(MainActivity.this, LocalLibraryActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void click3(View view) {

    }

    public void click4(View view) {

    }

    public void click5(View view) {

    }
}