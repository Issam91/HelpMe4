package com.example.amazigh.helpme.Logger;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amazigh.helpme.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Subscrib extends AppCompatActivity implements View.OnClickListener {


    public static String user;

    private EditText login, pw, tel, email,cpw;
    private Button b;
    private Button valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscrib_main);


        // final TextView outputView = (TextView) findViewById(R.id.showOutput);


        login = (EditText) findViewById(R.id.login);
        pw = (EditText) findViewById(R.id.pw);
        cpw = (EditText) findViewById(R.id.confirmationPassword);
        tel = (EditText) findViewById(R.id.tel);
        email = (EditText) findViewById(R.id.email);
        valider = (Button) findViewById(R.id.ajouter);
        valider.setOnClickListener(this);
        String urlParameters = "name=buzz";


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


    public static String senddata(URL url, String data){
        String result="";

        try{
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
            connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
            connection.setDoOutput(true);
            connection.setDoInput(true);


            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(data);
            wr.flush();
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line="";
            while ((line = rd.readLine()) != null) {
                result+=line;
                System.out.println(line);
            }
            wr.close();
            rd.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void onClick(View v) {
        if ((login.getText() != null) && (pw.getText() != null) &&(login.getText() != null) && (email.getText() != null) && (pw.getText().toString().equals(cpw.getText().toString()))) {


            String login_str = login.getText().toString();
            String pw_str = pw.getText().toString();
            String tel_str = tel.getText().toString();
            String email_str = email.getText().toString();
            new SendPost().execute(login_str, pw_str, tel_str, email_str);
            user = login_str;
        }else{
            Toast.makeText(Subscrib.this, "Verifier les champs",Toast.LENGTH_SHORT).show();
        }

    }

    class SendPost extends AsyncTask<String, Void, Void> {

        private Exception exception;

        protected Void doInBackground(String... urls) {


            String login_string = urls[0];
            String pw_string = urls[1];
            String tel_string = urls[2];
            String email_string = urls[3];
            try {
                String data = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(login_string, "UTF-8");

                data += "&" + URLEncoder.encode("pw", "UTF-8") + "=" + URLEncoder.encode(pw_string, "UTF-8");


                data += "&" + URLEncoder.encode("tel", "UTF-8") + "=" + URLEncoder.encode(tel_string, "UTF-8");
                data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email_string, "UTF-8");
                URL url = new URL("http://helpme.esy.es/index.php");
                senddata(url, data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return null;
        }


        protected void onPostExecute(String feed) {
            Toast.makeText(getApplicationContext(),"Vous êtes ajouté",Toast.LENGTH_SHORT).show();

        }
    }
}
