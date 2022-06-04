package edu.upc.eetac.dsa;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.upc.eetac.dsa.models.Stats;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatsActivity extends AppCompatActivity {

    private List<Stats> statsList;
    private RecyclerView recyclerView;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        recyclerView = findViewById(R.id.StatsViwer);
        statsList = new ArrayList<>();
        apiInterface = Api.getClient();

        setStatsInfo();


    }

    private void setAdapter(List<Stats> list) {
        RecyclerViewAdapterStats adapter = new RecyclerViewAdapterStats(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void setStatsInfo(){
        apiInterface.getRanking().enqueue(new Callback<List<Stats>>(){

            @Override
            public void onResponse(Call<List<Stats>> call, Response<List<Stats>> response) {
                if (response.isSuccessful()) {
                    Log.d("statsbien", response.body().get(0).getUsername());
                    setAdapter(response.body());
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
}
