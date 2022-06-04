package edu.upc.eetac.dsa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upc.eetac.dsa.models.Stats;

public class RecyclerViewAdapterStats extends RecyclerView.Adapter<RecyclerViewAdapterStats.myViewHolder>{

    private List<Stats> statsList;

    public RecyclerViewAdapterStats(List<Stats> statsList){
        this.statsList=statsList;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        private TextView nameText,pointsText;

        public myViewHolder (final View view)
        {
            super(view);
            nameText = view.findViewById(R.id.name_player);
            pointsText = view.findViewById(R.id.points);
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
        String points = Integer.toString(statsList.get(position).getPoints());
        holder.nameText.setText(name);
        holder.pointsText.setText(points);

    }

    @Override
    public int getItemCount() {
        return statsList.size();
    }
}
