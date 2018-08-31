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

public class PilihSuratKActivity extends AppCompatActivity {
    private TextView idsuk;
    private TextView idkok;
    private TextView tgsr;
    private TextView nsr;
    private TextView tuj;
    private TextView pri;
    private TextView tmb;
    private TextView cta;
    private ImageView fls;

    String idsk;
    String idkk;
    String tglsur;
    String nosur;
    String tja;
    String phal;
    String tmbs;
    String cata;
    String fl;
    List<SuratMa> suratkelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_surat_k);

        suratkelList = new ArrayList<>();

        tgsr  =(TextView)findViewById(R.id.tglsr);
        nsr  =(TextView)findViewById(R.id.nosr);
        tuj   =(TextView)findViewById(R.id.tuja);
        pri   =(TextView) findViewById(R.id.peri);
        tmb    =(TextView)findViewById(R.id.tembs);
        cta    =(TextView)findViewById(R.id.catat);
        /*idsuk  =(TextView)findViewById(R.id.idskel);
        idkok  =(TextView)findViewById(R.id.idkpok);*/
        fls   =(ImageView) findViewById(R.id.gambar);
        fls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PilihSuratKActivity.this, TampilGambar.class);
                startActivity(intent);
            }
        });

        Bundle bundle= getIntent().getExtras();

        tglsur   = bundle.getString("Tgl_surat");
        nosur   = bundle.getString("No_surat");
        tja   = bundle.getString("Tujuan");
        phal   = bundle.getString("Perihal");
        tmbs   = bundle.getString("Tembusan");
        cata   = bundle.getString("Catatan");
        idsk   = bundle.getString("Id_surat_keluar");
        idkk   = bundle.getString("Id_kelompok");
        fl     = bundle.getString("File");
        Glide.with(getApplicationContext())
                .load("http://192.168.43.186/apicoba/img/" + fl)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_not_interested_black_24dp)
                .override(300, 500)
        	    .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(fls);

        tgsr.setText("Tanggal Surat :\n"+tglsur);
        nsr.setText("Nomer Surat :\n"+nosur);
        tuj.setText("Tujuan :\n"+tja);
        pri.setText("Perihal :\n"+phal);
        tmb.setText("Tembusan :\n"+tmbs);
        cta.setText("Catatan :\n"+cata);
        /*idsuk.setText("Id Surat :\n"+idsk);
        idkok.setText("Id Kelompok :\n"+idkk);*/
    }
}
