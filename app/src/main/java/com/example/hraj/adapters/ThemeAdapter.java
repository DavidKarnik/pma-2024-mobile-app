package com.example.hraj.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hraj.R;
import com.example.hraj.models.Theme;

import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder> {

    private final List<Theme> themeList;
    private int selectedPosition = -1;

    public ThemeAdapter(List<Theme> themeList) {
        this.themeList = themeList;
    }

    @NonNull
    @Override
    public ThemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_theme_item, parent, false);
        return new ThemeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Theme theme = themeList.get(position);
        holder.themeImage.setImageResource(theme.getPreviewImage());

        if (position == selectedPosition) {
            holder.themeImage.setBackgroundResource(R.drawable.selected_theme_border);
        } else {
            holder.themeImage.setBackgroundResource(0);
        }

        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }

    static class ThemeViewHolder extends RecyclerView.ViewHolder {
        ImageView themeImage;

        public ThemeViewHolder(@NonNull View itemView) {
            super(itemView);
            themeImage = itemView.findViewById(R.id.themeImage);
        }
    }
}
