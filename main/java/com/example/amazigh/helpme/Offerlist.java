package com.example.amazigh.helpme;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.amazigh.helpme.Classe.Request;

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

public class Offerlist extends AppCompatActivity {
    // static final String URL = "http://helpme.esy.es/requests";
    List<Request> requests;
    ListView lview;
    RestAdapter restAdapter;
    String title;
    // ArrayAdapter<String> adapter;
    private ListView listV;

    ArrayAdapter<String> adapter2;


        // static final String URL = "http://helpme.esy.es/requests";


        private class SendPost extends AsyncTask<String, Void, HashMap<String,List<String>>>{
            List<String>  cats=new ArrayList<String>();
            List<String>  prixx=new ArrayList<String>();
            List<String>  titles=new ArrayList<String>();
            List<String>  dates=new ArrayList<String>();
            List<String>  adds=new ArrayList<String>();
            List<String>  descs=new ArrayList<String>();
            List<String>  logs=new ArrayList<String>();
            List<String>  prixlogs=new ArrayList<String>();


            HashMap<String,List<String>> values=new HashMap<>();



            // private Exception exception;
            @Override
            protected  HashMap<String,List<String>> doInBackground(String... urls) {


                String parsedString = "";
                try {
                    URL url = new URL("http://helpme.esy.es/offers");
                    URLConnection conn = url.openConnection();

                    HttpURLConnection httpConn = (HttpURLConnection) conn;
                    httpConn.setAllowUserInteraction(false);
                    httpConn.setInstanceFollowRedirects(true);
                    httpConn.setRequestMethod("GET");
                    httpConn.connect();

                    InputStream is = httpConn.getInputStream();
                    Log.e("tag",is.toString());
                    parsedString = convertinputStreamToString(is);
                    Log.e("tag",parsedString);
                    JSONArray arr = new JSONArray(parsedString);
                    Log.e("tag", arr.get(1).toString());
                    for (int i = 0; i < arr.length(); i++) {
                        HashMap<String,String> m=new HashMap<>();
                        try {
                            String login=arr.getJSONObject(i).getString("login");

                            m.put("login", login);
                            logs.add(login);
                            String date=arr.getJSONObject(i).getString("date");
                            m.put("date", date);
                            dates.add(date);


                            String category=arr.getJSONObject(i).getString("category");

                            cats.add(category);

                            m.put("category", category);
                            String prix=arr.getJSONObject(i).getString("prixmax")+" €";
                            m.put("prix", prix);

                            prixx.add(prix);

                            prixlogs.add(login+"\n"+prix);

                            String desc=arr.getJSONObject(i).getString("descrip");
                            descs.add(desc);
                            String add=arr.getJSONObject(i).getString("address");

                            adds.add(add);




                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                    int m=0;
                    String category=arr.getJSONObject(m).getString("category");
                    String login=arr.getJSONObject(m).getString("login");
                    String date=arr.getJSONObject(m).getString("date");
                    String prix=arr.getJSONObject(m).getString("prixmax")+" €";
                    String desc=arr.getJSONObject(m).getString("descrip");
                    String tel=arr.getJSONObject(m).getString("tel");
                    String add=arr.getJSONObject(m).getString("address");

                    String title="Offre ajouté par "+login;
                    String txt="le, "+date+"\n"+"pour "+category+"\n"+"pour "+prix;
                    Intent intent = new Intent(Offerlist.this, Affiche.class);

                    intent.putExtra("category", category);
                    intent.putExtra("prix",prix);
                    intent.putExtra("descrip",desc);
                    intent.putExtra("date",date);
                    intent.putExtra("login",login);
                    intent.putExtra("add",add);

                    intent.putExtra("type","Offre");

                    notif(title, txt, Offerlist.this,intent);






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
                values.put("date",dates);
                values.put("desc",descs);
                values.put("add",adds);

                values.put("prixlogin",prixlogs);






                return values;
            }


            protected void onPostExecute(final HashMap<String,List<String>> result) {
                // TODO: check this.exception
                // TODO: do something with the feed

                int size=result.get("category").size();
                String[] cs=result.get("category").toArray(new String[size]);
                ItemAdapter adapter = new ItemAdapter(getApplicationContext(),result.get("category").toArray(new String[size]),
                        result.get("prixlogin").toArray(new String[size]));
                listV.setAdapter(adapter);
                listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(Offerlist.this,Affiche.class);
                        intent.putExtra("category", result.get("category").get(position));
                        intent.putExtra("prix",result.get("prix").get(position));
                        intent.putExtra("descrip",result.get("desc").get(position));
                        intent.putExtra("date",result.get("date").get(position));
                        intent.putExtra("login",result.get("login").get(position));
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
        setContentView(R.layout.activity_offerlist);
        listV =(ListView) findViewById(R.id.lv_offer);
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



    public  String convertinputStreamToString(InputStream ists)
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
    public void notif(String title, String text, Context c,Intent intent){
// use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(c, (int) System.currentTimeMillis(), intent, 0);

// build notification
// the addAction re-use the same intent to keep the example short
        @SuppressLint("NewApi") Notification n  = new Notification.Builder(c)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.mipmap.ic)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build();


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(1, n);
    }
}

