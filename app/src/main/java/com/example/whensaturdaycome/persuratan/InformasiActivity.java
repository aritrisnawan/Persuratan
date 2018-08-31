package com.example.whensaturdaycome.persuratan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.whensaturdaycome.persuratan.adapter.Adapterinforma;
import com.example.whensaturdaycome.persuratan.adapter.Adaptersuratkel;
import com.example.whensaturdaycome.persuratan.callback.InformasiCallBack;
import com.example.whensaturdaycome.persuratan.callback.SuratKeluarCallBack;
import com.example.whensaturdaycome.persuratan.data.Informa;
import com.example.whensaturdaycome.persuratan.data.Suratkel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformasiActivity extends AppCompatActivity {

    public EditText search;
    List<Informa> informaList;
    ListView listinforma;
    Adapterinforma adapterinforma;
    ProgressDialog progressDialog;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);

        listinforma = (ListView) findViewById(R.id.listinforma);
        informaList = new ArrayList<>();
        /*search=(EditText)findViewById(R.id.cari);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String kata = s.toString();
                final List<Informa> pilihinforma = new ArrayList<Informa>();
                for (Informa ceking : informaList) {
                    if (ceking.getJudul().toUpperCase().startsWith(kata.toUpperCase())) {
                        pilihinforma.add(ceking);
                    }
                }
                adapterinforma = new Adapterinforma(pilihinforma, getApplicationContext());
                adapterinforma.notifyDataSetChanged();
                listinforma.setAdapter(adapterinforma);
                listinforma.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String selectedItem = ((TextView) view).getText().toString();
                        if (list.contains(selectedItem))
                            list.remove(selectedItem);
                        else
                            list.add(selectedItem);
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        informaList();
        list= new ArrayList<String>();
        listinforma.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent inten = new Intent(getApplicationContext(), PilihInformasiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Id_informasi", informaList.get(i).getId_informasi());
                bundle.putString("Id_user", informaList.get(i).getId_user());
                bundle.putString("Judul", informaList.get(i).getJudul());
                bundle.putString("Waktu", informaList.get(i).getWaktu());
                bundle.putString("Isi", informaList.get(i).getIsi());
                inten.putExtras(bundle);
                startActivity(inten);
            }
        });
    }
    private void informaList() {
        /*progressDialog = ProgressDialog.show(InformasiActivity.this, "", "mengambil data", true);
        progressDialog.setCancelable(true);*/

        InformasiCallBack diperiksaCallBack = new InformasiCallBack(InformasiActivity.this);
        diperiksaCallBack.InformasiCallBack(new InformasiCallBack.InformasiBack() {
            @Override
            public void hasil(String hasil) {

                try {

                    JSONObject object = new JSONObject(hasil);
                    Boolean status = object.getBoolean("status");
                    if (status) {
                        JSONArray data = object.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            Informa getnama = new Informa();
                            getnama.setId_informasi(item.getString("id_informasi"));
                            getnama.setId_user(item.getString("id_user"));
                            getnama.setJudul(item.getString("judul"));
                            getnama.setWaktu(item.getString("waktu"));
                            getnama.setIsi(item.getString("isi"));
                            informaList.add(getnama);
                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(InformasiActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                adapterinforma = new Adapterinforma(informaList, InformasiActivity.this);
                listinforma.setAdapter(adapterinforma);
                //progressDialog.dismiss();
            }
        });
    }
}
