package edu.upc.eetac.dsa;
import java.util.List;

import edu.upc.eetac.dsa.models.Item;
import edu.upc.eetac.dsa.models.LoginParam;
import edu.upc.eetac.dsa.models.User;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiInterface {

    @GET("catalogue")
    Call<List<Item>> getItems();


    @POST("login/")
    Call<User> login(@Body LoginParam parameters);


    @POST("register/")
    Call<User> register(@Field("username") String userName, @Field("password") String password, @Field("mail") String mail);
}
