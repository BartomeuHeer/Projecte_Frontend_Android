package edu.upc.eetac.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import edu.upc.eetac.dsa.models.LoginParam;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
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
        String username = userNameText.getText().toString().trim();
        String pass = passText.getText().toString().trim();
        /*String BASE_URL = "http://10.0.2.2:8080/";
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<User> call = api.login(new LoginParam(username,pass));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });*/




        //Toast.makeText(getApplicationContext(), "Loged In_1", Toast.LENGTH_SHORT).show();


       /* Api.getClient().login(new LoginParam(username,pass)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful())
                    Toast.makeText(getApplicationContext(), "Loged In_2", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("response", t.getStackTrace().toString());
                Toast.makeText(getApplicationContext(), "Error22", Toast.LENGTH_SHORT).show();

            }
        });*/

        apiInterface.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful())
                    Toast.makeText(getApplicationContext(), "Loged In", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "error1", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error22", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
