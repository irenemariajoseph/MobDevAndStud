package com.example.mobappproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminTrackingUpdate extends AppCompatActivity {


    private String urlupdatekurir = "http://192.168.1.9/mobappbackend/router/updatestatus.php";


    private EditText id_updatestatus;
    private Spinner spr_updatestatus;

    private String txt_idupdatestatus, txt_updatestatus;

    //get activity ganti semuaaaaaaaaaaaaaaaa
    private Button btnUpdatestatus ;
    //--------------------------------------Listkurir

    ListView lv;
    ArrayList<HashMap<String,String>> list_anggota;
    String url_get_mahasiswa = "http://192.168.1.9/mobappbackend/router/showpackagelistkurir.php";

    private static final String TAG_MAHASISWA="data";
    private static final String TAG_ID="id_transaksi";
    private static final String TAG_NAMA="alamat_pengirim";
    private static final String TAG_ALAMAT = "alamat_penerima";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tracking_update);
        list_anggota=new ArrayList<>();
        lv=findViewById(R.id.listViewKurir);

        final String[] from={"id_transaksi","alamat_pengirim","alamat_penerima"};//string array
        final int[] to={R.id.list_content8,R.id.list_content9,R.id.list_content10};//int array of views id's

        RequestQueue queue = Volley.newRequestQueue(AdminTrackingUpdate.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_get_mahasiswa, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    JSONArray member= jObj.getJSONArray(TAG_MAHASISWA);
                    //Toast.makeText(SemuaMahasiswaActivity.this, "sesudah tag", Toast.LENGTH_SHORT).show();

                    for (int i=0; i< member.length();i++) {
                        JSONObject a=member.getJSONObject(i);
                        String id=a.getString(TAG_ID);
                        String nama=a.getString(TAG_NAMA);
                        String alamat = a.getString(TAG_ALAMAT);

                        HashMap<String,String> map=new HashMap<>();
                        map.put("id_transaksi",id);
                        map.put("alamat_pengirim",nama);
                        map.put("alamat_penerima",alamat);
                        //Toast.makeText(SemuaMahasiswaActivity.this, "nama"+nama, Toast.LENGTH_SHORT).show();
                        list_anggota.add(map);
                    }


                    //String[] from={"TAG_ID","TAG_NAMA"};//string array

                    ListAdapter adapter=new SimpleAdapter(
                            AdminTrackingUpdate.this,list_anggota,R.layout.list_transaksi_kurir,
                            from, to);
                    lv.setAdapter(adapter);


                }                catch (Exception ex) {
                    Log.e("Error", ex.toString());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
                Toast.makeText(AdminTrackingUpdate.this, "silahkan cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        queue.add(stringRequest);



        id_updatestatus=(EditText)findViewById(R.id.idupdate);
        spr_updatestatus = (Spinner) findViewById(R.id.spinnerstatus);
        btnUpdatestatus = (Button)findViewById(R.id.btnUpdatestatus);
        btnUpdatestatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                txt_idupdatestatus = id_updatestatus.getText().toString();
                txt_updatestatus = spr_updatestatus.getSelectedItem().toString();





                //Toast.makeText(getActivity().getApplicationContext(),noplat + carmodel + caryear + carcolor + norangka + nomesin ,Toast.LENGTH_LONG).show();
                AlertDialog.Builder ad = new AlertDialog.Builder(AdminTrackingUpdate.this);
                ad.setTitle("Konfirmasi Update status");
                ad.setMessage("Apakah data yang anda sudah isi benar?");


                ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RequestQueue queue = Volley.newRequestQueue(AdminTrackingUpdate.this);
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlupdatekurir, new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jObj = new JSONObject(response);
                                            int sukses = jObj.getInt("code");
                                            if (sukses == 200) {
                                                Toast.makeText(AdminTrackingUpdate.this.getApplicationContext(), "Status sudah terupdate", Toast.LENGTH_SHORT).show();


                                                Intent i = new Intent(getApplicationContext(),AdminTrackingUpdate.class);
                                                startActivity(i);
                                                finish();

//                                        MyCarPage fragmycar = new MyCarPage();
//                                        fragmycar.setArguments(getActivity().getIntent().getExtras());
//                                        Bundle bundle = new Bundle();
//                                        bundle.putString("username",username);
//                                        fragmycar.setArguments(bundle);
//                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,fragmycar).commit();
                                            } else {
                                                Toast.makeText(AdminTrackingUpdate.this.getApplicationContext(), "Data status gagal terupdate", Toast.LENGTH_SHORT).show();
                                                Toast.makeText(AdminTrackingUpdate.this.getApplicationContext(), "Periksa pengisian data sesuai keterangan", Toast.LENGTH_LONG).show();
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
                                        Toast.makeText(AdminTrackingUpdate.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                    } }) {
                                    @Override
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<>();
                                        params.put("id_transaksi", txt_idupdatestatus);
                                        params.put("status_paket", txt_updatestatus);



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

        Button btnlogoutkurir =(Button)findViewById(R.id.btnlogoutkurir);
        btnlogoutkurir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Sign.class);
                startActivity(i);
            }
        });

        Button btnNotes =(Button)findViewById(R.id.button2);
        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),NotesDriver.class);
                startActivity(i);
            }
        });
    }
}
