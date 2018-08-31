package com.example.whensaturdaycome.persuratan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.whensaturdaycome.persuratan.R;
import com.example.whensaturdaycome.persuratan.data.Informa;

import java.util.List;

public class Adapterinforma extends BaseAdapter {
    private Context contextt;
    private List<Informa> informaList;
    private LayoutInflater layoutInflater;

    public Adapterinforma (List<Informa> informaList, Context context) {
        this.informaList = informaList;
        layoutInflater = LayoutInflater.from(context);
        this.contextt = context;
    }

    @Override
    public int getCount() {
        return informaList.size();
    }

    @Override
    public Object getItem(int position) {
        return informaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_informasi,parent,false);
        TextView judul;
        judul = (TextView)convertView.findViewById(R.id.iteminformaa);
        judul.setTextColor(Color.parseColor("#FF6C6B6B"));
        Informa informa=(Informa) getItem(position);
        judul.setText(informa.getJudul());
        return convertView;
    }
}
