package edu.upc.eetac.dsa;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import edu.upc.eetac.dsa.models.Stats;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatsActivity extends AppCompatActivity {

    private List<Stats> statsList;
    private RecyclerView recyclerView;
    private Button kill_btn, time_btn;
    ApiInterface apiInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        recyclerView = findViewById(R.id.StatsViwer);
        kill_btn = findViewById(R.id.btn_kills);
        time_btn = findViewById(R.id.btn_time);
        statsList = new ArrayList<>();
        apiInterface = Api.getClient();
        time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatsTime();
            }
        });
        kill_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatsKill();
            }
        });

    }

    private void setAdapter(List<Stats> list, String stats) {
        RecyclerViewAdapterStats adapter = new RecyclerViewAdapterStats(list, stats);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    public void setStatsKill(){
        statsList.clear();
        apiInterface.getRankingByKills().enqueue(new Callback<List<Stats>>(){

            @Override
            public void onResponse(Call<List<Stats>> call, Response<List<Stats>> response) {
                if (response.isSuccessful()) {
                    Log.d("statsbien", response.body().get(0).getUsername());
                    statsList=response.body();
                    setAdapter(statsList,"kill");

                }
                else
                {
                    Log.d("statsmal", Integer.toString(response.code()));
                }

            }

            @Override
            public void onFailure(Call<List<Stats>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setStatsTime(){
        statsList.clear();
        apiInterface.getRankingByTime().enqueue(new Callback<List<Stats>>(){

            @Override
            public void onResponse(Call<List<Stats>> call, Response<List<Stats>> response) {
                if (response.isSuccessful()) {
                    Log.d("statsbien", response.body().get(0).getUsername());
                    statsList=response.body();
                    setAdapter(statsList,"time");

                }
                else
                {
                    Log.d("statsmal", Integer.toString(response.code()));
                }

            }

            @Override
            public void onFailure(Call<List<Stats>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onBackPressed() {
        finish();
    }
}
