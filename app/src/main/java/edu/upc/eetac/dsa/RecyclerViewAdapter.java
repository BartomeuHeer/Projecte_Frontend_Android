package edu.upc.eetac.dsa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.eetac.dsa.models.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder>{

    private List<Item> itemList;
    private String username;
    private TextView remaining_coins;
    ApiInterface apiInterface;
    String name=null;
    private ShopActivity shopAct;




    public RecyclerViewAdapter (List<Item> itemList, String username, ShopActivity shopAct){
        this.itemList=itemList;
        this.username=username;
        this.shopAct=shopAct;
    }

    //public RecyclerViewAdapter (List<Stats> statsList){
        //this.statsList=statsList;
    //}



    public class myViewHolder extends RecyclerView.ViewHolder{
        private TextView nameText,priceText;
        private ImageView itempic;
        private Button buyButton;




        public myViewHolder (final View view)
        {
            super(view);
            nameText = view.findViewById(R.id.textView2);
            priceText = view.findViewById(R.id.textView3);
            itempic = view.findViewById(R.id.imageView_shop);
            buyButton = (Button) view.findViewById(R.id.button_buy);
            apiInterface = Api.getClient();
            buyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("recyclerbutton", username);
                    Log.d("recyclerbutton impo", nameText.getText().toString());
                    apiInterface.buyItems(new Inventory(nameText.getText().toString(),username)).enqueue(

                            new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    //remaining_coins = view.findViewById(R.id.remainin_coins_text);
                                    //remaining_coins.setText(Integer.toString(response.body().getCoins()));
                                    //Log.d("recyclercoins", Integer.toString(response.body().getCoins()));
                                    Log.d("recyclerbutton", nameText.getText().toString());
                                    int coins = response.body().getCoins();
                                    shopAct.updateCoins(Integer.toString(coins));

                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {

                                }
                            });


                }

            });



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
        name = itemList.get(position).getName();
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
