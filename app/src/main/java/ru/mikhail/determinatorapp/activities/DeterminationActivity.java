package ru.mikhail.determinatorapp.activities;

import static ru.mikhail.determinatorapp.DeterminatorApp.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.util.List;

import ru.mikhail.determinatorapp.DeterminatorApp;
import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.adapters.ChoiceAdapter;
import ru.mikhail.determinatorapp.common.Book;
import ru.mikhail.determinatorapp.determination.Card;
import ru.mikhail.determinatorapp.util.BookParser;
import ru.mikhail.determinatorapp.util.LocalLibraryLoader;

public class DeterminationActivity extends AppCompatActivity {

    private List<Card> cards;
    private LocalLibraryLoader loader;
    private ChoiceAdapter adapter;
    private Card current;
    private Book book;
    private BookParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_determination);

        loader = new LocalLibraryLoader();
        Intent intent = getIntent();
        book = intent.getParcelableExtra("book");
        try {
            parser = new BookParser(book.getContent());
            cards = parser.parseBook();
        } catch (JSONException e) {
            Log.e("DET", "onCreate: ", e);
        }
        current = cards
                .stream()
                .filter(card -> card.getKey()==0)
                .findFirst()
                .orElseGet(null);

        ListView choiceList = findViewById(R.id.choice_list);
        adapter = new ChoiceAdapter(this, R.layout.choice_fragment, current.getNodes());
        choiceList.setAdapter(adapter);
        choiceList.setOnItemClickListener((parent, view, position, id) -> {
            Log.i(TAG, String.format("item click: id=%d, pos=%d",id, position));
            Toast.makeText(this, "dsadasdasd", Toast.LENGTH_SHORT).show();
        });

    }

    private Card findCardByKey(List<Card> cards, int key){
        return cards
                .stream()
                .filter(c -> c.getKey() == key)
                .findFirst()
                .orElseGet(null);
    }
}