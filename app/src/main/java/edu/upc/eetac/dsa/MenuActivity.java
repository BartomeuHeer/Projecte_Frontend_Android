package edu.upc.eetac.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private Button profile,stats,shop,logout, issue, languages;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        profile = (Button) findViewById(R.id.button_profile);
        stats = (Button) findViewById(R.id.button_stats);
        shop = (Button) findViewById(R.id.button_shop);
        logout = (Button) findViewById(R.id.button_logout);
        issue = (Button) findViewById(R.id.btnIssues);
        languages = (Button) findViewById(R.id.languages_button);
        apiInterface = Api.getClient();

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
            }

        });

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatsActivity();
            }

        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShopActivity();
            }

        });

        issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIssueActivity();
            }
        });
        languages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLanguagesActivity();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

    }

    private void openProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void openStatsActivity(){
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }
    private void openShopActivity(){
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }
    private void openIssueActivity(){
        Intent intent = new Intent(this, IssuesActivity.class);
        startActivity(intent);
    }
    private void openLanguagesActivity(){
        Intent intent = new Intent(this, LanguageActivity.class);
        startActivity(intent);
    }
    private void logOut(){
        SharedPreferences myPrefs = getSharedPreferences("LoginData",
                MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(MenuActivity.this,
                LanguageActivity.class);
        finish();
    }


}