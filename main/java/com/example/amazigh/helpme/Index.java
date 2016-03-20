package com.example.amazigh.helpme;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amazigh.helpme.Classe.Offer;
import com.example.amazigh.helpme.Logger.Login;
import com.example.amazigh.helpme.Services.Autre;
import com.example.amazigh.helpme.Services.Cours;
import com.example.amazigh.helpme.Services.Demenagement;
import com.example.amazigh.helpme.Services.Menage;

public class Index extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Button demenagement, menage, cours, autre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        demenagement = (Button) findViewById(R.id.demenagement);
        menage = (Button) findViewById(R.id.menage);
        cours = (Button) findViewById(R.id.cour);
        autre = (Button) findViewById(R.id.autre);

        demenagement.setOnClickListener(this);
        menage.setOnClickListener(this);
        cours.setOnClickListener(this);
        autre.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView wel=(TextView)findViewById(R.id.well);
        String user="";
        if(Login.loginuser!=null) user=Login.loginuser;
        //wel.setText(Login.getLoginuser());
        View view = getLayoutInflater().inflate(R.layout.nav_header_index, null);

TextView log=(TextView) view.findViewById(R.id.TextView1);
        try {
            log.setText("Soyez le bienvenu " + user);
        }catch (Exception e){

        }
        wel.setText("Soyez le bienvenu M " + user);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Index.this, RequestService.class);
                startActivity(intent);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.index, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_deconnexion:
                Intent intent = new Intent(Index.this, Login.class);

                startActivity(intent);
                break;
            case R.id.action_settings:
                Toast.makeText(Index.this, "parametres", Toast.LENGTH_SHORT).show();
                break;

        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_connexion) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);

        } else if (id == R.id.nav_accueil) {
            Intent intent = new Intent(this, Index.class);
            startActivity(intent);
        } else if (id == R.id.nav_offres) {
            Intent intent = new Intent(this, OfferService.class);
            startActivity(intent);
        }else if (id == R.id.list_request) {
            Intent intent = new Intent(this, Requestlist.class);
            startActivity(intent);

        } else if (id == R.id.list_offres) {
                Intent intent = new Intent(this, Offerlist.class);
                startActivity(intent);

        } else if (id == R.id.nav_demandes) {
            Intent intent = new Intent(this, RequestService.class);
            startActivity(intent);
        } else if (id == R.id.nav_utilisateur) {
            Intent intent = new Intent(Index.this, Userlist.class);

            startActivity(intent);
        }else if (id == R.id.nav_map) {
            Intent intent = new Intent(Index.this, MapsActivity.class);

            startActivity(intent);
        }else if (id == R.id.nav_deconnexion) {
            Intent intent = new Intent(Index.this, Login.class);

            startActivity(intent);
        }
        else if (id == R.id.nav_parametres) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.demenagement:
                intent = new Intent(Index.this, Demenagement.class);
                startActivity(intent);
                break;
            case R.id.cour:
                intent = new Intent(Index.this, Cours.class);
                startActivity(intent);
                break;
            case R.id.autre:
                intent = new Intent(Index.this, Autre.class);
                startActivity(intent);
                break;
            case R.id.menage:
                intent = new Intent(Index.this, Menage.class);
                startActivity(intent);
                break;
        }
    }
    public void notif(String title, String text, Context c){
        Intent intent = new Intent(c, Index.class);
// use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(c, (int) System.currentTimeMillis(), intent, 0);

// build notification
// the addAction re-use the same intent to keep the example short
        @SuppressLint("NewApi") Notification n  = new Notification.Builder(c)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark_normal)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build();


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);
    }
}
