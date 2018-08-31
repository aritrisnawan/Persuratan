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
import android.widget.ImageView;
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
import com.example.whensaturdaycome.persuratan.adapter.Adaptersuratma;
import com.example.whensaturdaycome.persuratan.callback.SuratKeluarCallBack;
import com.example.whensaturdaycome.persuratan.callback.SuratMasukCallBack;
import com.example.whensaturdaycome.persuratan.data.SuratMa;
import com.example.whensaturdaycome.persuratan.data.Suratkel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuratMasukActivity extends AppCompatActivity {

    public EditText search;
    List<SuratMa> suratMaList;
    ListView listsuratm;
    ImageView gambar;
    Adaptersuratma adaptersuratma;
    ProgressDialog progressDialog;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_masuk);

        listsuratm = (ListView) findViewById(R.id.listsuratm);
        suratMaList = new ArrayList<>();
        search=(EditText)findViewById(R.id.cari);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String kata = s.toString();
                final List<SuratMa> pilihsuratm = new ArrayList<SuratMa>();
                for (SuratMa ceking : suratMaList) {
                    if (ceking.getNama_surat().toUpperCase().startsWith(kata.toUpperCase())) {
                        pilihsuratm.add(ceking);
                    } else if (ceking.getPengirim().toUpperCase().startsWith(kata.toUpperCase())) {
                        pilihsuratm.add(ceking);
                    }
                }
                adaptersuratma = new Adaptersuratma(pilihsuratm, getApplicationContext());
                adaptersuratma.notifyDataSetChanged();
                listsuratm.setAdapter(adaptersuratma);
                listsuratm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        suratMaList();
        list= new ArrayList<String>();
        listsuratm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent inten = new Intent(getApplicationContext(), PilihSuratMActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Tgl_diterima", suratMaList.get(i).getTgl_diterima());
                bundle.putString("Pengirim", suratMaList.get(i).getPengirim());
                bundle.putString("Nama_surat", suratMaList.get(i).getNama_surat());
                bundle.putString("Tgl_surat", suratMaList.get(i).getTgl_surat());
                bundle.putString("Perihal", suratMaList.get(i).getPerihal());
                bundle.putString("disposisi", suratMaList.get(i).getDisposisi());
                bundle.putString("catatan", suratMaList.get(i).getCatatan());
                bundle.putString("File", suratMaList.get(i).getFile());
                bundle.putString("id_surat_masuk", suratMaList.get(i).getId_surat_masuk());
                bundle.putString("Id_jenis", suratMaList.get(i).getId_jenis());
                bundle.putString("No_agenda", suratMaList.get(i).getNo_agenda());
                bundle.putString("Id_sifat", suratMaList.get(i).getId_sifat());
                bundle.putString("Id_macam", suratMaList.get(i).getId_macam());
                bundle.putString("Id_user", suratMaList.get(i).getId_user());
                inten.putExtras(bundle);
                startActivity(inten);
            }
        });
    }
    private void suratMaList() {
//        progressDialog = ProgressDialog.show(SuratMasukActivity.this, "", "mengambil data", true);
//        progressDialog.setCancelable(true);

        SuratMasukCallBack diperiksaCallBack = new SuratMasukCallBack(SuratMasukActivity.this);
        diperiksaCallBack.SuratMasukCallBack(new SuratMasukCallBack.SuratMasukBack() {
            @Override
            public void hasil(String hasil) {

                try {

                    JSONObject object = new JSONObject(hasil);
                    Boolean status = object.getBoolean("status");
                    if (status) {
                        JSONArray data = object.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = data.getJSONObject(i);
                            SuratMa getnama = new SuratMa();
                            getnama.setId_surat_masuk(item.getString("id_surat_masuk"));
                            getnama.setId_jenis(item.getString("id_jenis"));
                            getnama.setTgl_diterima(item.getString("tgl_diterima"));
                            getnama.setNo_agenda(item.getString("no_agenda"));
                            getnama.setPengirim(item.getString("pengirim"));
                            getnama.setNama_surat(item.getString("nama_surat"));
                            getnama.setTgl_surat(item.getString("tgl_surat"));
                            getnama.setPerihal(item.getString("perihal"));
                            getnama.setId_sifat(item.getString("id_sifat"));
                            getnama.setId_macam(item.getString("id_macam"));
                            getnama.setId_user(item.getString("id_user"));
                            getnama.setFile(item.getString("file"));
                            getnama.setDisposisi(item.getString("disposisi"));
                            getnama.setCatatan(item.getString("catatan"));
                            suratMaList.add(getnama);
                        }
                        adaptersuratma = new Adaptersuratma(suratMaList, SuratMasukActivity.this);
                        listsuratm.setAdapter(adaptersuratma);
                        //progressDialog.dismiss();

                    }

                } catch (Exception e) {
                    Toast.makeText(SuratMasukActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

}
