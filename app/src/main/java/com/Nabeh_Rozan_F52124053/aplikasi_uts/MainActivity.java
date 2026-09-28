package com.Nabeh_Rozan_F52124053.aplikasi_uts;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] nama  = {"Rendang", "Soto Betawi", "Gudeg", "Pempek", "Kaledo",
            "Coto Makassar", "Sate Madura", "Papeda Kuah Kuning"};
    String[] asal  = {"Sumatera Barat", "DKI Jakarta", "Yogyakarta", "Sumatera Selatan", "Sulawesi Tengah",
            "Sulawesi Selatan", "Jawa Timur", "Maluku & Papua"};
    String[] bahan = {"Daging sapi, santan, cabai", "Daging sapi, susu/santan, kentang", "Nangka muda, santan, gula merah",
            "Ikan tenggiri, sagu, cuko", "Tulang sapi, asam, cabai", "Daging & jeroan sapi, kacang tanah",
            "Daging ayam, bumbu kacang, kecap", "Sagu, ikan tongkol, kunyit"};
    String[] waktu = {"3 jam", "2 jam", "5 jam", "1,5 jam", "3 jam", "2,5 jam", "1 jam", "1 jam"};
    String[] level = {"Sulit", "Sedang", "Sulit", "Sedang", "Sedang", "Sedang", "Mudah", "Mudah"};
    String[] foto  = {"rendang", "soto_betawi", "gudeg", "pempek", "kaledo",
            "coto_makassar", "sate_madura", "papeda"};

    ResepAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Resep> data = new ArrayList<>();
        for (int i = 0; i < nama.length; i++) {
            data.add(new Resep(nama[i], asal[i], bahan[i], waktu[i], level[i], foto[i]));
        }

        ListView lv = findViewById(R.id.listResep);
        EditText etCari = findViewById(R.id.etCari);
        adapter = new ResepAdapter(this, data);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((p, v, pos, id) ->
                Toast.makeText(this, "Resep: " + ((Resep) adapter.getItem(pos)).nama, Toast.LENGTH_SHORT).show());

        etCari.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int a, int b, int c) {}
            @Override public void onTextChanged(CharSequence s, int a, int b, int c) { adapter.cari(s.toString()); }
            @Override public void afterTextChanged(Editable s) {}
        });
    }
}