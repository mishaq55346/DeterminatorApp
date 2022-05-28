package ru.mikhail.determinatorapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import ru.mikhail.determinatorapp.Determinator;
import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.activities.DeterminatorBookActivity;
import ru.mikhail.determinatorapp.common.Book;
import ru.mikhail.determinatorapp.util.LocalLibraryLoader;
import ru.mikhail.determinatorapp.util.RequestHandler;

public class GlobalLibraryAdapter extends ArrayAdapter<Book> {
    private List<Book> items;
    private LayoutInflater inflater;
    private Context context;
    private int layout;
    LocalLibraryLoader libraryLoader;

    public GlobalLibraryAdapter(Context context, int resource, List<Book> items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        libraryLoader = new LocalLibraryLoader();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final GlobalBookViewHolder globalBookViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            globalBookViewHolder = new GlobalBookViewHolder(convertView);
            convertView.setTag(globalBookViewHolder);
        } else {
            globalBookViewHolder = (GlobalBookViewHolder) convertView.getTag();
        }
        final Book book = items.get(position);

        globalBookViewHolder.name.setText(items.get(position).getTitle());
        globalBookViewHolder.smallName.setText(items.get(position).getTitle());
        globalBookViewHolder.authors.setText(String.join(", ", items.get(position).getAuthors()));
        globalBookViewHolder.hiddenId.setText(String.valueOf(position));

        globalBookViewHolder.downloadButton.setOnClickListener(v -> {
            int id = book.getId();
            Toast.makeText(context, "Trying to download book with id " + id, Toast.LENGTH_SHORT).show();
            RequestHandler.sendRequest(RequestHandler.RequestType.GET_BOOK, context,
                    RequestHandler.getRequestJson("book_id", id),
                    response -> {
                        Book downloadedBook = null;
                        try {
                            int downloadedId = response.getInt("id");
                            String author = response.getString("author");
                            String title = response.getString("title");
                            int year = response.getInt("year");
                            String roles = response.getString("roles");
                            String content = response.getString("content");
                            downloadedBook = new Book(downloadedId, title, year, author.split(","), roles.split(","), content);
                        } catch (JSONException e) {
                            Log.e(Determinator.TAG, "Error parsing response: ", e);
                        }
                        if (downloadedBook != null) {
                            libraryLoader.addBook(downloadedBook);
                            Toast.makeText(context, "Book with id " + id + " has been downloaded", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> {
                        Log.e(Determinator.TAG, "Error downloading book: ", error);
                    });
        });

        return convertView;
    }

    private class GlobalBookViewHolder {
        final Button downloadButton;
        TextView name, authors, smallName, hiddenId;

        GlobalBookViewHolder(View view) {
            downloadButton = view.findViewById(R.id.fragment_button);
            name = view.findViewById(R.id.fragment_name);
            smallName = view.findViewById(R.id.fragment_name_small);
            authors = view.findViewById(R.id.fragment_authors);
            hiddenId = view.findViewById(R.id.fragment_hidden_id);
        }
    }
}