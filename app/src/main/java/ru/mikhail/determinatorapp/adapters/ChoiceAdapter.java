package ru.mikhail.determinatorapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.determination.Node;

public class ChoiceAdapter extends ArrayAdapter<Node> {
    private List<Node> items;
    private LayoutInflater inflater;
    private int layout;

    public ChoiceAdapter(Context context, int resource, List<Node> items) {
        super(context, resource, items);
        this.items = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final LocalChoiceViewHolder localChoiceViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            localChoiceViewHolder = new LocalChoiceViewHolder(convertView);
            convertView.setTag(localChoiceViewHolder);
        } else {
            localChoiceViewHolder = (LocalChoiceViewHolder) convertView.getTag();
        }

        localChoiceViewHolder.text.setText(items.get(position).getDescription());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }

    private class LocalChoiceViewHolder {
        TextView text;

        LocalChoiceViewHolder(View view) {
            text = view.findViewById(R.id.choice_text_fragment);
        }
    }
}