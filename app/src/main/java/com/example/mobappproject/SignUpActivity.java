package com.example.mobappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {
        Button btnSignUp, btn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_sign_up);

                btnSignUp = findViewById(R.id.btnSignUp);

                btnSignUp.setOnClickListener((v) -> {
                        Intent signIn = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(signIn);

                });
        }
        }