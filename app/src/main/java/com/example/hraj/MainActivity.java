package com.example.hraj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hraj.adapters.TileAdapter;
import com.example.hraj.databinding.ActivityMainBinding;
import com.example.hraj.handlers.SearchHandler;
import com.example.hraj.handlers.SortingHandler;
import com.example.hraj.handlers.ThemeHandler;
import com.example.hraj.handlers.TileHandler;
import com.example.hraj.models.ThemeRepository;
import com.example.hraj.models.TileRepository;


public class MainActivity extends AppCompatActivity {

    private TileRepository tileRepository;
    private TileAdapter tileAdapter;
    private TileHandler tileHandler;
    private SearchHandler searchHandler;
    private SortingHandler sortingHandler;
    private ThemeRepository themeRepository;
    private ThemeHandler themeHandler;
    private ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        setUpToolbar();

        // init TileRepository
        tileRepository = new TileRepository(this);
        // *asynchronní* načtení dlaždic a nastavení komponent
        loadTiles();

        // themes
        themeRepository = ThemeRepository.getInstance(getApplicationContext());
        themeHandler = ThemeHandler.getInstance(mainBinding, themeRepository.loadThemesStatic());
    }

    private void setUpToolbar() {
        // balík androidx. appcompat. app. AppCompatActivity
        setSupportActionBar(mainBinding.toolbar);

        // Skrytí názvu aplikace v toolbaru
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * Asynchronní načtení dlaždic a nastavení závislých komponent
     * Kaskádové použití handleru -> setUps musejí jít po sobě, PO načtení dlaždic z databáze
     */
    private void loadTiles() {
        tileRepository.loadTilesAsync(resultLoadedTiles -> {
            // Nastavení TileHandleru
            tileHandler = new TileHandler(resultLoadedTiles);

            // závislé funkce
            setUpRecyclerView();
            setUpSearchHandler();
            setUpSortingHandler();
        });
    }

    /**
     * Nastavení recycler view pro vykreslení dlaždic
     * Init tileAdapteru důležitý - kaskádové použití
     */
    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        tileAdapter = new TileAdapter(this, tileHandler.getShownTileList());
        tileAdapter = TileAdapter.getInstance(this, tileHandler.getShownTileList(), themeHandler.getActiveTheme());
        recyclerView.setAdapter(tileAdapter);
    }

    /**
     * Nastavení handleru pro vyhledávání dlaždic (her) dle jejich názvu
     */
    private void setUpSearchHandler() {
        searchHandler = new SearchHandler(this, tileHandler.getOriginalTileList(), tileAdapter);
        mainBinding.searchIcon.setOnClickListener(v -> searchHandler.showSearchDialog());
    }

    /**
     * Nastavení handleru pro řazení dlaždic
     */
    private void setUpSortingHandler() {
        ExpandableListView expandableListView = findViewById(R.id.expandableListView);
        sortingHandler = new SortingHandler(this, tileHandler.getShownTileList(), tileAdapter);
        sortingHandler.setUpSortingOptions(expandableListView);
    }


    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.layout.menu_dropdown_options, menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addGame) {
            Toast.makeText(this, getString(R.string.option1_selected), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AddGameActivity.class);
//            intent.putExtra("tileLoader", tileLoader);
            startActivity(intent);
            return true;
        } else if (id == R.id.option2) {
            Toast.makeText(this, getString(R.string.option2_selected), Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.settings) {
            Toast.makeText(this, getString(R.string.option3_selected), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.about) {
            Toast.makeText(this, "O aplikaci zvoleno", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        // +tileHandler != null -> inicializuje se asynchronně, takže kontrola, zda už je inicializován
        if (tileHandler != null && tileHandler.isTileListChanged()) {
            tileHandler.resetTileList();
            tileAdapter.notifyDataSetChanged(); // Upozornění adapteru, že se data změnila
        }
    }
}
