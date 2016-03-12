package com.example.amazigh.helpme;

import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1;
    EditText login, pw, tel, email;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // final TextView outputView = (TextView) findViewById(R.id.showOutput);




        login=(EditText)findViewById(R.id.login);
        pw=(EditText)findViewById(R.id.pw);
        tel=(EditText)findViewById(R.id.tel);
        email=(EditText)findViewById(R.id.email);
        String urlParameters = "name=buzz";


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String login_str=login.getText().toString();
                String pw_str=pw.getText().toString();
                String tel_str=tel.getText().toString();
                String email_str=email.getText().toString();
                Snackbar.make(view, "the user " + login_str + " is added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
new SendPost().execute(login_str,pw_str,tel_str,email_str);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent in =new Intent(MainActivity.this,Userlist.class);
            startActivity(in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    class SendPost extends AsyncTask<String, Void, Void> {

        private Exception exception;

        protected Void doInBackground(String... urls) {

            try{
                String  login_string=urls[0];
                String   pw_string=urls[1];
                String   tel_string=urls[2];
                String   email_string=urls[3];
                URL  url = new URL("http://helpme.esy.es/index.php");
                String data = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(login_string, "UTF-8");
                data += "&" + URLEncoder.encode("pw", "UTF-8") + "=" + URLEncoder.encode(pw_string, "UTF-8");
                data += "&" + URLEncoder.encode("tel", "UTF-8") + "=" + URLEncoder.encode(tel_string, "UTF-8");
                data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email_string, "UTF-8");


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
                String line;
                while ((line = rd.readLine()) != null) {
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

            return null;
        }


        protected void onPostExecute(String feed) {
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }
}
