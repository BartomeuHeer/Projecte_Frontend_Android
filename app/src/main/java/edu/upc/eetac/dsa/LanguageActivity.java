package edu.upc.eetac.dsa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    SharedPreferences sharedPref;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_language);
        Button catbut = (Button) findViewById(R.id.catalan_button);
        Button spabut = (Button) findViewById(R.id.spanish_button);
        Button engbut = (Button) findViewById(R.id.english_button);
        apiInterface = Api.getClient();

        catbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("ca");
                recreate();
                //updateLangUser("ca");
            }
        });
        spabut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("es");
                recreate();
                //updateLangUser("es");

            }
        });
        engbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("en");
                recreate();
                //updateLangUser("en");

            }
        });
    }

    public void setLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", language);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLanguage(language);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MenuActivity.class));
    }
/*
    private void updateLangUser(String lang) {
        //first we get the user
        SharedPreferences sharedPref = getSharedPreferences("LoginData", MODE_PRIVATE);
        String username = sharedPref.getString("username", "");
        User usuario = null;
        apiInterface.profile(new String(username)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("grup1", "" + response.code());
                String c = Integer.toString(response.code());
                if (response.isSuccessful()) {
                    User user = response.body();
                    usuario.setLanguage(lang);
                    usuario.setUsername(user.getUsername());
                    usuario.setPassword(user.getPassword());
                    usuario.setCoins(user.getCoins());
                    usuario.setEmail(user.getEmail());
                }
                Toast.makeText(getApplicationContext(), c + ": " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("grup1", "" + t.getMessage());
                Toast.makeText(getApplicationContext(), "Error22", Toast.LENGTH_SHORT).show();
            }
        });
        apiInterface.updateUser(new String(username),usuario).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("grup1", "" + response.code());
                String c = Integer.toString(response.code());
                Toast.makeText(getApplicationContext(), c + ": " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("grup1", "" + t.getMessage());
                Toast.makeText(getApplicationContext(),  "No pude conectarme al server :( ", Toast.LENGTH_SHORT).show();

            }
        });

    }*/

}
