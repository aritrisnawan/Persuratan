package com.example.whensaturdaycome.persuratan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.whensaturdaycome.persuratan.R;
import com.example.whensaturdaycome.persuratan.data.SuratMa;

import java.util.List;

public class Adaptersuratma extends BaseAdapter {
    private Context contextt;
    private List<SuratMa> suratmaList;
    private LayoutInflater layoutInflater;

    public Adaptersuratma (List<SuratMa> suratmaList, Context context){
        this.suratmaList = suratmaList;
        layoutInflater = LayoutInflater.from(context);
        this.contextt = context;
    }

    @Override
    public int getCount() {
        return suratmaList.size();
    }

    @Override
    public Object getItem(int position) {
        return suratmaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_suratma,parent,false);
        TextView nama_surat;
        TextView pengirim;
        nama_surat = (TextView)convertView.findViewById(R.id.itemsuratma);
        nama_surat.setTextColor(Color.parseColor("#FF6C6B6B"));
        SuratMa suratMa=(SuratMa) getItem(position);
        nama_surat.setText(suratMa.getNama_surat());

        pengirim = (TextView)convertView.findViewById(R.id.itemsuratmaa);
        pengirim.setTextColor(Color.parseColor("#FF6C6B6B"));
        SuratMa suratMaa=(SuratMa) getItem(position);
        pengirim.setText(suratMaa.getPengirim());
        return convertView;
    }
}
