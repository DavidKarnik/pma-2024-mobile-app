package com.example.hraj;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hraj.adapters.ThemeAdapter;
import com.example.hraj.databinding.ActivitySettingsBinding;
import com.example.hraj.handlers.ThemeHandler;
import com.example.hraj.utils.ThemeRepository;

public class SettingsActivity extends AppCompatActivity {

    private RecyclerView themeRecyclerView;
    private ThemeAdapter themeAdapter;
    private ThemeRepository themesRepository;
    private ThemeHandler themeHandler;
    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        themesRepository = new ThemeRepository(this);
        loadThemes();

        ImageView thumbnailBasicBlueTheme = findViewById(R.id.thumbnailBasicBlueTheme);
        ImageView thumbnailHalloweenTheme = findViewById(R.id.thumbnailHalloweenTheme);

        thumbnailBasicBlueTheme.setOnClickListener(v -> themeHandler.themeSelected("BasicBlue"));
        thumbnailHalloweenTheme.setOnClickListener(v -> themeHandler.themeSelected("Halloween"));

//        themeRecyclerView = findViewById(R.id.themeRecyclerView);
//        themeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//
//        List<Theme> themes = new ArrayList<>();
////        themes.add(new Theme("Light", R.drawable.light_theme_preview));
////        themes.add(new Theme("Dark", R.drawable.dark_theme_preview));
////        themes.add(new Theme("Blue", R.drawable.blue_theme_preview));
//
//        themeAdapter = new ThemeAdapter(themes);
//        themeRecyclerView.setAdapter(themeAdapter);
    }

    /**
     * Statické načtení témat apliakce - styl
     * TODO - Asynchronní načtení témat a nastavení závislých komponent - téma aplikace
     * TODO jako u načítání Tiles v mainActivity
     */
    private void loadThemes() {
        themeHandler = new ThemeHandler(binding, themesRepository.loadThemesStatic());
    }

}
