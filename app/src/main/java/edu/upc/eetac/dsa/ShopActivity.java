package edu.upc.eetac.dsa;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.upc.eetac.dsa.models.Item;

public class ShopActivity extends AppCompatActivity {
    private ArrayList<Item> itemList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        recyclerView = findViewById(R.id.recyclerViewAct);
        itemList = new ArrayList<>();

        setItemInfo();
        setAdapter();




    }

    private void setAdapter() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(itemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void setItemInfo(){
        itemList.add(new Item("Espada", "Espada dorada",300.0));
        itemList.add(new Item("Escudo", "Escudo marron",45.0));
        itemList.add(new Item("Palo", "Palo madera",10.0));
        itemList.add(new Item("Espada", "Espada dorada",300.0));
        itemList.add(new Item("Escudo", "Escudo marron",45.0));
        itemList.add(new Item("Palo", "Palo madera",10.0));
        itemList.add(new Item("Espada", "Espada dorada",300.0));
        itemList.add(new Item("Escudo", "Escudo marron",45.0));
        itemList.add(new Item("Palo", "Palo madera",10.0));
        itemList.add(new Item("Espada", "Espada dorada",300.0));
        itemList.add(new Item("Escudo", "Escudo marron",45.0));
        itemList.add(new Item("Palo", "Palo madera",10.0));
    }


}
