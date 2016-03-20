package com.example.amazigh.helpme.Services;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.amazigh.helpme.Affiche;
import com.example.amazigh.helpme.Classe.Request;
import com.example.amazigh.helpme.ItemAdapter;
import com.example.amazigh.helpme.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.RestAdapter;

public class Menage extends AppCompatActivity {
    // static final String URL = "http://helpme.esy.es/requests";
    List<Request> requests;
    ListView lview;
    RestAdapter restAdapter;
    String title;
    // ArrayAdapter<String> adapter;
    private ListView listView;

    private ArrayAdapter<String> adapter;

    private class SendPost extends AsyncTask<String, Void,  HashMap<String,List<String>>> {
        List<String>  cats=new ArrayList<String>();
        List<String>  prixx=new ArrayList<String>();
        List<String>  titles=new ArrayList<String>();
        List<String>  dates=new ArrayList<String>();
        List<String>  adds=new ArrayList<String>();
        List<String>  descs=new ArrayList<String>();
        List<String>  logs=new ArrayList<String>();
        List<String>  prixlogs=new ArrayList<String>();

        List<String>  tels=new ArrayList<String>();


        HashMap<String,List<String>> values=new HashMap<>();



        // private Exception exception;
        @Override
        protected  HashMap<String,List<String>> doInBackground(String... urls) {


            String parsedString = "";
            try {
                URL url = new URL("http://helpme.esy.es/men");
                URLConnection conn = url.openConnection();

                HttpURLConnection httpConn = (HttpURLConnection) conn;
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();

                InputStream is = httpConn.getInputStream();
                Log.e("tag", is.toString());
                parsedString = convertinputStreamToString(is);
                Log.e("tag",parsedString);
                JSONArray arr = new JSONArray(parsedString);
                Log.e("tag", arr.get(1).toString());
                for (int i = 0; i < arr.length(); i++) {
                    HashMap<String,String> m=new HashMap<>();
                    try {
                        String login=arr.getJSONObject(i).getString("login");

                        m.put("login",login);
                        logs.add(login);
                        String date=arr.getJSONObject(i).getString("date");
                        m.put("date", date);
                        dates.add(date);


                        String category=arr.getJSONObject(i).getString("category");

                        cats.add(category);

                        m.put("category", category);
                        String prix=arr.getJSONObject(i).getString("prixmax")+" €";
                        m.put("prix",prix);
                        prixx.add(prix);
                        prixlogs.add(login+"\n"+prix);
                        String desc=arr.getJSONObject(i).getString("descrip");
                        descs.add(desc);
                        String tel=arr.getJSONObject(i).getString("tel");

                        tels.add(tel);
                        String add=arr.getJSONObject(i).getString("address");

                        adds.add(add);





                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }






            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            values.put("category",cats);
            values.put("login",logs);
            values.put("prix",prixx);
            values.put("prixlogin",prixlogs);

            values.put("date",dates);
            values.put("desc",descs);
            values.put("tel",tels);
            values.put("add",adds);








            return values;
        }


        protected void onPostExecute(final HashMap<String,List<String>> result) {
            // TODO: check this.exception
            // TODO: do something with the feed

            int size=result.get("category").size();
            String[] cs=result.get("category").toArray(new String[size]);
            ItemAdapter adapter = new ItemAdapter(getApplicationContext(),result.get("category").toArray(new String[size]),
                    result.get("prixlogin").toArray(new String[size]));
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(Menage.this,Affiche.class);
                    intent.putExtra("category", result.get("category").get(position));
                    intent.putExtra("prix",result.get("prix").get(position));
                    intent.putExtra("descrip",result.get("desc").get(position));
                    intent.putExtra("date",result.get("date").get(position));
                    intent.putExtra("login",result.get("login").get(position));
                    intent.putExtra("tel",result.get("tel").get(position));
                    intent.putExtra("add",result.get("add").get(position));

                    intent.putExtra("type","Offre");






                    startActivity(intent);

                }
            });


        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menage);
        listView =(ListView) findViewById(R.id.lv_menage);
        SendPost get=new SendPost();
        get.execute();




/*
            restAdapter = new RestAdapter.Builder()
                .setEndpoint(URL)
                .build();
        ApiUser api = restAdapter.create(ApiUser.class);
        Toast.makeText(getBaseContext(), Subscrib.user,
                Toast.LENGTH_SHORT).show();
        Log.i("tag", "________");
*/





        /*
api.getData(new Callback<List<Request>>() {
    @Override
    public void success(List<Request> requests, Response response) {

        List<String> titles = new ArrayList<String>();
        lview = (ListView) findViewById(R.id.lv);
        Log.i("tag", "________");

        for (Request u : requests) {
            title = u.getTitle().toString();
            titles.add(title);
            Log.i("tag", title);
            Toast.makeText(getBaseContext(), title,
                    Toast.LENGTH_SHORT).show();


        }
        adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, titles);
        lview.setAdapter(adapter);

    }

    @Override
    public void failure(RetrofitError error) {

    }
});*/
        Log.i("tag", "________");


    }



    public static String convertinputStreamToString(InputStream ists)
            throws IOException {
        if (ists != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader r1 = new BufferedReader(new InputStreamReader(
                        ists, "UTF-8"));
                while ((line = r1.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                ists.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }
}
