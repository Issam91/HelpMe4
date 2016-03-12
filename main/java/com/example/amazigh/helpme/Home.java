package com.example.amazigh.helpme;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Home extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private String[] links;
    private ActionBarDrawerToggle drawerListner;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        //lancer une commande du shell
        //Runtime.getRuntime().exec("Lancer");
        initActionBar();
        initMenu();
    }

    private void initMenu() {

    }

    private void initActionBar() {
        drawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        drawerList=(ListView)findViewById(R.id.drawerList);
        links= getResources().getStringArray(R.array.Links);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,links);
        drawerList.setAdapter(adapter);
        drawerList.setOnItemClickListener(this);
        drawerListner = new ActionBarDrawerToggle(this, drawerLayout, null ,R.string.drawer_open,R.string.drawer_close ){
            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(Home.this,"onglet ouvert ",Toast.LENGTH_SHORT).show();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(Home.this," onglet  fermer ",Toast.LENGTH_SHORT).show();
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerListner);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerListner.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(drawerListner.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,links[position]+" est selectionné ",Toast.LENGTH_SHORT).show();
        //selectItem(position);
        switch (links[position]){
            case "Connexion" : intent = new Intent(Home.this, Login.class); startActivity(intent); break;
            case "demande" : intent = new Intent(Home.this, RequestService.class); startActivity(intent); break;
            case "liste des utilisateurs" : intent = new Intent(Home.this, Userlist.class); startActivity(intent); break;

        }
    }
    // traitement de plus
    public   void selectItem(int position) {
        drawerList.setItemChecked(position, true);
        setTitle(links[position]);

    }


    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
