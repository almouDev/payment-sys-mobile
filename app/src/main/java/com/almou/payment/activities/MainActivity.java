package com.almou.payment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.almou.payment.R;
import com.almou.payment.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;
public class MainActivity extends AppCompatActivity {
    TextView textView;
    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarHome.toolbar);
        DrawerLayout drawerLayout=binding.mainDrawer;
        NavigationView navigationView=binding.navView;
        appBarConfiguration=new AppBarConfiguration.Builder(R.id.nav_home)
                .setOpenableLayout(drawerLayout)
                .build();
        NavController navController= Navigation.findNavController(this,R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);
//        Intent intent=getIntent();
//        String intentToken=intent.getStringExtra("accessToken").split("\\.")[1];
//        GsonBuilder gsonBuilder=new GsonBuilder();
//        Gson gson =gsonBuilder.create();
//        try {
//            String decodedJwt= new String(Base64.decode(intentToken,Base64.DEFAULT));
//            Map<String,String> accessToken=gson.fromJson(decodedJwt,Map.class);
//            textView.setText("Welcome "+accessToken.get("sub"));
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}