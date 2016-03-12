package com.example.amazigh.helpme;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;


/**
 * Created by hp on 08/03/2016.
 */
public interface Apioffer {
    @GET("/offers.json")
    void getData(Callback<List<Offer>> cb);
}
