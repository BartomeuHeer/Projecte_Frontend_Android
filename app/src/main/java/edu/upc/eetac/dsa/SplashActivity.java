package edu.upc.eetac.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences sharedPref = getSharedPreferences("LoginData", MODE_PRIVATE);
        String username = sharedPref.getString("username", "");
        String password = sharedPref.getString("password", "");
        Intent intent;
        if(username.isEmpty() && password.isEmpty())
            intent = new Intent(this, LandPageActivity.class);
        else
            intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}