package edu.upc.eetac.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.upc.eetac.dsa.models.Issue;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import java.util.Properties;




public class IssuesActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private EditText dateText,messageText;
    SharedPreferences shp;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        apiInterface = Api.getClient();
        dateText = (EditText) findViewById(R.id.date);
        messageText = (EditText) findViewById(R.id.evMessage);
        send = (Button) findViewById(R.id.btnSendIssue);
        shp = getSharedPreferences("LoginData",MODE_PRIVATE);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //makeCall();
                final String username = "eetac.dsa.grup1@gmail.com";
                final String password = "Dsa_grup1_binding";
                String date = dateText.getText().toString().trim();
                String message = messageText.getText().toString().trim();
                String informer = shp.getString("username", "");
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put ("mail.smtp.starttls.enable","true");
                props.put ("mail.smtp.host", "smtp.gmail.com");
                props.put ( "mail.smtp.port", "587");
                Session session = Session.getInstance(props, new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(username,password);

                    }
                });
                try{
                    Message message1 = new MimeMessage(session);
                    message1.setFrom(new InternetAddress(username));
                    String email = "claudia.garcia.gil@estudiantat.upc.edu";
                    message1.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                    message1.setSubject("Issue open from The Binding of Gilbert");
                    message1.setText(message);
                    Transport.send(message1);
                    Toast.makeText(getApplicationContext(), "Email send successfully", Toast.LENGTH_LONG).show();

                } catch (MessagingException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void makeCall(){
        String date = dateText.getText().toString().trim();
        String message = messageText.getText().toString().trim();
        String informer = shp.getString("username", "");

        apiInterface.sendIssue(new Issue(date,informer,message)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                    Toast.makeText(IssuesActivity.this, "Issue sent", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(IssuesActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}