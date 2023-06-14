package com.example.al_quranku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.al_quranku.model.SurahModel.Chapter;
import com.example.al_quranku.model.SurahModel.ChaptersItem;
import com.example.al_quranku.retrofit.ApiServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MainAdapter mainAdapter;

    private RecyclerView recyclerView;

    private List<ChaptersItem> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataFromApi();

        setUpView();
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mainAdapter = new MainAdapter(results);
        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(mainAdapter);

    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerview);

    }

    private void getDataFromApi(){
        ApiServices.endPoint().getSurah().enqueue(new Callback<Chapter>() {
            @Override
            public void onResponse(Call<Chapter> call, Response<Chapter> response) {
                if(response.isSuccessful()){
                    List<ChaptersItem> result = response.body().getChapters();
                    Log.d("Main", result.toString());
                    mainAdapter.setData(result);
                }
            }

            @Override
            public void onFailure(Call<Chapter> call, Throwable t) {
                Log.d("ErrorMain", t.toString());

            }
        });
    }

}