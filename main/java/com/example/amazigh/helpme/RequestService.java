package com.example.amazigh.helpme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RequestService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_service);
        initSpinenrMonnaie();

    }
    public void initSpinenrMonnaie(){
        Spinner categories,minPrice,maxPrice;
        String [] options={"0","50", "200","300"};
        String [] listCategory={"Déménagement", "ménage", "cours de soutien"};
        categories =(Spinner) findViewById(R.id.categories);
        minPrice =(Spinner) findViewById(R.id.minPrice);
        maxPrice =(Spinner) findViewById(R.id.maxPrice);
        ArrayAdapter<String> adapterCategories =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listCategory);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, options);
        categories.setAdapter(adapterCategories);
        minPrice.setAdapter(adapter);
        maxPrice.setAdapter(adapter);
    }
}
