package edu.upc.eetac.dsa;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface API {

    @GET("items/")
    Call<List<Item>> getItems();

    @POST("login/")
    Call<User> login(@Body User user);
}
