package com.Nabeh_Rozan_F52124053.aplikasi_uts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResepAdapter extends BaseAdapter {
    private final Context context;
    private final List<Resep> semua;
    private final List<Resep> tampil;

    public ResepAdapter(Context context, List<Resep> data) {
        this.context = context;
        this.semua = data;
        this.tampil = new ArrayList<>(data);
    }
    public void cari(String kata) {
        tampil.clear();
        String k = kata.toLowerCase().trim();
        for (Resep r : semua) {
            if (r.nama.toLowerCase().contains(k)
                    || r.asal.toLowerCase().contains(k)
                    || r.bahan.toLowerCase().contains(k)) {
                tampil.add(r);
            }
        }
        notifyDataSetChanged();
    }

    @Override public int getCount() { return tampil.size(); }
    @Override public Object getItem(int i) { return tampil.get(i); }
    @Override public long getItemId(int i) { return i; }

    static class Holder {
        ImageView img;
        TextView nama, asal, bahan, info;
    }

    @Override
    public View getView(int pos, View v, ViewGroup parent) {
        Holder h;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.item_resep, parent, false);
            h = new Holder();
            h.img = v.findViewById(R.id.imgResep);
            h.nama = v.findViewById(R.id.tvNama);
            h.asal = v.findViewById(R.id.tvAsal);
            h.bahan = v.findViewById(R.id.tvBahan);
            h.info = v.findViewById(R.id.tvInfo);
            v.setTag(h);
        } else {
            h = (Holder) v.getTag();
        }

        Resep r = tampil.get(pos);
        h.nama.setText(r.nama);
        h.asal.setText("Asal: " + r.asal);
        h.bahan.setText("Bahan utama: " + r.bahan);
        h.info.setText(r.waktu + "  |  Level: " + r.level);

        int id = context.getResources().getIdentifier(r.foto, "drawable", context.getPackageName());
        h.img.setImageResource(id != 0 ? id : R.drawable.ic_food_placeholder);
        return v;
    }
}