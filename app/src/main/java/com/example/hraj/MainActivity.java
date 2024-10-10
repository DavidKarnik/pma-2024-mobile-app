package com.example.hraj;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hraj.adapters.TileAdapter;
import com.example.hraj.handlers.SearchHandler;
import com.example.hraj.handlers.SortingHandler;
import com.example.hraj.models.Tile;
import com.example.hraj.utils.TileLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TileAdapter adapter;
    private List<Tile> tileList;
    private SearchHandler searchHandler;
    private SortingHandler sortingHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add Toolbaru jako ActionBar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add akce pro tlačítko lupy (search)
        searchHandler = new SearchHandler(this);
        ImageView searchIcon = findViewById(R.id.search_icon);
        searchIcon.setOnClickListener(v -> searchHandler.showSearchDialog());

        // Načítání a nastavení tile listu
        TileLoader tileLoader = new TileLoader();
        tileList = tileLoader.loadTiles();
        setUpRecyclerView();

        // add možnosti řazení
        ExpandableListView expandableListView = findViewById(R.id.expandableListView);
        sortingHandler = new SortingHandler(this, tileList, adapter);
        sortingHandler.setUpSortingOptions(expandableListView);
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TileAdapter(this, tileList);
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.dropdown_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.option1) {
            Toast.makeText(this, getString(R.string.option1_selected), Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.option2) {
            Toast.makeText(this, getString(R.string.option2_selected), Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.option3) {
            Toast.makeText(this, getString(R.string.option3_selected), Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
