package edu.upc.eetac.dsa;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit = null;
    public static ApiInterface getClient() {

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
