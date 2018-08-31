package com.example.whensaturdaycome.persuratan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.whensaturdaycome.persuratan.data.SuratMa;

import java.util.ArrayList;
import java.util.List;

public class PilihSuratMActivity extends AppCompatActivity {

    private TextView idsuk;
    private TextView idjen;
    private TextView tgdit;
    private TextView noag;
    private TextView pirim;
    private TextView smnama;
    private TextView tgrat;
    private TextView phal;
    private TextView idif;
    private TextView idcam;
    private TextView idser;
    private TextView disp;
    private TextView cact;
    private ImageView fl;

    String idjn;
    String idsm;
    String tgrim;
    String noda;
    String prim;
    String nasut;
    String tglrat;
    String prial;
    String idfat;
    String idmac;
    String idus;
    String dispo;
    String cata;
    String fls;
    List<SuratMa> suratMaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_surat_m);

        suratMaList = new ArrayList<>();

        tgdit  =(TextView)findViewById(R.id.tgldi);
        pirim   =(TextView)findViewById(R.id.peng);
        smnama   =(TextView) findViewById(R.id.namasm);
        tgrat    =(TextView)findViewById(R.id.tglsur);
        phal    =(TextView)findViewById(R.id.pri);
        disp   =(TextView)findViewById(R.id.dis);
        cact   =(TextView)findViewById(R.id.cat);
        idsuk  =(TextView)findViewById(R.id.idsma);
//        idjen  =(TextView)findViewById(R.id.idjns);
//        noag  =(TextView)findViewById(R.id.noagn);
//        idif   =(TextView)findViewById(R.id.idsif);
//        idcam   =(TextView)findViewById(R.id.idmac);
//        idser   =(TextView)findViewById(R.id.idusr);

        fl   =(ImageView) findViewById(R.id.gambar);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PilihSuratMActivity.this, TampilGambar.class);
                startActivity(intent);
            }
        });


        Bundle bundle= getIntent().getExtras();

        tgrim   = bundle.getString("Tgl_diterima");
        prim   = bundle.getString("Pengirim");
        nasut   = bundle.getString("Nama_surat");
        tglrat   = bundle.getString("Tgl_surat");
        prial   = bundle.getString("Perihal");
        dispo   = bundle.getString("disposisi");
        cata   = bundle.getString("catatan");
        idsm   = bundle.getString("id_surat_masuk");
        idjn   = bundle.getString("Id_jenis");
        noda   = bundle.getString("No_agenda");
        idfat   = bundle.getString("Id_sifat");
        idmac    = bundle.getString("Id_macam");
        idus    = bundle.getString("Id_user");
        fls     = bundle.getString("File");

        Glide.with(getApplicationContext())
                .load("http://192.168.43.186/apicoba/img/" + fls)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_not_interested_black_24dp)
                .override(300, 500)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(fl);

        tgdit.setText("Tanggal Diterima :\n"+tgrim);
        pirim.setText("Pengirim :\n"+prim);
        smnama.setText("Nama Surat :\n"+nasut);
        tgrat.setText("Tanggal Surat :\n"+tglrat);
        phal.setText("Perihal :\n"+prial);
        disp.setText("Disposisi :\n"+dispo);
        cact.setText("Catatan :\n"+cata);
        idsuk.setText("Id Surat :\n"+idsm);
//        idjen.setText("Id Jenis :\n"+idjn);
//        noag.setText("Nomer Agenda :\n"+noda);
//        idif.setText("Id Sifat :\n"+idfat);
//        idcam.setText("Id Macam :\n"+idmac);
//        idser.setText("Id User :\n"+idus);
//        fl.setImageURI(Uri.parse("File :\n"+fl));
    }
}
