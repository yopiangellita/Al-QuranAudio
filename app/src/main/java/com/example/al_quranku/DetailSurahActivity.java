package com.example.al_quranku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.al_quranku.model.AyatModel.Verses;
import com.example.al_quranku.model.AyatModel.VersesItem;
import com.example.al_quranku.retrofit.ApiServices;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailSurahActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private AdapterAyat adapterAyat;

    private List<VersesItem> results =new ArrayList<>();

    TextView textViewNameSimpleSurah;
    TextView textViewIDSurah;
    TextView textViewNameComplexSurah;
    TextView textViewNameArabicSurah;
    TextView textViewUrutanTurunSurah;
    TextView textViewTempatTurunSurah;
    TextView textViewJumlahAyatSurah;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);

        String nameSimple = getIntent().getStringExtra("name_simple");
        textViewNameSimpleSurah = findViewById(R.id.tvNamaSurah);
        textViewNameSimpleSurah.setText(nameSimple);

        int id = getIntent().getIntExtra("id", 1);
        textViewIDSurah = findViewById(R.id.ID);
        textViewIDSurah.setText("Surah Ke " + (id)+" Di Al-Qur'an");

        String nameComplex = getIntent().getStringExtra("name_complex");
        textViewNameComplexSurah = findViewById(R.id.tvNamaKompleks);
        textViewNameComplexSurah.setText ("("+(nameComplex)+")");

        String nameArabic = getIntent().getStringExtra("name_arabic");
        textViewNameArabicSurah = findViewById(R.id.tvNamaArab);
        textViewNameArabicSurah.setText(nameArabic);

        int revelationOrder = getIntent().getIntExtra("revelation_order", 1);
        textViewUrutanTurunSurah = findViewById(R.id.tvUrutan);
        textViewUrutanTurunSurah.setText("Dan Turun Diurutan ke : " + (revelationOrder));

        String revelationPlace = getIntent().getStringExtra("revelation_place");
        textViewTempatTurunSurah = findViewById(R.id.tvTurun);
        textViewTempatTurunSurah.setText("Surah Ini Diturunkan Di " + (revelationPlace));

        int versesCount = getIntent().getIntExtra("verses_count", 1);
        textViewJumlahAyatSurah = findViewById(R.id.tvJumlahAyat);
        textViewJumlahAyatSurah.setText((versesCount)+" Ayat ");

        setUpView();
        setUpRecyclerView();
        System.out.println(id);
        getDataFromApi(id);

    }

    private void setUpRecyclerView() {
        adapterAyat = new AdapterAyat(results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterAyat);
    }


    private void getDataFromApi(int id) {
        ApiServices.endPoint().getAyat(id).enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(Call<Verses> call, Response<Verses> response) {
                if (response.isSuccessful()) {
                    List<VersesItem> result = response.body().getVerses();
                    Log.d("Ayat", result.toString());
                    adapterAyat.setData(result);
                }
            }

            @Override
            public void onFailure(Call<Verses> call, Throwable t) {
                Log.d("Ayat", results.toString());
                adapterAyat.setData(results);
            }
        });
    }

    

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerViewAyat);
    }

}