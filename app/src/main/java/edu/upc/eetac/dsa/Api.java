package edu.upc.eetac.dsa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit;
    public static ApiInterface getClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        //if (retrofit==null) {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/dsaApp/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //}
        return retrofit.create(ApiInterface.class);
    }
}
