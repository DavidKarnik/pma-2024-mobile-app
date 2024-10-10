package com.example.hraj.handlers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.hraj.R;
import com.example.hraj.adapters.CustomExpandableListAdapter;
import com.example.hraj.adapters.TileAdapter;
import com.example.hraj.models.Tile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortingHandler {
    private List<Tile> tileList;
    private TileAdapter adapter;
    private Context context;

    public SortingHandler(Context context, List<Tile> tileList, TileAdapter adapter) {
        this.context = context;
        this.tileList = tileList;
        this.adapter = adapter;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setUpSortingOptions(ExpandableListView expandableListView) {
        Map<String, List<String>> expandableListDetail = getData();
        List<String> expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        ExpandableListAdapter expandableListAdapter = new CustomExpandableListAdapter(context, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            String selected = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
            if (selected.equals(context.getString(R.string.sort_ascending))) {
                tileList.sort(Comparator.comparing(Tile::getNumOfPlayers));
            } else if (selected.equals(context.getString(R.string.sort_descending))) {
                tileList.sort((tile1, tile2) -> tile2.getNumOfPlayers().compareTo(tile1.getNumOfPlayers()));
            }
            adapter.notifyDataSetChanged();
            return false;
        });
    }

    private Map<String, List<String>> getData() {
        Map<String, List<String>> expandableListDetail = new HashMap<>();
        List<String> sortingOptions = new ArrayList<>();
        sortingOptions.add(context.getString(R.string.sort_ascending));
        sortingOptions.add(context.getString(R.string.sort_descending));
        expandableListDetail.put(context.getString(R.string.sorting_options), sortingOptions);
        return expandableListDetail;
    }
}

