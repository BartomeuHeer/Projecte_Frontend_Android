package edu.upc.eetac.dsa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.upc.eetac.dsa.models.Item;
import edu.upc.eetac.dsa.models.LogInParams;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import edu.upc.eetac.dsa.models.User;

public class ShopActivity extends AppCompatActivity {
    private List<Item> itemList;
    private RecyclerView recyclerView;
    ApiInterface apiInterface;
    private TextView remaining_coins;
    SharedPreferences sharedPref;
    String username;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        recyclerView = findViewById(R.id.recyclerViewAct);
        remaining_coins = findViewById(R.id.remainin_coins_text);
        itemList = new ArrayList<>();
        apiInterface = Api.getClient();
        sharedPref = getSharedPreferences("LoginData", MODE_PRIVATE);
        username = sharedPref.getString("username", "");

        setItemInfo();



    }

    private void setAdapter(List<Item> list) {

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list, username);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


    }

    private void setInfo(int coins)
    {
        remaining_coins.setText(Integer.toString(coins));
        apiInterface.getItemsforShop().enqueue(new Callback<List<Item>>(){

            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful()) {
                    Log.d("shopbien", response.body().get(0).getName());
                    setAdapter(response.body());
                }
                else
                {
                    Log.d("shopmal", Integer.toString(response.code()));
                }

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();


            }
        });
    }


    public void setItemInfo(){

        apiInterface.getUser(new String (username)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("grup1",""+response.code());
                String c = Integer.toString(response.code());
                if (response.isSuccessful()) {
                    setInfo(response.body().getCoins());

                }
                Toast.makeText(getApplicationContext(), c + ": " + response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("grup1",""+t.getMessage());
                Toast.makeText(getApplicationContext(), "Error22", Toast.LENGTH_SHORT).show();
            }
        });



    }


}
