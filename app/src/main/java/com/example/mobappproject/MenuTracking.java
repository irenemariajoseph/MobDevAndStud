package com.example.mobappproject;

import static com.example.mobappproject.SignInActivity.userName;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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
import static com.example.mobappproject.SignInActivity.userID;
public class MenuTracking extends Fragment {
    public static String txtid_transaksi,
    //6
    txtnamapengirim ,
    txtalamatpengirim,
    txttelp_peng,
    txtnama_penerima ,
    txtalamat_penerima,
    txttelp_pen,
    //10
    txtnama_barang ,
    txtkuantitas ,
    txtunit_paket,
    txtfragile ,
    txtasuransibarang,
    txtberat,
    txtjarak,
    txtharga,
    txttipe_pengambilan,
    txtstatus_paket,
    //7
    txttanggal_pickup ,
    txttanggal_deliveredtopost ,
    txttanggal_warehousetransit,
    txttanggal_acceptedbykurir ,
    txttanggal_ongoing ,
    txttanggal_arrived ,
    txttanggal_failed ;

    public EditText edtTrackinguser;
    public String strTracking;
    Button btnTrack;
    private JSONObject data;

//---------------------------------------------------------------------------------------------------
    private String urllist_transaction = "http://192.168.1.78/mobappbackend/router/showpackagelistuser.php";
    private String url_track_user = "http://192.168.1.78/mobappbackend/router/showpackagetracking.php";





    private static final String TAG_MAHASISWA="data";
    private static final String TAG_ID="id_transaksi";
    private static final String TAG_NAMA="alamat_pengirim";
    private static final String TAG_ALAMAT = "alamat_penerima";
    ListView lv;
    private ArrayList<HashMap<String,String>> list_anggota;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        username = getArguments().getString("username");
        return inflater.inflate(R.layout.activity_menu_tracking, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        final String[] from={"id_transaksi","alamat_pengirim","alamat_penerima"};//string array
        final int[] to={R.id.list_content2,R.id.list_content4,R.id.list_content6};//int array of views id's


        list_anggota =new ArrayList<>();
        lv = getActivity().findViewById(R.id.listView);

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urllist_transaction, new Response.Listener<String>() {
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

                    try {


                        ListAdapter adapter=new SimpleAdapter(getActivity().getApplicationContext(),list_anggota,R.layout.list_transaction_user, from, to);
                        lv.setAdapter(adapter);


                        //Toast.makeText(SemuaMahasiswaActivity.this, "setelah listview adapter", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception ex){
                        Toast.makeText(getActivity().getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG);
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
                Toast.makeText(getActivity().getApplicationContext(), "silahkan cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                //finish();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", userID);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(stringRequest);

//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, urllist_transaction, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jObj = new JSONObject(response);
//                    JSONArray member= jObj.getJSONArray(TAG_MAHASISWA);
//                    //Toast.makeText(SemuaMahasiswaActivity.this, "sesudah tag", Toast.LENGTH_SHORT).show();
//
//                    for (int i=0; i< member.length();i++) {
//                        JSONObject a=member.getJSONObject(i);
//                        String id=a.getString(TAG_ID);
//                        String nama=a.getString(TAG_NAMA);
//                        String alamat = a.getString(TAG_ALAMAT);
//
//                        HashMap<String,String> map=new HashMap<>();
//                        map.put("id_transaksi",id);
//                        map.put("alamat_pengirim",nama);
//                        map.put("alamat_penerima",alamat);
//                        //Toast.makeText(SemuaMahasiswaActivity.this, "nama"+nama, Toast.LENGTH_SHORT).show();
//                        list_anggota.add(map);
//                    }
//
//
//                    //String[] from={"TAG_ID","TAG_NAMA"};//string array
//
//                    ListAdapter adapter=new SimpleAdapter(
//                            getActivity(),list_anggota,R.layout.list_transaction_user,
//                            from, to);
//                    lv.setAdapter(adapter);
//
//
//                }                catch (Exception ex) {
//                    Log.e("Error", ex.toString());
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Error", error.getMessage());
//                Toast.makeText(getActivity(), "silahkan cek koneksi internet anda", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        queue.add(stringRequest);
//        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, urllist_transaction, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jObj = new JSONObject(response);
//                    JSONArray member= jObj.getJSONArray(TAG_MAHASISWA);
//                    //Toast.makeText(SemuaMahasiswaActivity.this, "sesudah tag", Toast.LENGTH_SHORT).show();
//
//                    for (int i=0; i< member.length();i++) {
//                        JSONObject a=member.getJSONObject(i);
//                        String id=a.getString(TAG_ID);
//                        String nama=a.getString(TAG_NAMA);
//                        String alamat = a.getString(TAG_ALAMAT);
//
//                        HashMap<String,String> map=new HashMap<>();
//                        map.put("id_transaksi",id);
//                        map.put("alamat_pengirim",nama);
//                        map.put("alamat_penerima",alamat);
//                        //Toast.makeText(SemuaMahasiswaActivity.this, "nama"+nama, Toast.LENGTH_SHORT).show();
//                        list_anggota.add(map);
//                    }
//
//                    try {
//                        //String[] from={"TAG_ID","TAG_NAMA"};//string array\
//
//                        final String[] from={"id_transaksi","alamat_pengirim","alamat_penerima"};//string array
//                        final int[] to={R.id.list_content2,R.id.list_content4,R.id.list_content6};//int array of views id's
//
////
//                        ListAdapter adapter=new SimpleAdapter(
//                                getActivity().getApplicationContext(),list_anggota,R.layout.list_transaction_user, from, to);
//                        lv.setAdapter(adapter);
//                        //Toast.makeText(SemuaMahasiswaActivity.this, "setelah listview adapter", Toast.LENGTH_SHORT).show();
//                    }
//                    catch (Exception ex){
//                        Toast.makeText(getActivity().getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG);
//                    }
//
//
//                }
//                catch (Exception ex) {
//                    Log.e("Error", ex.toString());
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Error", error.getMessage());
//                Toast.makeText(getActivity().getApplicationContext(), "silahkan cek koneksi internet anda", Toast.LENGTH_SHORT).show();
//                //finish();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("id", userID);
//
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("Content-Type", "application/x-www-form-urlencoded");
//                return params;
//            }
//        };
//        queue.add(stringRequest);





        //menu tracking-------------------------------------------------------------------
        edtTrackinguser = (EditText) getActivity().findViewById(R.id.edtTrackinguser);

        btnTrack = (Button)getActivity().findViewById(R.id.btnTrack);
        btnTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strTracking = edtTrackinguser.getText().toString();


                //Toast.makeText(getActivity().getApplicationContext(),noplat + carmodel + caryear + carcolor + norangka + nomesin ,Toast.LENGTH_LONG).show();
                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                ad.setTitle("Konfirmasi Detail Service");
                ad.setMessage("Apakah anda yakin akan menambahkan data dari mobil dengan nomor plat " + strTracking +" di akun anda?");
                ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_track_user, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jObj = new JSONObject(response);
                                    int sukses = jObj.getInt("code");
                                    if (sukses == 200) {
                                        Toast.makeText(getActivity().getApplicationContext(),"Data Mobil berhasil dihapus",Toast.LENGTH_LONG).show();
                                        data = jObj.getJSONObject("data");
                                        System.out.print(data);
                                        txtid_transaksi = data.getString("id_transaksi");
                                        //6
                                        System.out.print(txtid_transaksi);
                                        txtnamapengirim = data.getString("nama_pengirim");
                                        txtalamatpengirim = data.getString("alamat_pengirim");
                                        txttelp_peng= data.getString("telp_peng");
                                        txtnama_penerima  = data.getString("nama_penerima");
                                        txtalamat_penerima= data.getString("alamat_penerima");
                                        txttelp_pen = data.getString("telp_pen");
                                        //10
                                        txtnama_barang = data.getString("nama_barang");
                                        txtkuantitas  = data.getString("kuantitas");
                                        txtunit_paket = data.getString("unit_paket");
                                        txtfragile = data.getString("fragile");
                                        txtasuransibarang = data.getString("asuransibarang");
                                        txtberat = data.getString("berat");
                                        txtjarak = data.getString("jarak");
                                        txtharga = data.getString("harga");
                                        txttipe_pengambilan = data.getString("tipe_pengambilan");
                                        txtstatus_paket  = data.getString("status_paket");
                                        //7
                                        txttanggal_pickup = data.getString("tanggal_pickup");
                                        txttanggal_deliveredtopost = data.getString("tanggal_deliveredtopost");
                                        txttanggal_warehousetransit = data.getString("tanggal_warehousetransit");
                                        txttanggal_acceptedbykurir = data.getString("tanggal_acceptedbykurir");
                                        txttanggal_ongoing = data.getString("tanggal_ongoing");
                                        txttanggal_arrived = data.getString("tanggal_arrived");
                                        txttanggal_failed = data.getString("tanggal_failed");

                                        //gtau bener ato kagak
                                        Intent i = new Intent(getActivity(),TransactionDetails.class);
                                        startActivity(i);


                                    } else {
                                        Toast.makeText(getActivity().getApplicationContext(), "Data mobil gagal ditambahkan", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            } }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();
                                params.put("id_transaksi", strTracking);

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


//
//        edtTrackinguser = (EditText) getActivity().findViewById(R.id.edtTrackinguser);
//        strTracking = edtTrackinguser.getText().toString();
//        btnTrack = (Button)getActivity().findViewById(R.id.btnTrack);
//        btnTrack.setOnClickListener(new View.OnClickListener() {
//
//
//
//
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
//                ad.setTitle("Konfirmasi Hapus");
//                ad.setMessage("Apakah anda yakin ingin mentrack transaksi dengan ID *" + strTracking +"* dari transaksi anda?");
//                ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
//                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_track_user, new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                try {
//                                    JSONObject jObj = new JSONObject(response);
//                                    int sukses = jObj.getInt("code");
//                                    if (sukses == 200) {
//                                        Toast.makeText(getActivity().getApplicationContext(),"Data Mobil berhasil dihapus",Toast.LENGTH_LONG).show();
//                                        data = jObj.getJSONObject("data");
//                                        System.out.print(data);
//                                        txtid_transaksi = data.getString("id_transaksi");
//                                        //6
//                                        System.out.print(txtid_transaksi);
//                                        txtnamapengirim = data.getString("nama_pengirim");
//                                        txtalamatpengirim = data.getString("alamat_pengirim");
//                                        txttelp_peng= data.getString("telp_peng");
//                                        txtnama_penerima  = data.getString("nama_penerima");
//                                        txtalamat_penerima= data.getString("alamat_penerima");
//                                        txttelp_pen = data.getString("telp_pen");
//                                        //10
//                                        txtnama_barang = data.getString("nama_barang");
//                                        txtkuantitas  = data.getString("kuantitas");
//                                        txtunit_paket = data.getString("unit_paket");
//                                        txtfragile = data.getString("fragile");
//                                        txtasuransibarang = data.getString("asuransibarang");
//                                        txtberat = data.getString("berat");
//                                        txtjarak = data.getString("jarak");
//                                        txtharga = data.getString("harga");
//                                        txttipe_pengambilan = data.getString("tipe_pengambilan");
//                                        txtstatus_paket  = data.getString("status_paket");
//                                        //7
//                                        txttanggal_pickup = data.getString("tanggal_pickup");
//                                        txttanggal_deliveredtopost = data.getString("tanggal_deliveredtopost");
//                                        txttanggal_warehousetransit = data.getString("tanggal_warehousetransit");
//                                        txttanggal_acceptedbykurir = data.getString("tanggal_acceptedbykurir");
//                                        txttanggal_ongoing = data.getString("tanggal_ongoing");
//                                        txttanggal_arrived = data.getString("tanggal_arrived");
//                                        txttanggal_failed = data.getString("tanggal_failed");
//
//                                        //gtau bener ato kagak
//                                        Intent i = new Intent(getActivity(),TransactionDetails.class);
//                                        startActivity(i);
//
//
//                                    } else {
//                                        Toast.makeText(getActivity().getApplicationContext(), "Data Mobil gagal dihapus", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                                catch (Exception ex) {
//                                    Log.e("Error", ex.toString());
//                                }
//                            }
//
//
//                        }, new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Log.e("Error", error.getMessage());
//                                Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                            } }) {
//                            @Override
//                            protected Map<String, String> getParams() {
//                                Map<String, String> params = new HashMap<>();
//                                params.put("id_transaksi", strTracking);
//                                return params;
//                            }
//
//                            @Override
//                            public Map<String, String> getHeaders() throws AuthFailureError {
//                                Map<String, String> params = new HashMap<>();
//                                params.put("Content-Type", "application/x-www-form-urlencoded");
//                                return params;
//                            }
//                        };
//                        queue.getCache().clear();
//                        queue.add(stringRequest);
//                    }
//                });
//                ad.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                ad.show();
//            }
//        });


