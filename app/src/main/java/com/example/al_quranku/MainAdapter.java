package com.example.al_quranku;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.al_quranku.model.SurahModel.ChaptersItem;


import java.util.List;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private List<ChaptersItem> results;

    public MainAdapter(List<ChaptersItem> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);


        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder holder, int position) {
        final ChaptersItem chapters = results.get(position);

        holder.textViewSurahLatin.setText(chapters.getNameSimple());
        holder.textViewTerjemahanSurah.setText(chapters.getTranslatedName().getName());
        holder.textViewSurahArab.setText(chapters.getNameArabic());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailSurahActivity.class);
                intent.putExtra("name_simple", chapters.getNameSimple());
                intent.putExtra("id", chapters.getId());
                intent.putExtra("name_arabic", chapters.getNameArabic());
                intent.putExtra("name_complex", chapters.getNameComplex());
                intent.putExtra("revelation_order", chapters.getRevelationOrder());
                intent.putExtra("revelation_place", chapters.getRevelationPlace());
                intent.putExtra("verses_count", chapters.getVersesCount());

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSurahLatin, textViewTerjemahanSurah, textViewSurahArab;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSurahLatin = itemView.findViewById(R.id.tvsurahlatin);
            textViewTerjemahanSurah = itemView.findViewById(R.id.tvterjemahansurah);
            textViewSurahArab = itemView.findViewById(R.id.tvsuraharab);
        }
    }

    public void setData(List<ChaptersItem> data ){
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }
}