package com.almou.payment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.almou.payment.R;
import com.almou.payment.utils.MainUtilities;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login_btn;
    private EditText username;
    private EditText password;
    private Button signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_btn = findViewById(R.id.signin_btn);
        username = findViewById(R.id.username);
        signup_btn = findViewById(R.id.register_now_btn);
        password = findViewById(R.id.password_label);
        signup_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signin_btn:
                String password_text=password.getText().toString();
                String login=username.getText().toString();
                if (!MainUtilities.validatePhoneNumber(login)) {
                    MainUtilities.showAlert(LoginActivity.this, "Please provide a valid phone number");
                    return;
                }
                Thread thread=new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                GsonBuilder gsonBuilder=new GsonBuilder();
                                Gson gson= gsonBuilder.create();
                                String data="username="+login+"&password="+password_text;
                                try {
                                    URL url=new URL(MainUtilities.serverHost+"/login");
                                    HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                                    urlConnection.setChunkedStreamingMode(0);
                                    urlConnection.setDoInput(true);
                                    urlConnection.setRequestMethod("POST");
                                    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                                    byte[] input=data.getBytes("UTF-8");
                                    urlConnection.setRequestProperty("Content-Length", Integer.toString(input.length));
                                    DataOutputStream writer=new DataOutputStream(urlConnection.getOutputStream());
                                    writer.write(input);
                                    InputStream inputStream=urlConnection.getInputStream();
                                    Scanner scanner=new Scanner(inputStream);
                                    String token=scanner.nextLine();
                                    Map<String,String>  AccessToken=gson.fromJson(token,Map.class);
                                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                    intent.putExtra("accessToken",AccessToken.get("access_token"));
                                    startActivity(intent);
                                    finish();
                                }catch (IOException e) {
                                    Log.e("httpError",e.getMessage());
                                }
                            }
                        });
                thread.start();
            case R.id.register_now_btn:
                Log.i("It","It works correctly");
                Intent intent=new Intent(this,SignupActivity.class);
                startActivity(intent);
        }
    }
}