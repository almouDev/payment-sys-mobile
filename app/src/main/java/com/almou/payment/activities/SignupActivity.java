package com.almou.payment.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.almou.payment.R;
import com.almou.payment.beans.Client;
import com.almou.payment.utils.MainUtilities;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignupActivity extends AppCompatActivity {
    private EditText phone;
    private EditText email;
    private EditText lastName;
    private EditText firstName;
    private EditText address;
    private EditText password;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar  bar=findViewById(R.id.signup_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the back button
        getSupportActionBar().setDisplayShowHomeEnabled(true); // Show the back button as an icon
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_back);

        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed(); // When the back button is clicked, call the onBackPressed method
            }
        });
        phone=findViewById(R.id.username);
        email=findViewById(R.id.email);
        lastName=findViewById(R.id.lastName);
        firstName=findViewById(R.id.fistName);
        address=findViewById(R.id.address);
        password=findViewById(R.id.password_label);
        button=findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MainUtilities.validatePhoneNumber(phone.getText().toString())){
                        MainUtilities.showAlert(SignupActivity.this,"Invalid phone number");
                        return;
                }
                if (!(password.getText().toString().length() >8)){
                        MainUtilities.showAlert(SignupActivity.this,"The password must contain at least 8 characters");
                        return;
                }

                GsonBuilder builder=new GsonBuilder();
                Gson gson=builder.create();
                Client client=new Client();
                client.setPhone(phone.getText().toString());
                client.setEmail(email.getText().toString());
                client.setAddress(address.getText().toString());
                client.setLastName(lastName.getText().toString());
                client.setFirstName(firstName.getText().toString());
                client.setPassword(password.getText().toString());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OkHttpClient httpClient=new OkHttpClient();
                            RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),gson.toJson(client));
                            Request request=new Request.Builder()
                                    .url(MainUtilities.serverHost+"/signup")
                                    .post(requestBody)
                                    .build();
                            Response response=httpClient.newCall(request).execute();
                            Log.i("response",response.body().string());
                        }catch (IOException exception){
                            MainUtilities.showAlert(SignupActivity.this,"An unexpected has occurred ,please try again ");
                        }

                    }
                }).start();
            }
        });
    }
}