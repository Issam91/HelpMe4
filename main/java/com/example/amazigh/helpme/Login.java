package com.example.amazigh.helpme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Context context;
    private EditText email, password;
    private Button connexion, inscription, forgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email=(EditText) findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);

        inscription=(Button)findViewById(R.id.inscription);
        inscription.setOnClickListener(this);

        forgotPassword=(Button) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.inscription :
                intent= new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.forgotPassword :
                intent= new Intent(this, ForgotPassword.class);
                startActivity(intent);
                break;
            case R.id.connexion :
                connexion();

        }

    }

    private void connexion() {
        /*
        * Verification de la saisie de l'integtalité des champs
        * verification avec la bdd
        * Affichage des erreur ou acces a lappli
        * */
        intent= new Intent(this, Home.class);
        startActivity(intent);

    }
}
