package edu.upc.eetac.dsa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.eetac.dsa.models.*;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder>{

    private List<Item> itemList;


    public RecyclerViewAdapter (List<Item> itemList){
        this.itemList=itemList;
    }

    //public RecyclerViewAdapter (List<Stats> statsList){
        //this.statsList=statsList;
    //}

    public class myViewHolder extends RecyclerView.ViewHolder{
        private TextView nameText,priceText;
        private ImageView itempic;

        public myViewHolder (final View view)
        {
            super(view);
            nameText = view.findViewById(R.id.textView2);
            priceText = view.findViewById(R.id.textView3);
            itempic = view.findViewById(R.id.imageView_shop);
        }

    }
    @NonNull
    @Override
    public RecyclerViewAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop,parent,false);
        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.myViewHolder holder, int position) {
        String name = itemList.get(position).getName();
        String price = Double.toString(itemList.get(position).getCoins());
        holder.nameText.setText(name);
        holder.priceText.setText(price);
        Picasso.get().load(itemList.get(position).getUrlPic()).into(holder.itempic);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
