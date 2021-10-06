package com.example.mobappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AdminLogin extends AppCompatActivity {

    Button btnSignInAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        btnSignInAdmin = findViewById(R.id.btnSignInAdmin);

        btnSignInAdmin.setOnClickListener((v) -> {
            Intent A = new Intent(AdminLogin.this, AdminTrackingUpdate.class);
            startActivity(A);

        });

    }
}