package com.example.hraj;

import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TileAdapter adapter;
    private List<Tile> tileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tileList = loadTiles();
        setUpRecyclerView();
        setUpSortingOptions();
    }

    /**
     * Nastavení RecyclerView - dynamičtější, modernější varianta od ListView
     */
    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TileAdapter(tileList);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Nastavení elementu pro možností řazení
     */
    private void setUpSortingOptions() {
        // Data pro ExpandableListView
        ExpandableListView expandableListView = findViewById(R.id.expandableListView);
        Map<String, List<String>> expandableListDetail = getData();
        List<String> expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        ExpandableListAdapter expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        // Akce po kliknutí na podskupinu (možnost třídění)
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            String selected = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);

            // Řazení podle vybrané možnosti
            if (selected.equals("Seřadit vzestupně podle počtu hráčů")) {
                Collections.sort(tileList, (tile1, tile2) -> tile1.getNumOfPlayers().compareTo(tile2.getNumOfPlayers()));
            } else if (selected.equals("Seřadit sestupně podle počtu hráčů")) {
                Collections.sort(tileList, (tile1, tile2) -> tile2.getNumOfPlayers().compareTo(tile1.getNumOfPlayers()));
            }

            // Aktualizace adapteru po řazení
            adapter.notifyDataSetChanged();
            return false; // = akce nebyla přerušena
        });
    }

    private Map<String, List<String>> getData() {
        Map<String, List<String>> expandableListDetail = new HashMap<>();
        List<String> sortingOptions = new ArrayList<>();
        sortingOptions.add("Seřadit vzestupně podle počtu hráčů");
        sortingOptions.add("Seřadit sestupně podle počtu hráčů");
        expandableListDetail.put("Možnosti řazení", sortingOptions);
        return expandableListDetail;
    }

    private List<Tile> loadTiles() {
        List<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile("Tile 1", "This is the description for tile 1", 5));
        tiles.add(new Tile("Tile 2", "This is the description for tile 2", 3));
        tiles.add(new Tile("Tile 3", "This is the description for tile 3", 7));
        tiles.add(new Tile("Tile 4", "This is the description for tile 4", 2));
        return tiles;
    }
}
