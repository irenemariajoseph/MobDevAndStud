package com.example.mobappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.mobappproject.MenuPackageDetails.txtHarga;
import static com.example.mobappproject.MenuOrderProcess.namapengirim;
import static com.example.mobappproject.MenuOrderProcess.alamatpengirim;
import static com.example.mobappproject.MenuOrderProcess.namapenerima;
import static com.example.mobappproject.MenuOrderProcess.alamatpenerima;
import static com.example.mobappproject.MenuOrderProcess.namabarang;
import static com.example.mobappproject.MenuOrderProcess.txtberat;
import static com.example.mobappproject.MenuOrderProcess.txtjarak;


public class MenuOrderConfirmOrder extends AppCompatActivity {
    TextView txtTotalPrice, txtjarakganti,txtbarang,txtItemWeight, txtalamatpenerima,txtnamapenerima,txtnamapengirim,txtpengirimAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_order_confirm_order);

        txtTotalPrice = findViewById(R.id.txtTotalPrices);
        txtTotalPrice.setText(txtHarga);

        txtjarakganti = findViewById(R.id.txtjarakganti);
        txtjarakganti.setText(txtjarak);

        txtbarang = findViewById(R.id.txtbarang);
        txtbarang.setText(namabarang);

        txtItemWeight = findViewById(R.id.txtItemWeight);
        txtItemWeight.setText(txtberat);

        txtnamapengirim = findViewById(R.id.txtnamapengirim);
        txtnamapengirim.setText(namapengirim);

        txtpengirimAddress = findViewById(R.id.txtpengirimAddress);
        txtpengirimAddress.setText(alamatpengirim);

        txtnamapenerima = findViewById(R.id.txtnamapenerima);
        txtnamapenerima.setText(namapenerima);

        txtalamatpenerima = findViewById(R.id.txtalamatpenerima);
        txtalamatpenerima.setText(alamatpenerima);





        Button btnPayment =(Button)findViewById(R.id.btnPayment);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MenuOrderPayment.class);
                startActivity(i);
            }
        });
    }
}