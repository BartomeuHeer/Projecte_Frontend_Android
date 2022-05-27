package edu.upc.eetac.dsa;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.upc.eetac.dsa.models.Item;
import edu.upc.eetac.dsa.models.LogInParams;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import edu.upc.eetac.dsa.models.User;






public class ProfileActivity extends AppCompatActivity{

    private TextView userNameText, passText, mailText;
    private Button deleteBtn;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userNameText = (TextView) findViewById(R.id.profilename);
        passText = (TextView) findViewById(R.id.profilepassword);
        mailText = (TextView) findViewById(R.id.profilemail);

        deleteBtn = (Button) findViewById(R.id.button_delete);
        apiInterface = Api.getClient();

        profile();
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void profile() {

        String un = userNameText.getText().toString().trim();


        apiInterface.profile(new String (un)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("grup1",""+response.code());
                String c = Integer.toString(response.code());
                if (response.isSuccessful()) {
                    // Recoger y asignar al cuadro de texto;
                }
                Toast.makeText(getApplicationContext(), c + ": " + response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("grup1",""+t.getMessage());
                Toast.makeText(getApplicationContext(), "Error22", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
