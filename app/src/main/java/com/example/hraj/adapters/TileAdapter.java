package com.example.hraj.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hraj.R;
import com.example.hraj.TileDetailActivity;
import com.example.hraj.models.Theme;
import com.example.hraj.models.Tile;
import com.example.hraj.utils.CommonUtils;

import java.util.List;

public class TileAdapter extends RecyclerView.Adapter<TileAdapter.TileViewHolder> {

    private List<Tile> tileList;
    private Context context;
    private static TileAdapter instance;
    private Theme theme;

    public TileAdapter(Context context, List<Tile> tileList) {
        this.context = context;
        this.tileList = tileList;
    }

    // Private constructor to implement singleton pattern
    private TileAdapter(Context context, List<Tile> tileList, Theme theme) {
        this.context = context;
        this.tileList = tileList;
        this.theme = theme;
    }

    // Static method to get a single instance of the adapter
    public static TileAdapter getInstance(Context context, List<Tile> tileList, Theme theme) {
        if (instance == null) {
            instance = new TileAdapter(context, tileList, theme);
        }
        return instance;
    }

    // Metoda pro reset instance, pokud se seznam změní
    public static void resetInstance() {
        instance = null;
    }

    public static TileAdapter getInstance() {
        return instance;
    }

    @NonNull
    @Override
    public TileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tile_item, parent, false);
        return new TileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TileViewHolder holder, int position) {
        Tile tile = tileList.get(position);
        holder.titleTextView.setText(tile.getTitle());
        holder.numOfPlayers.setText(context.getString(R.string.players) + ": " + tile.getNumOfPlayers());
        holder.descriptionTextView.setText(tile.getShortDescription());

        if(theme != null) {
            // Apply theme colors to each tile
            holder.cardView.setCardBackgroundColor(CommonUtils.getColorResource(theme.getTilesBackground()));
            holder.titleTextView.setTextColor(CommonUtils.getColorResource(theme.getTilesTextColor()));
            holder.numOfPlayers.setTextColor(CommonUtils.getColorResource(theme.getTilesTextColor()));
            holder.descriptionTextView.setTextColor(CommonUtils.getColorResource(theme.getTilesTextColor()));
        }

        // click listener pro dlaždici
        holder.itemView.setOnClickListener(v -> {
//            Toast.makeText(v.getContext(), "Clicked on: " + tile.getTitle(), Toast.LENGTH_SHORT).show();

            // Vytvoření intentu pro spuštění nové aktivity
            Intent intent = new Intent(v.getContext(), TileDetailActivity.class);

            // Předání dat do intentu
            intent.putExtra("tileId", tile.getId());
            intent.putExtra("title", tile.getTitle());
            intent.putExtra("description", tile.getDescription());
            intent.putExtra("numOfPlayers", tile.getNumOfPlayers());

            // Start nové aktivity
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tileList.size();
    }

    static class TileViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, numOfPlayers, descriptionTextView;
        CardView cardView;

        public TileViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);

            titleTextView = itemView.findViewById(R.id.tileTitle);
            numOfPlayers = itemView.findViewById(R.id.numOfPlayers);
            descriptionTextView = itemView.findViewById(R.id.tileDescription);
        }
    }

    // Metoda pro aktualizaci seznamu dlaždic
    @SuppressLint("NotifyDataSetChanged")
    public void updateTileList(List<Tile> newTileList) {
        this.tileList.clear();
        this.tileList.addAll(newTileList);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateTheme(Theme newTheme) {
        Toast.makeText(context, "updateTheme", Toast.LENGTH_SHORT).show();
        this.theme = newTheme;
        notifyDataSetChanged();
    }
}
