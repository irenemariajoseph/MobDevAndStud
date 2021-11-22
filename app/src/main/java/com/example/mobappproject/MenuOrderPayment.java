package com.example.mobappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import static com.example.mobappproject.MenuPackageDetails.txtHarga;
public class MenuOrderPayment extends AppCompatActivity {
    TextView txtTotalPrice;
    Button btnTransactionDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_order_payment);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtTotalPrice.setText(txtHarga);


        Button btnTransactionDone =(Button)findViewById(R.id.btnTransactionDone);
        btnTransactionDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainMenuActivity.class);
                startActivity(i);
            }
        });



    }
}