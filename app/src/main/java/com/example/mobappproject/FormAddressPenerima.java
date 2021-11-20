package com.example.mobappproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class FormAddressPenerima extends AppCompatActivity {
    public static String id_penerimaDB;
    private static final String TAG_ID = "data";


    private String txtnamaPen,txtnoTelpPen,txtalamatLengkapPen,txtkotaPen, txtkodePosPen,txtNotesPen;
    private String txtSpinProvinsi;



    private Button btnFormPen;

    private EditText namaPenerima,noTelpPenerima,alamatLengkapPenerima,kotaPenerima, kodePosPenerima,NotesPen;
    private Spinner spinProvPen;

    private String urlPackageDetails = "http://192.168.1.78/mobappbackend/router/inputalamatpen.php";

//    private JSONObject data;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_form_address_penerima);

            namaPenerima = (EditText)findViewById(R.id.namaPenerima);
            noTelpPenerima = (EditText)findViewById(R.id.noTelpPenerima);
            alamatLengkapPenerima = (EditText)findViewById(R.id.alamatLengkapPenerima);
            kotaPenerima = (EditText)findViewById(R.id.kotaPenerima);
            kodePosPenerima = (EditText)findViewById(R.id.kodePosPenerima);
            NotesPen=(EditText)findViewById(R.id.NotesPen);
            spinProvPen = (Spinner) findViewById(R.id.spinProvPen);


            btnFormPen = (Button)findViewById(R.id.btnFormPen);
            btnFormPen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    txtnamaPen = namaPenerima.getText().toString();
                    txtnoTelpPen = noTelpPenerima.getText().toString();
                    txtalamatLengkapPen = alamatLengkapPenerima.getText().toString();
                    txtkotaPen = kotaPenerima.getText().toString();
                    txtkodePosPen = kodePosPenerima.getText().toString();
                    txtNotesPen = NotesPen.getText().toString();
                    txtSpinProvinsi = spinProvPen.getSelectedItem().toString();






                    //Toast.makeText(getActivity().getApplicationContext(),noplat + carmodel + caryear + carcolor + norangka + nomesin ,Toast.LENGTH_LONG).show();
                    AlertDialog.Builder ad = new AlertDialog.Builder(FormAddressPenerima.this);
                    ad.setTitle("Konfirmasi Alamat Penerima");
                    ad.setMessage("Apakah data yang anda sudah isi benar?");


                    ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            RequestQueue queue = Volley.newRequestQueue(FormAddressPenerima.this);
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlPackageDetails, new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jObj = new JSONObject(response);
                                        int sukses = jObj.getInt("code");
                                        if (sukses == 200) {
                                            Toast.makeText(FormAddressPenerima.this.getApplicationContext(), "Data alamat penerima berhasil ditambahkan", Toast.LENGTH_SHORT).show();
//                                            data = jObj.getJSONObject("data"); ga pake ini karena jobj udah ada data yg diperlukan

                                            id_penerimaDB = jObj.getString("data");
                                            Intent i = new Intent(getApplicationContext(),MenuPackageDetails.class);
                                            startActivity(i);
                                            finish();


//                                        MyCarPage fragmycar = new MyCarPage();
//                                        fragmycar.setArguments(getActivity().getIntent().getExtras());
//                                        Bundle bundle = new Bundle();
//                                        bundle.putString("username",username);
//                                        fragmycar.setArguments(bundle);
//                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,fragmycar).commit();
                                        } else {
                                            Toast.makeText(FormAddressPenerima.this.getApplicationContext(), "Data alamat penerima gagal ditambahkan", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(FormAddressPenerima.this.getApplicationContext(), "Periksa pengisian data sesuai keterangan", Toast.LENGTH_LONG).show();
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
                                    Toast.makeText(FormAddressPenerima.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                } }) {
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("nama", txtnamaPen);
                                    params.put("telpon", txtnoTelpPen);
                                    params.put("alamat_lengkap", txtalamatLengkapPen);
                                    params.put("provinsi",txtSpinProvinsi );
                                    params.put("kota", txtkotaPen);
                                    params.put("kodepos", txtkodePosPen);
                                    params.put("notes_tambahan", txtNotesPen);



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


        }
    }