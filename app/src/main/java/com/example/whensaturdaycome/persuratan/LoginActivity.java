package com.example.whensaturdaycome.persuratan;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.whensaturdaycome.persuratan.data.AppControl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    private ProgressDialog pd;
    Button btnLogin;
    EditText txt_username, txt_password;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static final String LOGIN_URL = "http://192.168.43.186/apicoba/index.php/Login/";

    @SuppressLint({"CommitPrefEdits", "WrongConstant"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnLogin = (Button) findViewById(R.id.btnLogin);
        txt_username = (EditText) findViewById(R.id.txt_username);
        txt_password = (EditText) findViewById(R.id.txt_password);


        preferences = getSharedPreferences("Login",MODE_APPEND);
//        String status = preferences.getString("status", null);
//        editor = preferences.edit();
//        if (status.equals("true")){
//            startActivity(new Intent(this, MainActivity.class));
//            finish();
//        }

        if (new AppControl(this).isLogin()==true){
            startActivity(new Intent( this,MainActivity.class));
            finish();
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_username.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (txt_password.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    loginRequest(txt_username.getText().toString(), txt_password.getText().toString());
                }
            }
        });
    }

    private void loginRequest(final String username, final String password) {
//        pd.setMessage("login . . .");
//        pd.show();
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        String response = null;
        final String finalResponse = response;
        StringRequest postRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
//                            pd.hide();
                            //showSnackbar(response);
                            JSONObject obj = new JSONObject(response);
                            if(obj.getString("status").equals("true")) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                AppControl control= new AppControl(getApplicationContext(),username,password);
                                Toast.makeText(getApplicationContext(), "login sukses",  Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "invalid username or password",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        pd.hide();
                        NetworkResponse response = error.networkResponse;
                        if (response != null && response.data != null) {
                            Toast.makeText(getApplicationContext(), "salah email or password", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Cek your conection", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("user", txt_username.getText().toString());
                params.put("pass", txt_password.getText().toString());
                return params;
            }

        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }

    }



