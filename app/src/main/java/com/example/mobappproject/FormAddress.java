package com.example.mobappproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.textservice.SpellCheckerSession;
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

public class FormAddress extends AppCompatActivity {
    public static String id_pengirimDB;
    private static final String TAG_ID = "data";

    private String txtnamaPengirim,txtnoTelpPengirim,txtalamatLengkapPengirim,txtkotaPeng, txtkodePosPeng,txtNotesPeng;
    private String txtSpinProvinsi;



    private Button btnAlamatPeng;

    private EditText namaPengirim,noTelpPengirim,alamatLengkapPengirim,kotaPeng, kodePosPeng,NotesPeng;
    private Spinner spinProvinsi;

    private String urlAddressPeng = "http://192.168.1.9/mobappbackend/router/inputalamatpeng.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_address);

        namaPengirim = (EditText)findViewById(R.id.namaPengirim);
        noTelpPengirim = (EditText)findViewById(R.id.noTelpPengirim);
        alamatLengkapPengirim = (EditText)findViewById(R.id.alamatLengkapPengirim);
        kotaPeng = (EditText)findViewById(R.id.kotaPeng);
        kodePosPeng = (EditText)findViewById(R.id.kodePosPeng);
        NotesPeng=(EditText)findViewById(R.id.NotesPeng);
        spinProvinsi = (Spinner) findViewById(R.id.spinProvinsi);



        btnAlamatPeng = (Button)findViewById(R.id.btnAlamatPeng);
        btnAlamatPeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtnamaPengirim = namaPengirim.getText().toString();
                txtnoTelpPengirim = noTelpPengirim.getText().toString();
                txtalamatLengkapPengirim = alamatLengkapPengirim.getText().toString();
                txtkotaPeng = kotaPeng.getText().toString();
                txtkodePosPeng = kodePosPeng.getText().toString();
                txtNotesPeng = NotesPeng.getText().toString();
                txtSpinProvinsi = spinProvinsi.getSelectedItem().toString();




                //Toast.makeText(getActivity().getApplicationContext(),noplat + carmodel + caryear + carcolor + norangka + nomesin ,Toast.LENGTH_LONG).show();
                AlertDialog.Builder ad = new AlertDialog.Builder(FormAddress.this);
                ad.setTitle("Konfirmasi Alamat Pengirim");
                ad.setMessage("Apakah data yang anda sudah isi benar?");


                ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestQueue queue = Volley.newRequestQueue(FormAddress.this);
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlAddressPeng, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jObj = new JSONObject(response);
                                    int sukses = jObj.getInt("code");
                                    if (sukses == 200) {
                                        Toast.makeText(FormAddress.this.getApplicationContext(), "Data alamat pengirim berhasil ditambahkan", Toast.LENGTH_SHORT).show();

//                                        data = jObj.getJSONObject("data");
                                        id_pengirimDB = jObj.getString("data");
                                        Intent i = new Intent(getApplicationContext(),FormAddressPenerima.class);
                                        startActivity(i);
                                        finish();

//                                        MyCarPage fragmycar = new MyCarPage();
//                                        fragmycar.setArguments(getActivity().getIntent().getExtras());
//                                        Bundle bundle = new Bundle();
//                                        bundle.putString("username",username);
//                                        fragmycar.setArguments(bundle);
//                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,fragmycar).commit();
                                    } else {
                                        Toast.makeText(FormAddress.this.getApplicationContext(), "Data alamat pengirim gagal ditambahkan", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(FormAddress.this.getApplicationContext(), "Periksa pengisian data sesuai keterangan", Toast.LENGTH_LONG).show();
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
                                Toast.makeText(FormAddress.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            } }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();
                                params.put("nama", txtnamaPengirim);
                                params.put("telpon", txtnoTelpPengirim);
                                params.put("alamat_lengkap", txtalamatLengkapPengirim);
                                params.put("provinsi",txtSpinProvinsi );
                                params.put("kota", txtkotaPeng);
                                params.put("kodepos", txtkodePosPeng);
                                params.put("notes_tambahan", txtNotesPeng);



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
                            //pembatas



                }




                );

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