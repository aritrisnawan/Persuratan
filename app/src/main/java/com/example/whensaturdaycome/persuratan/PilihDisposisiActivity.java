package com.example.whensaturdaycome.persuratan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.whensaturdaycome.persuratan.Update.DiterimaSMActivity;
import com.example.whensaturdaycome.persuratan.data.SuratMa;

import java.util.ArrayList;
import java.util.List;

public class PilihDisposisiActivity extends AppCompatActivity {

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
    private ImageView fle;
    private TextView cat;
    private Button diterima;
    private RequestQueue requestQueue;
    List<SuratMa> suratMaList;

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
    String cate;
    String fls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_disposisi);

        suratMaList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);


        tgdit = (TextView) findViewById(R.id.tgldi);
        pirim = (TextView) findViewById(R.id.peng);
        smnama = (TextView) findViewById(R.id.namasm);
        tgrat = (TextView) findViewById(R.id.tglsur);
        phal = (TextView) findViewById(R.id.pri);
        disp = (TextView) findViewById(R.id.dis);
        cat = (TextView) findViewById(R.id.catate);
        fle = (ImageView) findViewById(R.id.gambar);
        /*idsuk = (TextView) findViewById(R.id.idsma);
        idjen = (TextView) findViewById(R.id.idjns);
        noag = (TextView) findViewById(R.id.noagn);
        idif = (TextView) findViewById(R.id.idsif);
        idcam = (TextView) findViewById(R.id.idmac);
        idser = (TextView) findViewById(R.id.idusr);*/

        fle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PilihDisposisiActivity.this, TampilGambar.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();

        idsm = bundle.getString("id_surat_masuk");
        idjn = bundle.getString("Id_jenis");
        tgrim = bundle.getString("Tgl_diterima");
        noda = bundle.getString("No_agenda");
        prim = bundle.getString("Pengirim");
        nasut = bundle.getString("Nama_surat");
        tglrat = bundle.getString("Tgl_surat");
        prial = bundle.getString("Perihal");
        idfat = bundle.getString("Id_sifat");
        idmac = bundle.getString("Id_macam");
        idus = bundle.getString("Id_user");
        dispo = bundle.getString("disposisi");
        cate   = bundle.getString("catatan");
        fls   = bundle.getString("File");
        Glide.with(getApplicationContext())
                .load("http://192.168.43.186/apicoba/img/" + fls)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_not_interested_black_24dp)
                .override(300, 500)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(fle);


        tgdit.setText("Tanggal Diterima :\n" + tgrim);
        pirim.setText("Pengirim :\n" + prim);
        smnama.setText("Nama Surat :\n" + nasut);
        tgrat.setText("Tanggal Surat :\n" + tglrat);
        phal.setText("Perihal :\n" + prial);
        disp.setText("Disposisi :\n" + dispo);
        cat.setText("Catatan :\n" + cate);
        /*idsuk.setText("Id Surat :\n" + idsm);
        idjen.setText("Id Jenis :\n" + idjn);
        noag.setText("Nomer Agenda :\n" + noda);
        idif.setText("Id Sifat :\n" + idfat);
        idcam.setText("Id Macam :\n" + idmac);
        idser.setText("Id User :\n" + idus);*/
        Button diterima = (Button) findViewById(R.id.diterimaa);
        diterima.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(getApplicationContext(), DiterimaSMActivity.class);
                                            intent.putExtra("id_surat_masuk",idsm);
                                            intent.putExtra("disposisi",dispo);
                                            intent.putExtra("catatan",cate);
                                            startActivity(intent);
                                        }
        });
    }
}