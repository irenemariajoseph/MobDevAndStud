package com.example.mobappproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
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
import static com.example.mobappproject.MenuPackageDetails.id_transaksi;

public class MenuOrderProcess extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static String  namapengirim, alamatpengirim, namapenerima, alamatpenerima, namabarang, txtberat, txtjarak, asuransibarang;
//    txtid_transaksi
    private JSONObject data;
    
    private String urlshowpackageinfo = "http://192.168.1.9/mobappbackend/router/showpackagedetails.php";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu_order_process);


            Button passingdata =(Button)findViewById(R.id.buatconfirm);
            passingdata.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RequestQueue queue = Volley.newRequestQueue(MenuOrderProcess.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, urlshowpackageinfo, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jObj = new JSONObject(response);
                                int sukses = jObj.getInt("code");

                                if (sukses == 200) {
                                    data = jObj.getJSONObject("data");
//                                    txtid_transaksi = data.getString("id_transaksi");
                                    namapengirim = data.getString("nama_pengirim");
                                    alamatpengirim = data.getString("alamat_pengirim");
                                    namapenerima= data.getString("nama");
                                    alamatpenerima   = data.getString("alamat_lengkap");
                                    namabarang= data.getString("nama_barang");
                                    txtberat = data.getString("berat");
                                    txtjarak = data.getString("jarak");
                                    asuransibarang = data.getString("asuransibarang");



                                    Intent i = new Intent(getApplicationContext(),MenuOrderConfirmOrder.class);
                                    startActivity(i);
                                    finish();




                                } else {
                                    Toast.makeText(MenuOrderProcess.this, "Email dan Password yang anda masukkan salah", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(MenuOrderProcess.this, "Data harus di isi berurutan, Isi detail data mulai dari tombol proses 1", Toast.LENGTH_SHORT).show();
                        } }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("id_transaksi", id_transaksi);


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


            Button btnForm =(Button)findViewById(R.id.button4);
            btnForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),FormAddress.class);
                    startActivity(i);
                }
            });
//
            Button btnFormPen =(Button)findViewById(R.id.button5);
            btnFormPen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MenuOrderProcess.this, "Data harus di isi berurutan, Isi detail data mulai dari tombol proses 1", Toast.LENGTH_LONG).show();
                }
            });

//
//
//
//
            Button btnPackageDetails =(Button)findViewById(R.id.btnPackageDetails);
            btnPackageDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MenuOrderProcess.this, "Data harus di isi berurutan, Isi detail data mulai dari tombol proses 1", Toast.LENGTH_LONG).show();
                }
            });

//            Button buatconfirm =(Button)findViewById(R.id.buatconfirm);
//            buatconfirm.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(getApplicationContext(),MenuOrderConfirmOrder.class);
//                    startActivity(i);
//                }
//            });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}