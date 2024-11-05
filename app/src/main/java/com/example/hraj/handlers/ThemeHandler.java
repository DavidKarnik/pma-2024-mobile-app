package com.example.hraj.handlers;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.hraj.R;
import com.example.hraj.adapters.TileAdapter;
import com.example.hraj.databinding.ActivityMainBinding;
import com.example.hraj.databinding.ActivitySettingsBinding;
import com.example.hraj.models.Theme;

import java.util.List;

public class ThemeHandler {
    // singleton
    private static ThemeHandler instance;

    private ActivitySettingsBinding settingsBinding;
    private Theme activeTheme;
    private List<Theme> themes;
    private ActivityMainBinding mainBinding;

    private ThemeHandler(ActivityMainBinding mainBinding, List<Theme> themes) {
        this.mainBinding = mainBinding;
        this.themes = themes;
        this.activeTheme = basicTheme();
    }

    public static ThemeHandler getInstance(ActivityMainBinding mainBinding, List<Theme> themes) {
        if (instance == null) {
            instance = new ThemeHandler(mainBinding, themes);
        }
        return instance;
    }

    private Theme basicTheme() {
        // whole app
        String themeName = "BasicBlue";
        String themeTextFont = ""; // font
        String windowBackground = "@drawable/background_gradient_basic_blue";
        // toolbar
        String toolbarBackground = "@color/black";
        String toolbarTextColor = "@color/white";
        // tiles
        String tilesBackground = "@color/black";
        String tilesTextColor = "@color/white";

        return new Theme(themeName, themeTextFont, windowBackground, toolbarBackground, toolbarTextColor, tilesBackground, tilesTextColor);
    }

    public static void initSettingsBinging(ActivitySettingsBinding settingsBinding) {
        instance.settingsBinding = settingsBinding;
    }

    public void themeSelected(String themeName) {
        Theme theme = findThemeByNameInList(this.themes, themeName);
        if (theme == null) {
            return;
        }

        setUpAppTheme(theme);

        applyBorderOfActiveTheme();
    }


    public Theme findThemeByNameInList(List<Theme> themeList, String name) {
        for (Theme theme : themeList) {
            if (name.equalsIgnoreCase(theme.getThemeName())) {
                return theme;
            }
        }
        return null;
    }

    public void setUpAppTheme(Theme theme) {
        activeTheme = theme;

        // Nastavení pozadí hlavní aktivity
        mainBinding.getRoot().setBackgroundResource(getResourceId(theme.getWindowBackground()));

        // Nastavení pozadí a textové barvy toolbaru
        mainBinding.toolbar.setBackgroundResource(getResourceId(theme.getToolbarBackground()));
        mainBinding.toolbar.setTitleTextColor(getColorResource(theme.getToolbarTextColor()));
        // menu_main.xml
//        mainBinding.toolbar.setPopupTheme(getResourceId(theme.getToolbarBackground()))

        mainBinding.searchIcon.setBackgroundColor(getColorResource(theme.getToolbarTextColor()));
        TileAdapter tileAdapter = TileAdapter.getInstance();
        if (tileAdapter != null) {
            tileAdapter.updateTheme(theme);
        }
    }


    @SuppressLint("ResourceAsColor")
    private void resetBackgrounds() {
//        binding.thumbnailBasicBlueTheme.setBackgroundColor(R.color.white);
//        binding.thumbnailHalloweenTheme.setBackgroundColor(R.color.white);
        settingsBinding.thumbnailBasicBlueTheme.setBackground(null);
        settingsBinding.thumbnailHalloweenTheme.setBackground(null);
    }

    // TODO - getIdentifier() není efektivní -> udělat jinak
    // Helper method for getting resource ID
    private int getResourceId(String resourceName) {
        return mainBinding.getRoot().getContext().getResources().getIdentifier(
                resourceName, "drawable", mainBinding.getRoot().getContext().getPackageName());
    }

    // Helper method for getting color ID
    private int getColorResource(String colorName) {
        return mainBinding.getRoot().getContext().getResources().getIdentifier(
                colorName, "color", mainBinding.getRoot().getContext().getPackageName());
    }

    public Theme getActiveTheme() {
        return activeTheme;
    }

    public void applyBorderOfActiveTheme() {
        Log.d("ThemeHandler", "Aktivní téma: " + this.activeTheme.getThemeName());
        resetBackgrounds();
        
        switch (this.activeTheme.getThemeName()) {
            case "BasicBlue":
                settingsBinding.thumbnailBasicBlueTheme.setBackgroundResource(R.drawable.selected_theme_border);
                break;
            case "Halloween":
                settingsBinding.thumbnailHalloweenTheme.setBackgroundResource(R.drawable.selected_theme_border);
                break;
        }
    }
}
