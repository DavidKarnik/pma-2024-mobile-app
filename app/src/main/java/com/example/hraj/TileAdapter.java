package com.example.hraj;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TileAdapter extends RecyclerView.Adapter<TileAdapter.TileViewHolder> {

    private List<Tile> tileList;

    public TileAdapter(List<Tile> tileList) {
        this.tileList = tileList;
    }

    @NonNull
    @Override
    public TileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_item, parent, false);
        return new TileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TileViewHolder holder, int position) {
        Tile tile = tileList.get(position);
        holder.titleTextView.setText(tile.getTitle());
        holder.numOfPlayers.setText("Players: " + tile.getNumOfPlayers());
        holder.descriptionTextView.setText(tile.getDescription());

        // click listener pro dlaždici
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Clicked on: " + tile.getTitle(), Toast.LENGTH_SHORT).show();

            // Vytvoření intentu pro spuštění nové aktivity
            Intent intent = new Intent(v.getContext(), TileDetailActivity.class);

            // Předání dat do intentu
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
        TextView titleTextView;
        TextView numOfPlayers;
        TextView descriptionTextView;

        public TileViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tileTitle);
            numOfPlayers = itemView.findViewById(R.id.numOfPlayers);
            descriptionTextView = itemView.findViewById(R.id.tileDescription);
        }
    }
}
