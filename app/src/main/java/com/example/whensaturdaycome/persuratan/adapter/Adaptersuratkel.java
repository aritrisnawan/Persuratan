package com.example.whensaturdaycome.persuratan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.whensaturdaycome.persuratan.R;
import com.example.whensaturdaycome.persuratan.data.Suratkel;

import java.util.List;

public class Adaptersuratkel extends BaseAdapter {
    private Context contextt;
    private List<Suratkel> suratkelList;
    private LayoutInflater layoutInflater;

    public Adaptersuratkel (List<Suratkel> suratkelList, Context context){
        this.suratkelList = suratkelList;
        layoutInflater = LayoutInflater.from(context);
        this.contextt = context;
    }

    @Override
    public int getCount() {
        return suratkelList.size();
    }

    @Override
    public Object getItem(int position) {
        return suratkelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_suratkel,parent,false);
        TextView no_surat;
        TextView tujuan;
        no_surat = (TextView)convertView.findViewById(R.id.itemsuratkel);
        no_surat.setTextColor(Color.parseColor("#FF6C6B6B"));
        Suratkel suratkel=(Suratkel) getItem(position);
        no_surat.setText(suratkel.getNo_surat());

        tujuan = (TextView)convertView.findViewById(R.id.itemsuratkell);
        tujuan.setTextColor(Color.parseColor("#FF6C6B6B"));
        Suratkel suratkell=(Suratkel) getItem(position);
        tujuan.setText(suratkell.getTujuan());
        return convertView;
    }
}
