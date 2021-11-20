package com.example.mobappproject;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.mobappproject.MenuTracking.txtid_transaksi;
import static com.example.mobappproject.MenuTracking.txtnamapengirim;
import static com.example.mobappproject.MenuTracking.txtalamatpengirim;
import static com.example.mobappproject.MenuTracking.txttelp_peng;
import static com.example.mobappproject.MenuTracking.txtnama_penerima;
import static com.example.mobappproject.MenuTracking.txtalamat_penerima;
import static com.example.mobappproject.MenuTracking.txttelp_pen;
        //10
import static com.example.mobappproject.MenuTracking.txtnama_barang;
import static com.example.mobappproject.MenuTracking.txtkuantitas;
import static com.example.mobappproject.MenuTracking.txtunit_paket;
import static com.example.mobappproject.MenuTracking.txtfragile;
import static com.example.mobappproject.MenuTracking.txtasuransibarang;
import static com.example.mobappproject.MenuTracking.txtberat;
import static com.example.mobappproject.MenuTracking.txtjarak;
import static com.example.mobappproject.MenuTracking.txtharga;
import static com.example.mobappproject.MenuTracking.txttipe_pengambilan;
import static com.example.mobappproject.MenuTracking.txtstatus_paket;
        //7
import static com.example.mobappproject.MenuTracking.txttanggal_pickup;
import static com.example.mobappproject.MenuTracking.txttanggal_deliveredtopost;
import static com.example.mobappproject.MenuTracking.txttanggal_warehousetransit;
import static com.example.mobappproject.MenuTracking.txttanggal_acceptedbykurir;
import static com.example.mobappproject.MenuTracking.txttanggal_ongoing;
import static com.example.mobappproject.MenuTracking.txttanggal_arrived;
import static com.example.mobappproject.MenuTracking.txttanggal_failed;


public class TransactionDetails extends AppCompatActivity {
    TextView txtidDB, txtnamapengirimDB,txtalamatpengDB,txttelppengDB,
            txtnamapen,txtalamatpenDB,txttelppenDB;

    TextView namabarangDB, quantityDB,unitDB,fragileDB,
            InsuranceDB,weightDB,distanceDB, priceDB,typeDelDB,statusDB;

    TextView tgl_pickup, tgl_deltopos,tgl_warehouse,tgl_AccpetedbyKurir,
            tglOnGoing,tgl_arrived,tgl_Failed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);
        //7
        txtidDB = findViewById(R.id.txtidDB);
        txtidDB.setText(txtid_transaksi);
        txtnamapengirimDB = findViewById(R.id.txtnamapengirimDB);
        txtnamapengirimDB.setText(txtnamapengirim);
        txtalamatpengDB = findViewById(R.id.txtalamatpengDB);
        txtalamatpengDB.setText(txtalamatpengirim);
        txttelppengDB = findViewById(R.id.txttelppengDB);
        txttelppengDB.setText(txttelp_peng);
        txtnamapen = findViewById(R.id.txtnamapen);
        txtnamapen.setText(txtnama_penerima);
        txtalamatpenDB = findViewById(R.id.txtalamatpenDB);
        txtalamatpenDB.setText(txtalamat_penerima);
        txttelppenDB = findViewById(R.id.txttelppenDB);
        txttelppenDB.setText(txttelp_pen);
        //10
        namabarangDB = findViewById(R.id.namabarangDB);
        namabarangDB.setText(txtnama_barang);
        quantityDB = findViewById(R.id.quantityDB);
        quantityDB.setText(txtkuantitas);
        unitDB = findViewById(R.id.unitDB);
        unitDB.setText(txtunit_paket);
        //
        fragileDB = findViewById(R.id.fragileDB);
        fragileDB.setText(txtfragile);

        InsuranceDB = findViewById(R.id.InsuranceDB);
        InsuranceDB.setText(txtasuransibarang);
        //
        weightDB = findViewById(R.id.weightDB);
        weightDB.setText(txtberat);
        distanceDB = findViewById(R.id.distanceDB);
        distanceDB.setText(txtjarak);
        priceDB = findViewById(R.id.priceDB);
        priceDB.setText(txtharga);
        typeDelDB = findViewById(R.id.typeDelDB);
        typeDelDB.setText(txttipe_pengambilan);
        statusDB = findViewById(R.id.statusDB);
        statusDB.setText(txtstatus_paket);
        //7
        tgl_pickup = findViewById(R.id.tgl_pickup);
        tgl_pickup.setText(txttanggal_pickup);

        tgl_deltopos = findViewById(R.id.tgl_deltopos);
        tgl_deltopos.setText(txttanggal_deliveredtopost);

        tgl_warehouse = findViewById(R.id.tgl_warehouse);
        tgl_warehouse.setText(txttanggal_warehousetransit);

        tgl_AccpetedbyKurir = findViewById(R.id.tgl_AccpetedbyKurir);
        tgl_AccpetedbyKurir.setText(txttanggal_acceptedbykurir);

        tglOnGoing = findViewById(R.id.tglOnGoing);
        tglOnGoing.setText(txttanggal_ongoing);

        tgl_arrived = findViewById(R.id.tgl_arrived);
        tgl_arrived.setText(txttanggal_arrived);

        tgl_Failed = findViewById(R.id.tgl_Failed);
        tgl_Failed.setText(txttanggal_failed);






        Button btnDoneTrack =(Button)findViewById(R.id.btnDoneTrack);
        btnDoneTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainMenuActivity.class);
                startActivity(i);
            }
        });

    }
}