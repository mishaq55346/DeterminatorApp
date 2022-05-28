package ru.mikhail.determinatorapp.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ru.mikhail.determinatorapp.determination.Card;
import ru.mikhail.determinatorapp.common.LifeForm;
import ru.mikhail.determinatorapp.determination.Node;

public class BookParser {
    JSONObject root;
    public BookParser(String bookContent) throws JSONException {
        root = new JSONObject(bookContent);
    }
    public List<Card> parseBook() throws JSONException {
        List<Card> cards = new ArrayList<>();
        Iterator<String> keys = root.keys();
        while (keys.hasNext()){

            String keyStr = keys.next();
            int key = Integer.parseInt(keyStr);
            List<Node> nodes = new ArrayList<>();
            JSONArray cardObj = root.getJSONArray(keyStr);

            for (int i = 0; i < cardObj.length(); i++) {
                JSONObject jsonNode = (JSONObject) cardObj.get(i);


                Iterator<String> keys1 = jsonNode.keys();
                int keyToGo = 0;
                String description = "";
                Map<LifeForm.Taxon, String> props = new HashMap<>();
                while (keys1.hasNext()){
                    String next = keys1.next();
                    if (next.equals("key")){
                        keyToGo = jsonNode.getInt(next);
                    }
                    else if (next.equals("description")){
                        description = jsonNode.getString("description");
                    } else {
                        props.put(LifeForm.Taxon.fromString(next), jsonNode.getString(next));
                    }
                }
                nodes.add(new Node(keyToGo, description, props));
            }
            cards.add(new Card(key, nodes));
        }
        return cards;
    }
}
