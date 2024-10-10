package com.example.hraj.handlers;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hraj.R;
import com.example.hraj.adapters.TileAdapter;
import com.example.hraj.models.Tile;

import java.util.ArrayList;
import java.util.List;

public class SearchHandler {
    private List<Tile> originalTileList; // Původní seznam dlaždic
    private TileAdapter adapter;
    private Context context;

    public SearchHandler(Context context, List<Tile> tileList, TileAdapter adapter) {
        this.context = context;
        this.originalTileList = tileList;
        this.adapter = adapter;
    }

    public void showSearchDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.search_title));

        final EditText input = new EditText(context);
        builder.setView(input);

        builder.setPositiveButton(context.getString(R.string.search), (dialog, which) -> {
            String query = input.getText().toString().toLowerCase().trim();
            searchFor(query);
        });
        builder.setNegativeButton(context.getString(R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void searchFor(String query) {
        // Filtrování dlaždic na základě názvu (title)
        List<Tile> filteredTiles = new ArrayList<>();
        for (Tile tile : originalTileList) {
            if (tile.getTitle().toLowerCase().contains(query)) {
                filteredTiles.add(tile);
            }
        }

        // Aktualizace seznamu v adaptéru s filtrovanými výsledky
        adapter.updateTileList(filteredTiles);

        // Zobrazení zprávy, pokud žádná dlaždice neodpovídá vyhledávání
        if (filteredTiles.isEmpty()) {
//            adapter.resetTileList();
            Toast.makeText(context, context.getString(R.string.no_results_found), Toast.LENGTH_SHORT).show();
        }
    }
}
