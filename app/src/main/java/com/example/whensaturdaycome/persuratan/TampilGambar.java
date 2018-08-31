package com.example.whensaturdaycome.persuratan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.whensaturdaycome.persuratan.data.SuratMa;

import java.util.ArrayList;
import java.util.List;

public class TampilGambar extends AppCompatActivity {
    private ImageView fl;
    String fls;
    List<SuratMa> suratMaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_gambar);

        suratMaList = new ArrayList<>();

        fl = (ImageView) findViewById(R.id.gambare);

        Bundle bundle= getIntent().getExtras();

        //fls     = bundle.getString("File");

        Glide.with(getApplicationContext())
                .load("http://192.168.43.186/apicoba/img/" + fls)
                .asBitmap()
                .thumbnail(0.5f)
                .error(R.drawable.ic_not_interested_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(fl);
    }
}
