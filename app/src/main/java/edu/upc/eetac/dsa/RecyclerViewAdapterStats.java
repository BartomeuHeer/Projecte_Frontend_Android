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

import edu.upc.eetac.dsa.models.Stats;

public class RecyclerViewAdapterStats extends RecyclerView.Adapter<RecyclerViewAdapterStats.myViewHolder>{

    private List<Stats> statsList;
    private String stats;

    public RecyclerViewAdapterStats(List<Stats> statsList, String stats){
        this.statsList=statsList;
        this.stats=stats;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        private TextView nameText,pointsText;
        private ImageView img;


        public myViewHolder (final View view)
        {
            super(view);
            nameText = view.findViewById(R.id.name_player);
            pointsText = view.findViewById(R.id.points);
            img = view.findViewById(R.id.imageView2);

        }

    }
    @NonNull
    @Override
    public RecyclerViewAdapterStats.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View statsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_for_stats,parent,false);
        return new RecyclerViewAdapterStats.myViewHolder(statsView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterStats.myViewHolder holder, int position) {
        String name = statsList.get(position).getUsername();
        String time = Integer.toString(statsList.get(position).getTime());
        String kills = Integer.toString(statsList.get(position).getEnemiesKilled());
        String avatarurl= statsList.get(position).getAvatar();
        holder.nameText.setText(name);
        if (stats.equals("kill"))
        {
            holder.pointsText.setText(kills + " enemies killed");
        }
        else
        {
            holder.pointsText.setText(time + " seconds");
        }

        Picasso.get().load(statsList.get(position).getAvatar()).into(holder.img);


    }

    public void clear() {
        int size = statsList.size();
        statsList.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public int getItemCount() {
        return statsList.size();
    }
}
