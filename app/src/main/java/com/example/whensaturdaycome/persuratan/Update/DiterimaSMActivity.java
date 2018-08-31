package com.example.whensaturdaycome.persuratan.Update;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.whensaturdaycome.persuratan.PilihDisposisiActivity;
import com.example.whensaturdaycome.persuratan.PilihSuratMActivity;
import com.example.whensaturdaycome.persuratan.R;
import com.example.whensaturdaycome.persuratan.SuratMasukActivity;

import java.util.HashMap;
import java.util.Map;

public class DiterimaSMActivity extends AppCompatActivity {
    EditText id_surat_masuk,disposisi,catatan;
    TextView update;
    private RequestQueue requestQueue;

    //private static final String UPDATE_URL = "http://192.168.43.186/apicoba/index.php/surat_masuk.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diterima_sm);

        requestQueue = Volley.newRequestQueue(this);
        id_surat_masuk = (EditText)findViewById(R.id.idsme);
        disposisi = (EditText)findViewById(R.id.disposi);
        catatan = (EditText)findViewById(R.id.catate);
        update = (TextView)findViewById(R.id.editsm);
        id_surat_masuk.setText(getIntent().getStringExtra("id_surat_masuk"));
        disposisi.setText(getIntent().getStringExtra("disposisi"));
        catatan.setText(getIntent().getStringExtra("catatan"));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.43.186/apicoba/index.php/Surat_masuk/";

                StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(DiterimaSMActivity.this, response, Toast.LENGTH_SHORT).show();
//                                Intent cklik = new Intent(getApplicationContext(), SuratMasukActivity.class);
//                                startActivity(cklik);
//                                Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "error " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("id_surat_masuk",getIntent().getStringExtra("id_surat_masuk"));
                        hashMap.put("disposisi",disposisi.getText().toString());
                        hashMap.put("catatan",catatan.getText().toString());
                        return hashMap;
                    }
                };

                requestQueue.add(stringRequest);
            }

        });
        }
}
