package edu.upc.eetac.dsa;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;


import edu.upc.eetac.dsa.models.ForumMessage;

public class RecyclerViewAdapterForum extends RecyclerView.Adapter<RecyclerViewAdapterForum.ViewHolder>{
    //To create the views
    List<ForumMessage> listMessages;
    Context context;
    String username;


    public RecyclerViewAdapterForum(Context context, List<ForumMessage> listMessages, String username){
        this.listMessages = listMessages;
        this.context = context;
        this.username = username;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterForum.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.forum_message,parent,false);
        return new RecyclerViewAdapterForum.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterForum.ViewHolder holder, int position) {
        ForumMessage entry = this.listMessages.get(position);
        holder.user.setText(entry.getUsername());
        holder.message.setText(entry.getMessage());
    }


    @Override
    public int getItemCount() {
        return listMessages.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView user, message;
        ImageView profilePic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.userForumID);
            message = itemView.findViewById(R.id.messageForumID);
            profilePic = itemView.findViewById(R.id.imageForumID);
        }
    }
}