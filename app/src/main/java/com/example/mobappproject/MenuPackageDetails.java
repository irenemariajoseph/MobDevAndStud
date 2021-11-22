package com.example.mobappproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.mobappproject.FormAddress.id_pengirimDB;
import static com.example.mobappproject.FormAddressPenerima.id_penerimaDB;
import static com.example.mobappproject.SignInActivity.userID;


public class MenuPackageDetails extends AppCompatActivity {
    public static String txtHarga, id_transaksi;


    private String txtNamaBarang,txtQuantity,txtWeight,txtJarak;

    private String txtdeliveryType, txtspinUnit;
    private String fnl_berat, fnl_jarak;


    private Spinner deliveryType, spinUnit;
    private Button btnDone,btnMaps;
    private EditText edtNamaBarang,edtQuantity,edtWeight,edtJarak;
    private CheckBox cbAsuransi,cbFragile;

    private String urlPackageDetails = "http://192.168.1.78/mobappbackend/router/inputpackagedetail.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_package_details);

        edtNamaBarang = (EditText)findViewById(R.id.edtNamaBarang);
        edtQuantity = (EditText)findViewById(R.id.edtQuantity);
        edtWeight = (EditText)findViewById(R.id.edtWeight);
        edtJarak = (EditText)findViewById(R.id.edtJarak);
        deliveryType = (Spinner)findViewById(R.id.deliveryType);
        spinUnit = (Spinner) findViewById(R.id.spinUnit);
        cbAsuransi = (CheckBox)findViewById(R.id.cbAsuransi);
        cbFragile = (CheckBox) findViewById(R.id.cbFragile);


        btnDone = (Button)findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                txtWeight = edtWeight.getText().toString();
                txtJarak = edtJarak.getText().toString();

                int itungberat = Integer.parseInt(txtWeight);
                int itungjarak = Integer.parseInt(txtJarak);

                int harga = 10000* itungberat * itungjarak;

                Integer converthasil = new Integer(harga);
                txtHarga= converthasil.toString();



                Integer berattt = new Integer(itungberat);
                fnl_berat = berattt.toString();

                Integer jarakkk = new Integer(itungjarak);
                fnl_jarak = jarakkk.toString();



                txtNamaBarang = edtNamaBarang.getText().toString();
                txtQuantity = edtQuantity.getText().toString();
                txtdeliveryType = deliveryType.getSelectedItem().toString();
                txtspinUnit = spinUnit.getSelectedItem().toString();

                String txtAsuransi ;
                if(cbAsuransi.isChecked() ){
                    txtAsuransi = "1";
                }else {
                    txtAsuransi = "0";
                }
                String finalTxtAsuransi = txtAsuransi;


                String txtFragile;
                if(cbFragile.isChecked() ){
                    txtFragile = "1";
                }else {
                    txtFragile = "0";
                }
                String finaltxtFragile = txtFragile;




                //Toast.makeText(getActivity().getApplicationContext(),noplat + carmodel + caryear + carcolor + norangka + nomesin ,Toast.LENGTH_LONG).show();
                AlertDialog.Builder ad = new AlertDialog.Builder(MenuPackageDetails.this);
                ad.setTitle("Konfirmasi Package Details");
                ad.setMessage("Apakah data yang anda sudah isi benar?");


                ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestQueue queue = Volley.newRequestQueue(MenuPackageDetails.this);
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlPackageDetails, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {
                                    System.out.print(response);
                                    JSONObject jObj = new JSONObject(response);

                                    int sukses = jObj.getInt("code");
                                    if (sukses == 200) {
                                        id_transaksi = jObj.getString("data");
                                        Toast.makeText(MenuPackageDetails.this.getApplicationContext(), "Data package details berhasil ditambahkan", Toast.LENGTH_SHORT).show();

                                        Intent i = new Intent(getApplicationContext(),MenuOrderProcess.class);
                                        Toast.makeText(MenuPackageDetails.this.getApplicationContext(), "Klik Informasi Pengiriman Lengkap apabila sudah terisi seluruhnya", Toast.LENGTH_LONG).show();
                                        startActivity(i);
                                        finish();


//                                        MyCarPage fragmycar = new MyCarPage();
//                                        fragmycar.setArguments(getActivity().getIntent().getExtras());
//                                        Bundle bundle = new Bundle();
//                                        bundle.putString("username",username);
//                                        fragmycar.setArguments(bundle);
//                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,fragmycar).commit();
                                    } else {
                                        Toast.makeText(MenuPackageDetails.this.getApplicationContext(), "Data package details gagal ditambahkan", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(MenuPackageDetails.this.getApplicationContext(), "Periksa pengisian data sesuai keterangan", Toast.LENGTH_LONG).show();

                                    }
                                }
                                catch (Exception ex) {
                                    Log.e("Error", ex.toString());
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error", error.getMessage());
                                Toast.makeText(MenuPackageDetails.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            } }) {



                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();
                                params.put("nama_barang", txtNamaBarang);
                                params.put("kuantitas", txtQuantity);
                                params.put("unit_paket", txtspinUnit);
                                params.put("berat",fnl_berat );
                                params.put("jarak", fnl_jarak);
                                params.put("fragile", finaltxtFragile);
                                params.put("asuransibarang", finalTxtAsuransi);
                                params.put("tipe_pengambilan", txtdeliveryType);
                                params.put("harga", txtHarga);
                                params.put("id_user", userID);
                                params.put("id_penerima", id_penerimaDB);
                                params.put("id_pengirim", id_pengirimDB);


                                return params;
                            }

                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("Content-Type", "application/x-www-form-urlencoded");
                                return params;
                            }
                        };
                        queue.getCache().clear();
                        queue.add(stringRequest);
                    }


                });
                ad.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ad.show();
            }
        });


        btnMaps = (Button)findViewById(R.id.btnMaps);
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.co.in/maps?q=" + "Jakarta Pusat";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(i);
            }
        });
    }
    }