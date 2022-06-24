package edu.upc.eetac.dsa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;


import edu.upc.eetac.dsa.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private TextView userNameText, passText, mailText;
    private Button update_username_btn, update_password_btn, update_email_button;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Intent myIntent = getIntent();
        userNameText = (TextView) findViewById(R.id.edit_username);
        passText = (TextView) findViewById(R.id.edit_password);
        mailText = (TextView) findViewById(R.id.edit_email);

        update_username_btn = (Button) findViewById(R.id.update_username_btn);
        update_password_btn = (Button) findViewById(R.id.update_password_button);
        update_email_button = (Button) findViewById(R.id.update_email_button);

        String usernamebox = myIntent.getStringExtra("username");
        String passwordbox = myIntent.getStringExtra("password");
        String emailbox = myIntent.getStringExtra("email");

        validateUser();
        validatePass();
        validateEmail();

        apiInterface = Api.getClient();
        User user = profile();

        update_username_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUser()) {
                    updateUsername();
                }
                else
                    Toast.makeText(getApplicationContext(), "Incorrect format", Toast.LENGTH_SHORT).show();
            }
        });
        update_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatePass()) {
                    updatePassword();
                }
                else
                    Toast.makeText(getApplicationContext(), "Incorrect format", Toast.LENGTH_SHORT).show();
            }
        });
        update_email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEmail()) {
                    updateEmail();
                }
                else
                    Toast.makeText(getApplicationContext(), "Incorrect format", Toast.LENGTH_SHORT).show();
            }
        });

    }

   private User profile() {

        SharedPreferences sharedPref = getSharedPreferences("LoginData", MODE_PRIVATE);
        SharedPreferences.Editor myEdit;
        String username = sharedPref.getString("username", "");
        User user = new User();
        apiInterface.profile(new String(username)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("grup1", "" + response.code());
                String c = Integer.toString(response.code());
                if (response.isSuccessful()) {
                    user.setUsername(response.body().getUsername());
                    user.setEmail(response.body().getEmail());
                    user.setCoins(response.body().getCoins());
                    user.setPassword(response.body().getPassword());
                    user.setLanguage(response.body().getLanguage());
                    pintamelo(user);
                    saveEmailToSharedPref(user);

                }
                Toast.makeText(getApplicationContext(), c + ": " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("grup1", "" + t.getMessage());
                Toast.makeText(getApplicationContext(), "Error22", Toast.LENGTH_SHORT).show();
            }
        });
        return user;
    }



    private void updateUsername() {
        String new_username = userNameText.getText().toString();
        SharedPreferences sharedPref = getSharedPreferences("LoginData", MODE_PRIVATE);
        String username = sharedPref.getString("username", "");

            if (!username.equals(new_username)) {
                apiInterface.updateUserUsername(new String(username), new_username).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("grup1", "" + response.code());
                        String c = Integer.toString(response.code());
                        Toast.makeText(getApplicationContext(), c + ": " + response.message(), Toast.LENGTH_SHORT).show();
                        saveUsernameToSharedPref(new_username);

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("grup1", "" + t.getMessage());
                        Toast.makeText(getApplicationContext(), "No pude conectarme al server :( ", Toast.LENGTH_SHORT).show();

                    }
                });
            }
            else{
                Toast.makeText(getApplicationContext(),   "New username is the same as the old one!" , Toast.LENGTH_SHORT).show();
            }

    }
    private void updatePassword() {
        String new_password = passText.getText().toString();
        SharedPreferences sharedPref = getSharedPreferences("LoginData", MODE_PRIVATE);
        String password = sharedPref.getString("password", "");
        String username = sharedPref.getString("username", "");


            if (!password.equals(new_password)) {
                apiInterface.updateUserPassword(new String(username), new_password).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("grup1", "" + response.code());
                        String c = Integer.toString(response.code());
                        Toast.makeText(getApplicationContext(), c + ": " + response.message(), Toast.LENGTH_SHORT).show();
                        savePasswordToSharedPref(new_password);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("grup1", "" + t.getMessage());
                        Toast.makeText(getApplicationContext(), "No pude conectarme al server :( ", Toast.LENGTH_SHORT).show();

                    }
                });
            }
            else{
                Toast.makeText(getApplicationContext(),   "New password is the same as the old one!" , Toast.LENGTH_SHORT).show();
            }
        }

    private void updateEmail() {
        String new_email = mailText.getText().toString();
        SharedPreferences sharedPref = getSharedPreferences("LoginData", MODE_PRIVATE);
        String email = sharedPref.getString("email", "");
        String username = sharedPref.getString("username", "");
            if (!email.equals(new_email)) {
                apiInterface.updateUserEmail(new String(username), new_email).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("grup1", "" + response.code());
                        String c = Integer.toString(response.code());
                        Toast.makeText(getApplicationContext(), c + ": " + response.message(), Toast.LENGTH_SHORT).show();
                        saveEmailToSharedPref2(new_email);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("grup1", "" + t.getMessage());
                        Toast.makeText(getApplicationContext(), "No pude conectarme al server :( ", Toast.LENGTH_SHORT).show();

                    }
                });
            }
            else{
                Toast.makeText(getApplicationContext(),   "New email is the same as the old one!" , Toast.LENGTH_SHORT).show();
            }
    }
    private boolean validateUser() {
        String username = userNameText.getText().toString().trim();

        if (username.isEmpty()){
            userNameText.setError("Field can not be empty");
            return false;
        }
        else
            return true;
    }

    private boolean validateEmail() {
        String email = mailText.getText().toString().trim();

        if (email.isEmpty()) {
            mailText.setError("Field can not be empty");
            return false;
            }

        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            mailText.setError("Not valid email address");
            return false;
        }
        else
            return true;
    }

    private boolean validatePass() {
        String pass = passText.getText().toString().trim();

        if (pass.isEmpty()) {
            passText.setError("Field can not be empty");
            return false;
        }
        else
            return true;
    }
    private void saveEmailToSharedPref (User user){
        SharedPreferences sharedPref = getSharedPreferences("LoginData", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPref.edit();
        myEdit.putString("email",user.getEmail());
        myEdit.apply();
    }
    private void saveEmailToSharedPref2 (String email){
        SharedPreferences sharedPref = getSharedPreferences("LoginData", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPref.edit();
        myEdit.putString("email",email);
        myEdit.apply();
    }
    private void saveUsernameToSharedPref (String username){
        SharedPreferences sharedPref = getSharedPreferences("LoginData", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPref.edit();
        myEdit.putString("username",username);
        myEdit.apply();
    }
    private void savePasswordToSharedPref (String password){
        SharedPreferences sharedPref = getSharedPreferences("LoginData", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPref.edit();
        myEdit.putString("password",password);
        myEdit.apply();
    }
    private void pintamelo(User user) {
        userNameText.setText(user.getUsername());
        passText.setText(user.getPassword());
        mailText.setText(user.getEmail());
    }
}
