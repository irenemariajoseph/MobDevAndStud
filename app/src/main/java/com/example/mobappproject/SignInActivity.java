package com.example.mobappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    private EditText Login_Pass,Login_Email;
    private String email,password;
    private String urlcheckdata ="http://192.168.1.78/mobappbackend/router/login.php";

    private static final String TAG_USER="data";

    private JSONObject data;


    Button btnSignInMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnSignInMenu = findViewById(R.id.btnSignInMenu);



        Button btnlogin = (Button)findViewById(R.id.btnSignInMenu);
        Login_Email = (EditText) findViewById(R.id.Login_Email);
        Login_Pass = (EditText) findViewById(R.id.Login_Pass);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Login_Email.getText().toString();
                password = Login_Pass.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(SignInActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlcheckdata, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            int sukses = jObj.getInt("code");

                            if (sukses == 200) {
//                                Intent i = new Intent(getApplicationContext(),MainMenuActivity.class);
//                                startActivity(i);

                                data = jObj.getJSONObject("data");
                                String userRole = data.getString("role");

                                if(userRole.equals("user")){
                                    Intent i = new Intent(getApplicationContext(),MainMenuActivity.class);
//                                    i.putExtra("email",Uname);
                                    startActivity(i);
                                    finish();
                                }

                                if(userRole.equals("kurir")){
                                    Intent i = new Intent(getApplicationContext(),AdminTrackingUpdate.class);
//                                    i.putExtra("username",Uname);
                                    startActivity(i);
                                    finish();
                                }
                                finish();
                            } else {
                                Toast.makeText(SignInActivity.this, "Email dan Password yang anda masukkan salah", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SignInActivity.this, "silahkan cek koneksi internet anda", Toast.LENGTH_SHORT).show();
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