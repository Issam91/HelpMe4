package com.example.amazigh.helpme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Subscribe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscribe);
        Toast.makeText(this, "Rénitialiser le mot de passe", Toast.LENGTH_LONG).show();
    }
}
