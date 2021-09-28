package com.example.mobappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenuActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        navigationView = findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.framefragment, new MenuOrderService()).commit();
        navigationView.setSelectedItemId(R.id.nav_pesan);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.nav_pesan:
                        fragment = new MenuOrderService();
                        break;


                    case R.id.nav_tracking:
                        fragment = new MenuTracking();
                        break;

                    case R.id.nav_user:
                        fragment = new MenuUser();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framefragment, fragment).commit();
                return true;

            }
        });

    }
}