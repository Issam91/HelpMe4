package com.example.amazigh.helpme;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<HashMap<String,String>> listlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        new getlocation_d().execute();
        new getlocation_o().execute();

       /* for(HashMap<String,String> m:listlocation){
            double x=Double.parseDouble(m.get("lx"));
            double y=Double.parseDouble(m.get("ly"));
            String Categ=m.get("category");


            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(x, y);


            mMap.addMarker(new MarkerOptions().position(sydney).title(Categ));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }*/


    }
    class getlocation_d extends AsyncTask<String, Void, List<HashMap<String,String>>> {
        List<HashMap<String,String>> list=new ArrayList<HashMap<String, String>>();
        private Exception exception;

        protected List<HashMap<String,String>> doInBackground(String... urls) {


            String parsedString = "";
            //  users=new ArrayList<String>();
            try {
                URL url = new URL("http://helpme.esy.es/requests");
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
                    try {
                        HashMap<String,String> map=new HashMap<>();
                        String lx=arr.getJSONObject(i).getString("lx");
                        String ly=arr.getJSONObject(i).getString("ly");
                        String category=arr.getJSONObject(i).getString("category");
                        String login=arr.getJSONObject(i).getString("login");
                        String date=arr.getJSONObject(i).getString("date");
                        String prix=arr.getJSONObject(i).getString("prixmax")+" €";
                        String desc=arr.getJSONObject(i).getString("descrip");
                        String tel=arr.getJSONObject(i).getString("tel");
                        map.put("login",login);
                        map.put("date", date);
                        map.put("prix", prix);
                        map.put("category",category);
                        map.put("descrip",desc);
                        map.put("tel",tel);

                        map.put("lx",lx);
                        map.put("ly",ly);
                        list.add(map);
                        //   users.add(date+"\n"+category);

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

            return list;
        }


        protected void onPostExecute(List<HashMap<String,String>> list) {
            listlocation=list;
            for(HashMap<String,String> m:listlocation){
                double x=Double.parseDouble(m.get("lx"));
                double y=Double.parseDouble(m.get("ly"));
                String Categ=m.get("login")+": "+m.get("category");
                String snip="dmande ajouté le "+m.get("date")+ ",Prix: "+m.get("prix");
            Log.e(m.get("lx"),m.get("ly"));

                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(Double.parseDouble(m.get("lx")), Double.parseDouble(m.get("ly")));


                mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(m.get("lx")), Double.parseDouble(m.get("ly")))).title(Categ)).setSnippet(snip);
            //    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


            }


        }


    }
    class getlocation_o extends AsyncTask<String, Void, List<HashMap<String,String>>> {
        List<HashMap<String,String>> list=new ArrayList<HashMap<String, String>>();
        private Exception exception;

        protected List<HashMap<String,String>> doInBackground(String... urls) {


            String parsedString = "";
            //  users=new ArrayList<String>();
            try {
                URL url = new URL("http://helpme.esy.es/offers");
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
                    try {
                        HashMap<String,String> map=new HashMap<>();
                        String lx=arr.getJSONObject(i).getString("lx");
                        String ly=arr.getJSONObject(i).getString("ly");
                        String category=arr.getJSONObject(i).getString("category");
                        String login=arr.getJSONObject(i).getString("login");
                        String date=arr.getJSONObject(i).getString("date");
                        String prix=arr.getJSONObject(i).getString("prixmax")+" €";
                        String desc=arr.getJSONObject(i).getString("descrip");
                        String tel=arr.getJSONObject(i).getString("tel");
                        map.put("login",login);
                        map.put("date", date);
                        map.put("prix", prix);
                        map.put("category",category);
                        map.put("descrip",desc);
                        map.put("tel",tel);

                        map.put("lx",lx);
                        map.put("ly",ly);
                        list.add(map);
                        //   users.add(date+"\n"+category);

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

            return list;
        }


        protected void onPostExecute(List<HashMap<String,String>> list) {
            listlocation=list;
            for( HashMap<String,String> m:listlocation){
                double x=Double.parseDouble(m.get("lx"));
                double y=Double.parseDouble(m.get("ly"));
                String Categ=m.get("login")+": "+m.get("category");
                String snip="offre ajouté le "+m.get("date")+ ",Prix: "+m.get("prix");                Log.e(m.get("lx"),m.get("ly"));

                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(Double.parseDouble(m.get("lx")), Double.parseDouble(m.get("ly")));


                mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(m.get("lx")), Double.parseDouble(m.get("ly")))).title(Categ).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))).setSnippet(snip);

                //    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }


        }


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
