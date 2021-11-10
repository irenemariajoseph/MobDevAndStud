package com.example.mobappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.badge.BadgeUtils;

public class MenuUserSettings extends AppCompatActivity {
        Button btnSave;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user_settings);

        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        startActivity(new Intent(MenuUserSettings.this, MainMenuActivity.class));
                }
        });
        }
        }