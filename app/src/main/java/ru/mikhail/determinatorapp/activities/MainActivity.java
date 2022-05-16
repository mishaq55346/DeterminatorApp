package ru.mikhail.determinatorapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

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
        //add shit
        Book book = new Book(String.valueOf(new Random().nextInt()), 228,
                new String[]{"М.В. Чертила", "Е.С. Чертила"},
                new String[]{"MSU", "MPGU"}, "");
        loader.addBook(book);
    }

    public void click4(View view) {
        //show shit
    }

    public void click5(View view) {

    }
}