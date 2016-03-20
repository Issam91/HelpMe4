package com.example.amazigh.helpme.Logger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amazigh.helpme.Index;
import com.example.amazigh.helpme.R;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Context context;
    private EditText email, password;
    private Button connexion, inscription, forgotPassword;
    public static String loginuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email=(EditText) findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        connexion = (Button)findViewById(R.id.connexion);
        connexion.setOnClickListener(this);
        loginuser="guest";

        inscription=(Button)findViewById(R.id.inscription);
        inscription.setOnClickListener(this);

        forgotPassword=(Button) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.index, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_deconnexion:
                Toast.makeText(this,"deconexion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this,"parametres", Toast.LENGTH_SHORT).show();
                break;

        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.inscription :
                intent= new Intent(this, Subscrib.class);
                startActivity(intent);
                break;
            case R.id.forgotPassword :
                intent= new Intent(this, ForgotPassword.class);
                new SendPost_forget().execute(email.getText().toString(),"");
                startActivity(intent);
                break;
            case R.id.connexion :
                if((email.getText()!= null)/*&&(password.getText()!=null)*/){
                    Toast.makeText(this, "lancer la connexion ",Toast.LENGTH_SHORT).show();
                    //TextView wel=(TextView)findViewById(R.id.wel);
                    email=(EditText) findViewById(R.id.email);
                    password=(EditText)findViewById(R.id.password);
                    //    wel.setText("Soyez les bienvenus "+Login.loginuser);
                    new SendPost().execute(email.getText().toString(),password.getText().toString());
                    intent= new Intent(this, Index.class);
                }else{
                    Toast.makeText(this, "verifier les champs",Toast.LENGTH_SHORT).show();
                }

        }

    }
    class SendPost extends AsyncTask<String, String, String> {

        private Exception exception;

        protected String doInBackground(String... urls) {
            String result="";


            try {
                String data = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(urls[0], "UTF-8");
                data += "&" + URLEncoder.encode("mdp", "UTF-8") + "=" + URLEncoder.encode(urls[1], "UTF-8");


                URL url = new URL("http://helpme.esy.es/authentification.php");
                result= Subscrib.senddata(url, data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return result;
        }


        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            Log.e("tag", result);
            if(result.equals("success")){
                Intent intent=new Intent(Login.this,Index.class);
//                TextView wel=(TextView)findViewById(R.id.wel);
                email=(EditText) findViewById(R.id.email);
                password=(EditText)findViewById(R.id.password);
                loginuser=email.getText().toString();
                //  wel.setText("Soyez les bienvenus "+email.getText().toString());
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"echec d'ath",Toast.LENGTH_SHORT).show();

            }
        }
    }
    class SendPost_forget extends AsyncTask<String, String, String> {

        private Exception exception;

        protected String doInBackground(String... urls) {
            String result="";


            try {
                String data = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(urls[0], "UTF-8");
                data += "&" + URLEncoder.encode("mdp", "UTF-8") + "=" + URLEncoder.encode(urls[1], "UTF-8");


                URL url = new URL("http://helpme.esy.es/authen.php");
                result= Subscrib.senddata(url, data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return result;
        }


        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            Log.e("tag", result);

        }
    }

}
