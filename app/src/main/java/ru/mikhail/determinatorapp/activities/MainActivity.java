package ru.mikhail.determinatorapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.common.Book;
import ru.mikhail.determinatorapp.common.UserDTO;
import ru.mikhail.determinatorapp.util.LocalLibraryLoader;

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
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        MainActivity.this.startActivity(intent);



//        //add shit
//        Book book = new Book(String.valueOf(new Random().nextInt()), 228,
//                new String[]{"М.В. Чертила", "Е.С. Чертила"},
//                new String[]{"MSU", "MPGU"}, "");
//        loader.addBook(book);
    }

    public void click4(View view) {
//        //show shit
//        String textToShow = "nothing to show";
//        List<Book> booksList = loader.getBooksList();
//        if (booksList.size() != 0){
//            textToShow = "";
//            textToShow += "there is " + booksList.size() + " entries: [";
//            textToShow += booksList.stream().map(Book::getName).collect(Collectors.joining(","));
//            textToShow += "]";
//        }
//        Toast.makeText(this, textToShow, Toast.LENGTH_SHORT).show();
    }

    public void click5(View view) {

    }
}