package edu.upc.eetac.dsa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import edu.upc.eetac.dsa.models.ForumMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumActivity extends AppCompatActivity {
    SharedPreferences sharedPref;
    SharedPreferences.Editor myEdit;
    private TextView messageText;
    RecyclerView recyclerView;
    private Button postBtn;
    ApiInterface apiInterface;
    List<ForumMessage> listMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        apiInterface = Api.getClient();

        postBtn = (Button) findViewById(R.id.postButtonID);
        recyclerView = findViewById(R.id.recyclerForumID);
        messageText = findViewById(R.id.postTextID);
        getAllEntries();
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMessage();
            }
        });

    }

    public void initializeRecyclerView(List<ForumMessage> listMessages) {
        SharedPreferences sharedPref = getSharedPreferences("LoginData", MODE_PRIVATE);
        String username = sharedPref.getString("username", "");
        RecyclerViewAdapterForum myAdapter = new RecyclerViewAdapterForum(this, listMessages, username);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

    }
    private void getAllEntries() {
        apiInterface.getAllMessages().enqueue(new Callback<List<ForumMessage>>() {
            @Override
            public void onResponse(Call<List<ForumMessage>> call, Response<List<ForumMessage>> response) {
                if (!response.isSuccessful()) {
                    Log.d("MYAPP", "Error" + response.code());
                    return;
                }
                listMessages = response.body();
                Collections.sort(listMessages);
                initializeRecyclerView(listMessages);
            }
            @Override
            public void onFailure(Call<List<ForumMessage>> call, Throwable t) {
                Log.d("MYAPP", "Error:" + t.getMessage());
            }
        });
    }


    public void addMessage (){
        SharedPreferences sharedPref = getSharedPreferences("LoginData", MODE_PRIVATE);
        String username = sharedPref.getString("username", "");
        String message = this.messageText.getText().toString();

        ForumMessage forumMessage= new ForumMessage(username,message);
        apiInterface.addEntry(forumMessage).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.d("MYAPP", "Error" + response.code());
                    return;
                }
                getAllEntries();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("MYAPP", "Error:" + t.getMessage());
            }
        });
        this.messageText.setText("");
    }


}
