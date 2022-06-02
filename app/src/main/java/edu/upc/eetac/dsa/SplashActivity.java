package edu.upc.eetac.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences sharedPref = getSharedPreferences("LoginData", MODE_PRIVATE);
        Boolean isLogged = sharedPref.getBoolean("isLogged", false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(isLogged)
                    intent = new Intent(SplashActivity.this, LandPageActivity.class);
                else
                    intent = new Intent(SplashActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }
}