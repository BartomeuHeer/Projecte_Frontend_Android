package edu.upc.eetac.dsa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.upc.eetac.dsa.models.Inventory;
import edu.upc.eetac.dsa.models.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryActivity extends AppCompatActivity {

    private List<Item> itemList;
    private List<Inventory> inventoryList;
    private RecyclerView recyclerView;
    ApiInterface apiInterface;
    SharedPreferences sharedPref;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        recyclerView = findViewById(R.id.recyclerViewInv);
        itemList = new ArrayList<>();
        inventoryList = new ArrayList<>();
        apiInterface = Api.getClient();
        sharedPref = getSharedPreferences("LoginData", MODE_PRIVATE);
        username = sharedPref.getString("username", "");

        setInventoryInfo();

    }

    private void setAdapter(List<Inventory> inventoryList, List<Item> itemList) {
        RecyclerViewAdapterInventory adapter = new RecyclerViewAdapterInventory(inventoryList, username,itemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setInventoryInfo()
    {
        apiInterface.getItemsforShop().enqueue(new Callback<List<Item>>(){

            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful()) {
                    Log.d("invbien", response.body().get(0).getName());
                    itemList = response.body();
                    //setAdapter(response.body());
                }
                else
                {
                    Log.d("invmal", Integer.toString(response.code()));
                }

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();


            }
        });

        apiInterface.getInventory(username).enqueue(new Callback<List<Inventory>>() {
            @Override
            public void onResponse(Call<List<Inventory>> call, Response<List<Inventory>> response) {
                inventoryList = response.body();

            }

            @Override
            public void onFailure(Call<List<Inventory>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        setAdapter( inventoryList, itemList);


    }
}
