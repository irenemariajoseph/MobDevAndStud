package com.example.mobappproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.appindexing.builders.StickerBuilder;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class PickupLocationPengirim extends AppCompatActivity {
    EditText etPlace;
    TextView textLongLat;
    Button btnPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_location_pengirim);

        etPlace = findViewById(R.id.etPlace);
        textLongLat = findViewById(R.id.textLongLat);
        btnPicker = findViewById(R.id.btnPicker);

        btnPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = etPlace.getText().toString();

                GeoLocation.getAddress(address, getApplicationContext(), new GeoHandler());
            }
        });
    }

    private class GeoHandler extends Handler {
        @Override
        public void handleMessage (Message msg) {
            String address;
            switch (msg.what) {
                case 1 :
                    Bundle bundle = msg.getData();
                    address = bundle.getString("address");
                    break;
                default:
                    address = null;
            }
            textLongLat.setText(address);
        }


        @Override
        public void publish(LogRecord logRecord) {

        }

        @Override
        public void flush() {

        }

        @Override
        public void close() throws SecurityException {

        }
    }
}
