package com.example.mobappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ChangePassword extends AppCompatActivity {
    private EditText edtemail, edtpassword;
    private String email, password;
    Button btnkirimpass;
    private String urlchangepass ="http://192.168.1.78/mobappbackend/router/changepassword.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        edtemail = (EditText) findViewById(R.id.edtemail);
        edtpassword = (EditText) findViewById(R.id.edtpassword);

        btnkirimpass = findViewById(R.id.btnkirimpass);
        Button btnsubmitpass = (Button)findViewById(R.id.btnkirimpass);
        btnsubmitpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtemail.getText().toString();
                password = edtpassword.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(ChangePassword.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlchangepass, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            int sukses = jObj.getInt("code");

                            if (sukses == 200) {
                                Intent i = new Intent(getApplicationContext(),MenuUserSettings.class);
                                Toast.makeText(ChangePassword.this, "Password sukses terganti", Toast.LENGTH_SHORT).show();
                                startActivity(i);


                            } else {
                                Toast.makeText(ChangePassword.this, "Password gagal terganti, periksa pengisian data", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ChangePassword.this, "silahkan cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                    } }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email);
                        params.put("password", password);

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

    }
}