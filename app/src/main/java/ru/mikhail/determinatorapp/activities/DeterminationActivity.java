package ru.mikhail.determinatorapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.Serializable;
import java.util.List;

import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.adapters.ChoiceAdapter;
import ru.mikhail.determinatorapp.common.Book;
import ru.mikhail.determinatorapp.determination.Card;
import ru.mikhail.determinatorapp.common.LifeForm;
import ru.mikhail.determinatorapp.determination.Node;
import ru.mikhail.determinatorapp.util.BookParser;
import ru.mikhail.determinatorapp.util.LocalLibraryLoader;

public class DeterminationActivity extends AppCompatActivity {

    private List<Card> cards;
    private LocalLibraryLoader loader;
    private ChoiceAdapter adapter;
    private Card current;
    private Book book;
    private BookParser parser;
    private ListView choiceList;

    private LifeForm lifeForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_determination);
        lifeForm = new LifeForm();
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

        choiceList = findViewById(R.id.choice_list);
        choiceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Node touched = current.getNodes().get(position);
                lifeForm.addDescription(touched.getParameters());
                if (touched.getKeyToGo() == -1){
                    Intent intent = new Intent(DeterminationActivity.this, ResultsActivity.class);
                    intent.putExtra("lifeform", (Serializable) lifeForm.getDescriptionMap());
                    current = cards
                            .stream()
                            .filter(card -> card.getKey()==0)
                            .findFirst()
                            .orElseGet(null);
                    update();
                    DeterminationActivity.this.startActivity(intent);
                }
                else {
                    callNext(touched.getKeyToGo());
                }
            }
        });
        adapter = new ChoiceAdapter(this, R.layout.choice_fragment, current.getNodes());
        choiceList.setAdapter(adapter);

    }

    private Card findCardByKey(int key){
        return cards
                .stream()
                .filter(c -> c.getKey() == key)
                .findFirst()
                .orElseGet(null);
    }
    private void callNext(int cardKey){
        current = findCardByKey(cardKey);
        update();
    }
    private void update(){
        adapter = new ChoiceAdapter(this, R.layout.choice_fragment, current.getNodes()
        );
        choiceList.setAdapter(adapter);
    }
}