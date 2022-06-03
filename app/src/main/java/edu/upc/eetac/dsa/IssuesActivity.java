package edu.upc.eetac.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.upc.eetac.dsa.models.Issue;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssuesActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private EditText dateText,messageText;
    SharedPreferences shp;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        apiInterface = Api.getClient();
        dateText = (EditText) findViewById(R.id.date);
        messageText = (EditText) findViewById(R.id.evMessage);
        send = (Button) findViewById(R.id.btnSendIssue);
        shp = getSharedPreferences("LoginData",MODE_PRIVATE);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });
    }

    private void makeCall(){
        String date = dateText.getText().toString().trim();
        String message = messageText.getText().toString().trim();
        String informer = shp.getString("username", "");

        apiInterface.sendIssue(new Issue(date,informer,message)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                    Toast.makeText(IssuesActivity.this, "Issue sent", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(IssuesActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}