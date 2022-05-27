package edu.upc.eetac.dsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

//import edu.upc.eetac.dsa.databinding.ActivityRegisterBinding;
import edu.upc.eetac.dsa.models.*;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {
    private TextView userText, emailText, repeatEmailText, passwordText, repeatPassText;
    private Button registerBtn;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userText = findViewById(R.id.user);
        emailText =  findViewById(R.id.mail);
        repeatEmailText =  findViewById(R.id.repeatemail);
        passwordText = findViewById(R.id.pass);
        repeatPassText = findViewById(R.id.repeatepass);
        registerBtn =  findViewById(R.id.registerButton);
        apiInterface = Api.getClient();
        validateUser();
        validateEmail();
        validatePass();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUser()==true && validateEmail()==true && validatePass()==true) {
                    register();
                }
                else
                    Toast.makeText(getApplicationContext(), "Incorrect format", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateUser() {
        String username = userText.getText().toString().trim();

        if (username.isEmpty()){
            userText.setError("Field can not be empty");
            return false;
        }

        else
            return true;
    }

    private boolean validateEmail() {
        String email = emailText.getText().toString().trim();
        String repMail = repeatEmailText.getText().toString().trim();

        if (email.isEmpty() || repMail.isEmpty()) {
            if (email.isEmpty()) {
                emailText.setError("Field can not be empty");
            }
            if (repMail.isEmpty()) {
                repeatEmailText.setError("Field can not be empty");
            }
            return false;
        }
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("Not valid email address");
            return false;
        }

        if (!PatternsCompat.EMAIL_ADDRESS.matcher(repMail).matches()){
            repeatEmailText.setError("Not valid email address");
            return false;
        }

        if (!repMail.equals(email)){
            repeatEmailText.setError("Email not match");
            return false;
        }
        else
            return true;
    }

    private boolean validatePass() {
        String pass = passwordText.getText().toString().trim();
        String repPass = repeatPassText.getText().toString().trim();

        if (pass.isEmpty() || repPass.isEmpty()) {
            if (pass.isEmpty()) {
                passwordText.setError("Field can not be empty");
            }
            if (repPass.isEmpty()) {
                repeatPassText.setError("Field can not be empty");
            }
            return false;
        }
        if (!repPass.equals(pass)){
            repeatPassText.setError("Password not match");
            return false;
        }
        else
            return true;
    }

    private void register() {

        String username = userText.getText().toString().trim();
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        apiInterface.register(new User(username,email,password)).enqueue(new Callback<User>() {
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