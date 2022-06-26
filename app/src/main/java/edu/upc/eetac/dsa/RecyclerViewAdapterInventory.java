package edu.upc.eetac.dsa;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.eetac.dsa.models.Inventory;
import edu.upc.eetac.dsa.models.Item;
import edu.upc.eetac.dsa.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewAdapterInventory extends RecyclerView.Adapter<RecyclerViewAdapterInventory.myViewHolder>{
    private List<Item> inventoryList;
    private String username;
    ApiInterface apiInterface;
    String name=null;
    private List<Item> itemsList;

    public RecyclerViewAdapterInventory (List<Item> inventoryList, String username, List<Item> itemsList){
        this.inventoryList=inventoryList;
        this.itemsList=itemsList;
        this.username=username;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView nameText, quantityText;
        private ImageView itempic;

        public myViewHolder (final View view)
        {
            super(view);
            nameText = view.findViewById(R.id.text_name);
            quantityText = view.findViewById(R.id.text_quantity);
            itempic = view.findViewById(R.id.imageView_inv);

        }
    }
    @NonNull
    @Override
    public RecyclerViewAdapterInventory.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory,parent,false);
        return new RecyclerViewAdapterInventory.myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterInventory.myViewHolder holder, int position) {
        name = inventoryList.get(position).getName();
        holder.nameText.setText(name);
        //holder.quantityText.setText(price);
        Picasso.get().load(inventoryList.get(position).getUrlPic()).into(holder.itempic);


    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }

}
