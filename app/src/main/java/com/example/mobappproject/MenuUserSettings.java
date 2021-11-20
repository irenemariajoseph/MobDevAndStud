package com.example.mobappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuUserSettings extends AppCompatActivity {
        Button btnChangeemail,btnchangepass;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_menu_user_settings);

                btnChangeemail = findViewById(R.id.btnChangeemail);

                btnChangeemail.setOnClickListener((v) -> {
                        Intent A = new Intent(MenuUserSettings.this, ChangeEmail.class);
                        startActivity(A);

                });

                btnchangepass = findViewById(R.id.btnchangepass);

                btnchangepass.setOnClickListener((v) -> {
                        Intent A = new Intent(MenuUserSettings.this, ChangePassword.class);
                        startActivity(A);

                });


//        btnchangepass = findViewById(R.id.btnchangepass);



//        Button maugantiemail =(Button)findViewById(R.id.btnChangeemail);
//        maugantiemail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                        Intent i = new Intent(getApplicationContext(),ChangeEmail.class);
//                        startActivity(i);
//                }
//        });

//        Button maugantipassword =(Button)findViewById(R.id.btnchangepass);
//        maugantipassword.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                        Intent i = new Intent(getApplicationContext(),ChangePassword.class);
//                        startActivity(i);
//                }
//        });


//
//        btnChangeemail.setOnClickListener((v) -> {
//                Intent i = new Intent(MenuUserSettings.this, ChangeEmail.class);
//                startActivity(i);
//
//        });



        }
}
