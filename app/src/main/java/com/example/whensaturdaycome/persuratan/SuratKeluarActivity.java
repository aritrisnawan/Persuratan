package com.example.whensaturdaycome.persuratan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.whensaturdaycome.persuratan.adapter.Adaptersuratkel;
import com.example.whensaturdaycome.persuratan.callback.SuratKeluarCallBack;
import com.example.whensaturdaycome.persuratan.data.Suratkel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuratKeluarActivity extends AppCompatActivity {

    public EditText search;
    List<Suratkel> suratkelList;
    ListView listsuratk;
    Adaptersuratkel adaptersuratkel;
    ProgressDialog progressDialog;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_keluar);

        listsuratk = (ListView) findViewById(R.id.listsuratk);
        suratkelList = new ArrayList<>();
        search = (EditText) findViewById(R.id.cari);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String kata = s.toString();
                final List<Suratkel> pilihsuratk = new ArrayList<Suratkel>();
                for (Suratkel ceking : suratkelList) {
                    if (ceking.getNo_surat().toUpperCase().startsWith(kata.toUpperCase())) {
                        pilihsuratk.add(ceking);
                    } else if (ceking.getTujuan().toUpperCase().startsWith(kata.toUpperCase())) {
                        pilihsuratk.add(ceking);
                    }
                }
                adaptersuratkel = new Adaptersuratkel(pilihsuratk, getApplicationContext());
                adaptersuratkel.notifyDataSetChanged();
                listsuratk.setAdapter(adaptersuratkel);
                listsuratk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        });

        suratkelList();
        list = new ArrayList<String>();
        listsuratk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent inten = new Intent(getApplicationContext(), PilihSuratKActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Id_surat_keluar", suratkelList.get(i).getId_surat_keluar());
                bundle.putString("Id_kelompok", suratkelList.get(i).getId_kelompok());
                bundle.putString("Tgl_surat", suratkelList.get(i).getTgl_surat());
                bundle.putString("No_surat", suratkelList.get(i).getNo_surat());
                bundle.putString("Tujuan", suratkelList.get(i).getTujuan());
                bundle.putString("Perihal", suratkelList.get(i).getPerihal());
                bundle.putString("Tembusan", suratkelList.get(i).getTembusan());
                bundle.putString("Catatan", suratkelList.get(i).getCatatan());
                bundle.putString("File", suratkelList.get(i).getFile());
                inten.putExtras(bundle);
                startActivity(inten);
            }
        });
    }

    private void suratkelList() {
        /*progressDialog = ProgressDialog.show(SuratKeluarActivity.this, "", "mengambil data", true);
        progressDialog.setCancelable(true);*/

        SuratKeluarCallBack diperiksaCallBack = new SuratKeluarCallBack(SuratKeluarActivity.this);
        diperiksaCallBack.SuratKeluarCallBack(new SuratKeluarCallBack.SuratKeluarBack() {
            @Override
            public void hasil(String hasil) {


                try {

                    JSONObject object = new JSONObject(hasil);
                    Boolean status = object.getBoolean("status");
                    if (status) {
                        JSONArray data = object.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            Suratkel getnama = new Suratkel();
                            getnama.setId_surat_keluar(item.getString("id_surat_keluar"));
                            getnama.setId_kelompok(item.getString("id_kelompok"));
                            getnama.setTgl_surat(item.getString("tgl_surat"));
                            getnama.setNo_surat(item.getString("no_surat"));
                            getnama.setPerihal(item.getString("perihal"));
                            getnama.setTujuan(item.getString("tujuan"));
                            getnama.setPerihal(item.getString("perihal"));
                            getnama.setTembusan(item.getString("tembusan"));
                            getnama.setFile(item.getString("file"));
                            getnama.setCatatan(item.getString("catatan"));
                            suratkelList.add(getnama);
                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(SuratKeluarActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                adaptersuratkel = new Adaptersuratkel(suratkelList, SuratKeluarActivity.this);
                listsuratk.setAdapter(adaptersuratkel);
                //progressDialog.dismiss();
            }
        });
    }
}
