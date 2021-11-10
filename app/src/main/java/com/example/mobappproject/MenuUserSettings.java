package com.example.mobappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
<<<<<<< Updated upstream
=======

import com.google.android.material.badge.BadgeUtils;
>>>>>>> Stashed changes

public class MenuUserSettings extends AppCompatActivity {
        Button btnSave;

        Button btnBack;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user_settings);

<<<<<<< Updated upstream
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        startActivity(new Intent(MenuUserSettings.this, MenuUser.class));
=======
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        startActivity(new Intent(MenuUserSettings.this, MainMenuActivity.class));
>>>>>>> Stashed changes
                }
        });
        }
        }