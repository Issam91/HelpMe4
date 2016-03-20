package com.example.amazigh.helpme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amazigh.helpme.R;

/**
 * Created by hp on 15/03/2016.
 */
public class ItemAdapter extends ArrayAdapter {
    private final Context context;
    private final String[] values;
    private final String[] prix;


    public ItemAdapter(Context context, String[] values,String[] prix) {
        super(context, R.layout.item_layout, values);
        this.context = context;
        this.values = values;
        this.prix=prix;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.cat);
        TextView textprix = (TextView) rowView.findViewById(R.id.pri);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.Image);
        textView.setText(values[position]);
        textprix.setText(prix[position]);


        // Change icon based on name

        String s = values[position];

        if (s.equals("Déménagement")) {
            imageView.setImageResource(R.drawable.dem);
        } else if (s.equals("ménage")) {
            imageView.setImageResource(R.drawable.men);
        } else if (s.equals("cours de soutien")) {
            imageView.setImageResource(R.drawable.prof);
        } else if (s.equals("autres")) {
            imageView.setImageResource(R.drawable.add);
        }

        return rowView;
    }
}