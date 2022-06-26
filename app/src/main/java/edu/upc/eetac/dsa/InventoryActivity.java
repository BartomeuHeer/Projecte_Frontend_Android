package edu.upc.eetac.dsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upc.eetac.dsa.models.Item;

public class InventoryActivity extends AppCompatActivity {
    private List<Item> itemList;
    private RecyclerView recyclerView;
    ApiInterface apiInterface;
}
