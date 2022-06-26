package edu.upc.eetac.dsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity {

    private Button profile,stats,shop, play, logout, issue, languages;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_menu);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case(R.id.nav_account):
                        openProfileActivity();
                        break;
                    case(R.id.nav_settings):
                        openLanguagesActivity();
                        break;
                    case(R.id.nav_logout):
                        logOut();
                        break;
                    case(R.id.nav_issues):
                        openIssueActivity();
                        break;
                    case(R.id.nav_forum):
                        openForumActivity();
                        break;
                }
                return true;
            }
        });

        stats = (Button) findViewById(R.id.button_stats);
        shop = (Button) findViewById(R.id.button_shop);
        play = (Button) findViewById(R.id.button_play);

        apiInterface = Api.getClient();

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
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlayActivity();
            }

        });
    }

    private void openPlayActivity() {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
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
    private void openForumActivity(){
        Intent intent = new Intent(this, ForumActivity.class);
        startActivity(intent);
    }
    private void logOut(){
        SharedPreferences myPrefs = getSharedPreferences("LoginData",
                MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(MenuActivity.this,
                LandPageActivity.class);
        startActivity(intent);
        finish();
    }

}