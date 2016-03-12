package com.example.amazigh.helpme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        Toast.makeText(this, "Rénitialiser le mot de passe", Toast.LENGTH_LONG).show();
    }
}
