package edu.upc.eetac.dsa;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

    private TextView userNameText, passText, mailText,languageText;
    private Button deleteBtn, updateBtn;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userNameText = (TextView) findViewById(R.id.usernameBox);
        passText = (TextView) findViewById(R.id.passBox);
        mailText = (TextView) findViewById(R.id.emailBox);
        languageText = (TextView) findViewById(R.id.languageBox);
        deleteBtn = (Button) findViewById(R.id.delete_btn);
        updateBtn = (Button) findViewById(R.id.update_btn);
        apiInterface = Api.getClient();

        User user = profile();
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int i = Integer.parseInt((String)v.getTag());

                AlertDialog.Builder alert = new AlertDialog.Builder(ProfileActivity.this);
                alert.setTitle("Delete");
                alert.setMessage("Are you sure you want to delete your user?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteUser();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();

            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateUserActivity();
            }
        });
    }

    private User profile() {

        SharedPreferences sharedPref = getSharedPreferences("LoginData", MODE_PRIVATE);
        String username = sharedPref.getString("username", "");
        User user = new User();
        apiInterface.profile(new String (username)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("grup1",""+response.code());
                String c = Integer.toString(response.code());
                if (response.isSuccessful()) {
                    user.setUsername(response.body().getUsername());
                    user.setEmail(response.body().getEmail());
                    user.setCoins(response.body().getCoins());
                    user.setPassword(response.body().getPassword());
                    user.setLanguage(response.body().getLanguage());
                    pintamelo(user);
                }
                Toast.makeText(getApplicationContext(), c + ": " + response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("grup1",""+t.getMessage());
                Toast.makeText(getApplicationContext(), "Error22", Toast.LENGTH_SHORT).show();
            }
        });
        return user;
    }
    private void pintamelo(User user){
        userNameText.setText(user.getUsername());
        passText.setText(user.getPassword());
        mailText.setText(user.getEmail());
        languageText.setText(user.getLanguage());
        //languageText.setText(user.getLanguage());
    }
    private void openUpdateUserActivity(){

        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra("username", userNameText.getText().toString());
        intent.putExtra("password", passText.getText().toString());
        intent.putExtra("email", mailText.getText().toString());
        startActivity(intent);
    }
    private void deleteUser(){
        SharedPreferences sharedPref = getSharedPreferences("LoginData", MODE_PRIVATE);
        String username = sharedPref.getString("username", "");

        apiInterface.deleteUser(new String(username)).enqueue(new Callback<Void>(){
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("grup1Bien",""+response.code());
                String c = Integer.toString(response.code());
                if (response.isSuccessful()) {
                    logOut();
                }
                Toast.makeText(getApplicationContext(), c + ": " + response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("grup1",""+t.getMessage());
                Toast.makeText(getApplicationContext(), "Error22", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void logOut(){
        SharedPreferences myPrefs = getSharedPreferences("LoginData",
                MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(this,
                LandPageActivity.class);
        startActivity(intent);
        finish();
    }
}
