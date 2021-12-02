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

public class SignUpActivity extends AppCompatActivity {
        private EditText editName1, editEmail, editPass;
        CheckBox simpleCheckBox;
        Button btnSignUp, btn;

        private String txtname;
        private String txtemail;
        private String txtpassword;
        private CheckBox checkbox;
        private String urlregisterdata ="http://192.168.1.9/mobappbackend/router/registration.php";

        private static final String TAG_USER="data";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_sign_up);

                editName1 = (EditText)findViewById(R.id.editName1);
                editEmail = (EditText)findViewById(R.id.editEmail);
                editPass = (EditText)findViewById(R.id.editPass);
                checkbox = (CheckBox)findViewById(R.id.simpleCheckBox);


                btnSignUp = (Button)findViewById(R.id.btnSignUp);


                btnSignUp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                txtname = editName1.getText().toString();
                                txtemail = editEmail.getText().toString();
                                txtpassword = editPass.getText().toString();





                                if(checkbox.isChecked() ){

                                        RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                                        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlregisterdata, new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                        try {
                                                                JSONObject jObj = new JSONObject(response);
                                                                int sukses = jObj.getInt("code");
                                                                if (sukses == 200) {
                                                                        Toast.makeText(SignUpActivity.this, "Register data berhasil! Silahkan Sign In", Toast.LENGTH_SHORT).show();
                                                                        Intent i = new Intent(getApplicationContext(), SignInActivity.class);
//
                                                                        startActivity(i);
                                                                        finish();
                                                                } else {
                                                                        Toast.makeText(SignUpActivity.this, "Register data gagal periksa informasi  tertera", Toast.LENGTH_SHORT).show();
                                                                }
                                                        } catch (Exception ex) {
                                                                Log.e("Error", ex.toString());
                                                        }
                                                }
                                        }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                        Log.e("Error", error.getMessage());
                                                        Toast.makeText(SignUpActivity.this, "silahkan cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                                                }
                                        }) {
                                                @Override
                                                protected Map<String, String> getParams() {
                                                        Map<String, String> params = new HashMap<>();
                                                        params.put("name", txtname);
                                                        params.put("email", txtemail);
                                                        params.put("password", txtpassword);

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

                                }else{
                                        Toast.makeText(SignUpActivity.this, "Anda Harus Setuju dengan Policy yang Ada", Toast.LENGTH_SHORT).show();
                                }

                        }

                });
        }

}