package edu.upc.eetac.dsa;
import java.util.List;

import edu.upc.eetac.dsa.models.Item;
import edu.upc.eetac.dsa.models.LogInParams;
import edu.upc.eetac.dsa.models.User;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiInterface {

    @GET("catalogo")
    Call<List<Item>> getItems();




    @POST("users/login")
    Call<User> login(@Body LogInParams loginpar);


    @POST("users/register")
    Call<User> register(@Body User user);

    @GET("users")
    Call<List<User>> getUsers();

    @GET("catalogo")
    Call<List<Item>> getCatalogo();

    @POST("users/profile")
    Call<User> profile (@Body String username);
}
