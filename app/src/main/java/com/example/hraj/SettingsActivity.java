package com.example.hraj;

import static com.example.hraj.handlers.ThemeHandler.initSettingsBinging;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hraj.databinding.ActivitySettingsBinding;
import com.example.hraj.handlers.ThemeHandler;

public class SettingsActivity extends AppCompatActivity {

    private ThemeHandler themeHandler;
    private ActivitySettingsBinding settingsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        settingsBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(settingsBinding.getRoot());

        initSettingsBinging(settingsBinding);
        loadThemes();

        themeHandler.applyBorderOfActiveTheme();

        settingsBinding.thumbnailBasicBlueTheme.setOnClickListener(v -> themeHandler.themeSelected("BasicBlue"));
        settingsBinding.thumbnailHalloweenTheme.setOnClickListener(v -> themeHandler.themeSelected("Halloween"));
    }

    /**
     * Statické načtení témat apliakce - styl
     * TODO - Asynchronní načtení témat a nastavení závislých komponent - téma aplikace
     * TODO jako u načítání Tiles v mainActivity
     * TODO načítání Theme z databáze v main activity, nebo až tady ? -> teď je to v main
     */
    private void loadThemes() {
//        themeHandler = new ThemeHandler(binding, themesRepository.loadThemesStatic());
        // singleton, instance je zde null, vezme se instance aktuálního ThemeHandleru
        themeHandler = ThemeHandler.getInstance(null, null);
    }

}
