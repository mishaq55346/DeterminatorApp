package ru.mikhail.determinatorapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.activities.DeterminatorBookActivity;
import ru.mikhail.determinatorapp.common.Book;

public class BooksAdapter extends ArrayAdapter<Book> {
    private List<Book> items;
    private LayoutInflater inflater;
    private Context context;
    private int layout;

    public BooksAdapter(Context context, int resource, List<Book> items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final LocalBookViewHolder localBookViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            localBookViewHolder = new LocalBookViewHolder(convertView);
            convertView.setTag(localBookViewHolder);
        } else {
            localBookViewHolder = (LocalBookViewHolder) convertView.getTag();
        }
        final Book book = items.get(position);

        localBookViewHolder.name.setText(items.get(position).getName());
        localBookViewHolder.smallName.setText(items.get(position).getName());
        localBookViewHolder.authors.setText(String.join(", ", items.get(position).getAuthors()));
        localBookViewHolder.hiddenId.setText(String.valueOf(position));

        localBookViewHolder.beginButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, DeterminatorBookActivity.class);
            intent.putExtra("book", book);
            context.startActivity(intent);
        });

        return convertView;
    }

    private class LocalBookViewHolder {
        final Button beginButton;
        TextView name, authors, smallName, hiddenId;

        LocalBookViewHolder(View view) {
            beginButton = view.findViewById(R.id.fragment_button);
            name = view.findViewById(R.id.fragment_name);
            smallName = view.findViewById(R.id.fragment_name_small);
            authors = view.findViewById(R.id.fragment_authors);
            hiddenId = view.findViewById(R.id.fragment_hidden_id);
        }
    }
}