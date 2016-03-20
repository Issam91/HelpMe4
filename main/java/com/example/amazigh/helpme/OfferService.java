package com.example.amazigh.helpme;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.amazigh.helpme.Logger.Login;
import com.example.amazigh.helpme.Logger.Subscrib;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class OfferService extends AppCompatActivity implements View.OnClickListener {
    EditText desc, title, datemax;
    private Button button;
    private Spinner category, prixmax, prixmin;
    private EditText address_o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offer_service);
        initSpinenrMonnaie();
        category = (Spinner) findViewById(R.id.categories);
        desc = (EditText) findViewById(R.id.description);
        title = (EditText) findViewById(R.id.title);
        address_o = (EditText) findViewById(R.id.adress_o);


        prixmax = (Spinner) findViewById(R.id.maxPrice);
        prixmin = (Spinner) findViewById(R.id.minPrice);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    public void initSpinenrMonnaie() {
        Spinner categories, minPrice, maxPrice;
        String[] options = {"0", "50", "200", "300"};
        String[] listCategory = {"Déménagement", "ménage", "cours de soutien", "autres"};
        categories = (Spinner) findViewById(R.id.categories);
        minPrice = (Spinner) findViewById(R.id.minPrice);
        maxPrice = (Spinner) findViewById(R.id.maxPrice);
        ArrayAdapter<String> adapterCategories = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
        categories.setAdapter(adapterCategories);
        minPrice.setAdapter(adapter);
        maxPrice.setAdapter(adapter);
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
                Intent intent = new Intent(OfferService.this, Login.class);
                startActivity(intent);
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
        String title_str = title.getText().toString();
        String cat_str = category.getSelectedItem().toString();
        String desc_str = desc.getText().toString();
        String address_str = address_o.getText().toString();

        String prixmin_str = prixmin.getSelectedItem().toString();
        String prixmax_str = prixmax.getSelectedItem().toString();


        new SendPost().execute(cat_str, title_str, desc_str, prixmin_str, prixmax_str, Login.loginuser,address_str);

    }

    class SendPost extends AsyncTask<String, Void, Void> {

        private Exception exception;

        protected Void doInBackground(String... urls) {


            try {
                String data = URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(urls[0], "UTF-8");
                data += "&" + URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(urls[1], "UTF-8");
                data += "&" + URLEncoder.encode("desc", "UTF-8") + "=" + URLEncoder.encode(urls[2], "UTF-8");
                data += "&" + URLEncoder.encode("prixmin", "UTF-8") + "=" + URLEncoder.encode(urls[3], "UTF-8");
                data += "&" + URLEncoder.encode("prixmax", "UTF-8") + "=" + URLEncoder.encode(urls[4], "UTF-8");
                data += "&" + URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(urls[5], "UTF-8");
                data += "&" + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(urls[6], "UTF-8");



                URL url = new URL("http://helpme.esy.es/offer.php");
                Subscrib.senddata(url, data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return null;
        }


        protected void onPostExecute(String feed) {
            Toast.makeText(getApplicationContext(),"Offre envoyé",Toast.LENGTH_SHORT).show();

        }
    }
}
