package com.example.mobappproject;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< Updated upstream
import android.os.Bundle;

public class MenuFormAddress extends AppCompatActivity {

=======
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuFormAddress extends AppCompatActivity {

    Button btnBack;

>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_form_address);
<<<<<<< Updated upstream
=======

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuFormAddress.this, MenuOrderProcess.class));
            }
        });
>>>>>>> Stashed changes
    }
}