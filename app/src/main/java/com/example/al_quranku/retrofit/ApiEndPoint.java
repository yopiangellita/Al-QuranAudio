package com.example.al_quranku.retrofit;

import com.example.al_quranku.model.AyatModel.Verses;
import com.example.al_quranku.model.SurahModel.Chapter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {

   @GET("chapters?language=id")
    Call<Chapter> getSurah();

    @GET("quran/verses/uthmani")
    Call<Verses> getAyat(@Query("chapter_number") int id);

}
