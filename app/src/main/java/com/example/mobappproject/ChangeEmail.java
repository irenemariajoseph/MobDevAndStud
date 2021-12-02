package com.example.mobappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class ChangeEmail extends AppCompatActivity {

    private EditText txtemailbaru, txbemailganti;
    private String kirimemailbaru, kirimemailganti;
    Button btngantiemail;



    private String urlchangeemail ="http://192.168.1.9/mobappbackend/router/changeemail.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        txtemailbaru = (EditText)findViewById(R.id.txtemailbaru);
        txbemailganti = (EditText)findViewById(R.id.txbemailganti);


//        btngantiemail = (Button)findViewById(R.id.btnSignUp);

        btngantiemail = findViewById(R.id.btngantiemail);
        Button kirimbtngantiemail = (Button)findViewById(R.id.btngantiemail);

        kirimbtngantiemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kirimemailbaru = txtemailbaru.getText().toString();
                kirimemailganti = txbemailganti.getText().toString();

                    RequestQueue queue = Volley.newRequestQueue(ChangeEmail.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, urlchangeemail, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jObj = new JSONObject(response);
                                int sukses = jObj.getInt("code");
                                if (sukses == 200) {
                                    Toast.makeText(ChangeEmail.this, "Email sukses terganti", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(),MenuUserSettings.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(ChangeEmail.this, "Email gagal terganti, periksa pengisian data", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception ex) {
                                Log.e("Error", ex.toString());
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Error", error.getMessage());
                            Toast.makeText(ChangeEmail.this, "silahkan cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("email",kirimemailganti );
                            params.put("emailbaru", kirimemailbaru);


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