package com.example.hraj.handlers;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.hraj.R;
import com.example.hraj.adapters.TileAdapter;
import com.example.hraj.databinding.ActivityAddGameBinding;
import com.example.hraj.databinding.ActivityMainBinding;
import com.example.hraj.databinding.ActivitySettingsBinding;
import com.example.hraj.models.Theme;
import com.example.hraj.utils.CommonUtils;

import java.util.List;

public class ThemeHandler {
    // singleton
    private static ThemeHandler instance;

    private ActivitySettingsBinding settingsBinding;
    private ActivityAddGameBinding addGameBinding;
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
        String themeName = "Halloween";
        String themeTextFont = ""; // font
        String windowBackground = "@drawable/background_gradient_light_orange_red";
        String imageBack = "@drawable/icon_back_64_white";
        // toolbar
        String toolbarBackground = "@color/black";
        String toolbarTextColor = "@color/white";
        String logoImage = "@drawable/hraj_logo";
        // tiles
        String tilesBackground = "@color/black";
        String tilesTextColor = "@color/white";
        Theme theme = new Theme(themeName, themeTextFont, windowBackground, toolbarBackground, toolbarTextColor, logoImage, tilesBackground, tilesTextColor, imageBack);
        theme.setEditIcon("@drawable/icon_edit_100_white");
        theme.setDeleteIcon("@drawable/icon_delete_100_white");
        theme.setSearchIcon("@android:drawable/ic_menu_search");
        return theme;
    }

    public static void initSettingsBinging(ActivitySettingsBinding settingsBinding) {
        instance.settingsBinding = settingsBinding;
    }

//    public static void initAddGameBinging(ActivityAddGameBinding addGameBinding) {
//        instance.addGameBinding = addGameBinding;
//    }

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

        setUpSettingsTheme();

        setUpMainTheme();

        setUpTileTheme();
    }


    @SuppressLint("ResourceAsColor")
    private void resetBackgrounds() {
//        binding.thumbnailBasicBlueTheme.setBackgroundColor(R.color.white);
//        binding.thumbnailHalloweenTheme.setBackgroundColor(R.color.white);
        settingsBinding.thumbnailBasicBlueTheme.setBackground(null);
        settingsBinding.thumbnailHalloweenTheme.setBackground(null);
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

    private void setUpSettingsTheme() {
        settingsBinding.getRoot().setBackgroundResource(CommonUtils.getResourceId(activeTheme.getWindowBackground()));

        settingsBinding.toolbar.toolbar.setBackgroundResource(CommonUtils.getResourceId(activeTheme.getToolbarBackground()));
        settingsBinding.toolbar.toolbar.setTitleTextColor(CommonUtils.getColorResource(activeTheme.getToolbarTextColor()));

        settingsBinding.toolbar.logoImage.setImageResource(CommonUtils.getResourceId(activeTheme.getLogoImage()));
        settingsBinding.toolbar.backImage.setImageResource(CommonUtils.getResourceId(activeTheme.getImageBack()));
    }

    private void setUpMainTheme() {
        // Nastavení pozadí hlavní aktivity
        mainBinding.getRoot().setBackgroundResource(CommonUtils.getResourceId(activeTheme.getWindowBackground()));

        // Nastavení pozadí a textové barvy toolbaru
        mainBinding.toolbar.setBackgroundResource(CommonUtils.getResourceId(activeTheme.getToolbarBackground()));
        mainBinding.toolbar.setTitleTextColor(CommonUtils.getColorResource(activeTheme.getToolbarTextColor()));
        // menu_main.xml
//        mainBinding.toolbar.setPopupTheme(getResourceId(activeTheme.getToolbarBackground()))

        mainBinding.logoImage.setImageResource(CommonUtils.getResourceId(activeTheme.getLogoImage()));

//        mainBinding.searchIcon.setBackgroundColor(getColorResource(activeTheme.getToolbarTextColor()));
        mainBinding.searchIcon.setImageResource(CommonUtils.getResourceId(activeTheme.getSearchIcon()));
    }

    private void setUpTileTheme() {
        TileAdapter tileAdapter = TileAdapter.getInstance();
        if (tileAdapter != null) {
            tileAdapter.updateTheme(activeTheme);
        }
    }
}
