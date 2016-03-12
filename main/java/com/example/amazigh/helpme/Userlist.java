package com.example.amazigh.helpme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Userlist extends AppCompatActivity {
    static final String API_URL = "http://helpme.esy.es";
    List<User> users;
    ListView lv;
    RestAdapter restAdapter;
    String login;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        Toast.makeText(getBaseContext(), "Liste des utilisateurs",
                Toast.LENGTH_SHORT).show();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();
        ApiUser methods = restAdapter.create(ApiUser.class);
        methods.getData(new Callback<List<User>>() {
            @Override
            public void success(List<User> user, Response response) {
                List<User> users = (List<User>) user;
                List<String> logins = new ArrayList<String>();
                lv = (ListView) findViewById(R.id.listview);

                for (User u : users) {
                    login = u.getLogin().toString();
                    logins.add(login);
                    Log.i("tag", login);
                    Toast.makeText(getBaseContext(), login,
                            Toast.LENGTH_SHORT).show();

                }
                adapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, logins);
                lv.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }


}
