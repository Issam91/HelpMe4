package com.example.amazigh.helpme;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amazigh.helpme.R;

public class Affiche extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche);
        TextView tv = (TextView) findViewById(R.id.display);
        TextView login = (TextView) findViewById(R.id.log2);
        TextView cat = (TextView) findViewById(R.id.cat2);
        TextView prix = (TextView) findViewById(R.id.pri2);
        TextView desc = (TextView) findViewById(R.id.desc2);
        TextView add = (TextView) findViewById(R.id.add2);

        ImageView imageView = (ImageView) findViewById(R.id.Image2);

        cat.setText(getIntent().getStringExtra("category"));
        login.setText(getIntent().getStringExtra("login"));
        desc.setText("Description : \n" + getIntent().getStringExtra("descrip"));
        tv.setText(getIntent().getStringExtra("type") + " Ajouté le: " + getIntent().getStringExtra("date"));
        prix.setText("Prix : \t" + getIntent().getStringExtra("prix"));
        add.setText("Addresse: " + getIntent().getStringExtra("add"));


        String s = cat.getText().toString();

        if (s.equals("Déménagement")) {
            imageView.setImageResource(R.drawable.dem);
        } else if (s.equals("ménage")) {
            imageView.setImageResource(R.drawable.men);
        } else if (s.equals("cours de soutien")) {
            imageView.setImageResource(R.drawable.prof);
        } else if (s.equals("autres")) {
            imageView.setImageResource(R.drawable.add);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getIntent().getStringExtra("category") + "\n" + getIntent().getStringExtra("prix"), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                String posted_by = getIntent().getStringExtra("tel");

                String uri = "tel:" + posted_by.trim();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));

                startActivity(intent);
            }
        });

    }
}
