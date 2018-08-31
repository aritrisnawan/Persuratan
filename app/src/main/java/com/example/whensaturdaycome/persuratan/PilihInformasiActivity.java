package com.example.whensaturdaycome.persuratan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PilihInformasiActivity extends AppCompatActivity {

    private TextView idfor;
    //private TextView idsre;
    private TextView jdl;
    private TextView ktu;
    private TextView sii;

    String idmas;
    //String ider;
    String judd;
    String wkk;
    String iss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_informasi);

        idfor  =(TextView)findViewById(R.id.idinf);
        //idsre  =(TextView)findViewById(R.id.idusr);
        jdl  =(TextView)findViewById(R.id.jud);
        ktu  =(TextView)findViewById(R.id.wak);
        sii   =(TextView)findViewById(R.id.is);

        Bundle bundle= getIntent().getExtras();

        idmas   = bundle.getString("Id_informasi");
        //ider   = bundle.getString("Id_user");
        judd   = bundle.getString("Judul");
        wkk   = bundle.getString("Waktu");
        iss   = bundle.getString("Isi");

        idfor.setText("Id Informasi :\n"+idmas);
        //idsre.setText("Id User :\n"+ider);
        jdl.setText("Judul :\n"+judd);
        ktu.setText("Waktu :\n"+wkk);
        sii.setText("Isi :\n"+iss);

    }
}
