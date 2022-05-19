package edu.upc.eetac.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    private TextView userNameText, passText;
    private Button logInBtn;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameText = (TextView) findViewById(R.id.username);
        passText = (TextView) findViewById(R.id.password);
        logInBtn = (Button) findViewById(R.id.accept);
        apiInterface = Api.getClient();
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {

        String un = userNameText.getText().toString().trim();
        String pass = passText.getText().toString().trim();

        apiInterface.login(new LogInParams(un,pass)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String c = Integer.toString(response.code());
                Toast.makeText(getApplicationContext(), c + ": " + response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error22", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
