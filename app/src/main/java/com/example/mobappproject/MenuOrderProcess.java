package com.example.mobappproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;

public class MenuOrderProcess extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
 Button btnBack;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu_order_process);



            Button btnPackageDetails =(Button)findViewById(R.id.btnPackageDetails);
            btnPackageDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),MenuPackageDetails.class);
                    startActivity(i);
                }
            });

            btnBack = findViewById(R.id.btnBack);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MenuOrderProcess.this, MainMenuActivity.class));
                }
            });

            Button buatconfirm =(Button)findViewById(R.id.buatconfirm);
            buatconfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),MenuOrderConfirmOrder.class);
                    startActivity(i);
                }
            });

            Button buatlocpengirim =(Button)findViewById(R.id.button4);
            buatlocpengirim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),PickupLocationPengirim.class);
                    startActivity(i);
                }
            });
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu_order_process);
//
//        MenuPackageDetails frag1 = new MenuPackageDetails();
//        frag1.setArguments(getIntent().getExtras());
//
////        PenguranganActivity frag2 = new PenguranganActivity();
////        frag2.setArguments(getIntent().getExtras());
//
////        PerkalianActivity frag3 = new PerkalianActivity();
////        frag3.setArguments(getIntent().getExtras());
//
//        FragmentManager fragManager = getSupportFragmentManager();


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}