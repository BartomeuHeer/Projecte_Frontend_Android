package edu.upc.eetac.dsa;
import java.util.List;

import edu.upc.eetac.dsa.models.ForumMessage;
import edu.upc.eetac.dsa.models.Issue;
import edu.upc.eetac.dsa.models.Item;
import edu.upc.eetac.dsa.models.LogInParams;
import edu.upc.eetac.dsa.models.Stats;
import edu.upc.eetac.dsa.models.Inventory;
import edu.upc.eetac.dsa.models.User;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiInterface {

    @GET("users/catalogo")
    Call<List<Item>> getItems();

    @GET("stats/ranking")
    Call<List<Stats>> getRanking();

    @POST("users/login")
    Call<User> login(@Body LogInParams loginpar);

    @POST("users/register")
    Call<User> register(@Body User user);

    @PUT("users/buyItem")
    Call<User> buyItems (@Body Inventory inventory);

    @GET("users")
    Call<List<User>> getUsers();

    @GET("catalogo")
    Call<List<Item>> getCatalogo();

    @GET("items/items")
    Call<List<Item>> getItemsforShop();

    @GET("users/{username}")
    Call<User> profile (@Path("username") String username);

    @POST("users/issue")
    Call<Void> sendIssue(@Body Issue issue);

    @PUT ("users/{username}/updatelanguage")
    Call<Void> updateUserLanguage(@Path("username") String username,@Body String language);

    @PUT ("users/{username}/updateusername")
    Call<Void> updateUserUsername(@Path("username") String username,@Body String newusername);
    @PUT ("users/{username}/updatepassword")
    Call<Void> updateUserPassword(@Path("username") String username,@Body String newpassword);
    @PUT ("users/{username}/updateemail")
    Call<Void> updateUserEmail(@Path("username") String username,@Body String newemail);

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    @DELETE("users/{username}")
    Call<Void> deleteUser(@Path("username") String username);

    @POST("forum/addMessage")
    Call<Void> addEntry(@Body ForumMessage message);

    @GET("forum/GetAll")
    Call<List<ForumMessage>> getAllMessages();

    @GET("inventory/{username}")
    Call<List<Inventory>> getInventory(@Path("username") String username);
}
