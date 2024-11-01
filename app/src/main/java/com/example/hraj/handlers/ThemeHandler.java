package com.example.hraj.handlers;

import android.annotation.SuppressLint;

import com.example.hraj.R;
import com.example.hraj.databinding.ActivitySettingsBinding;
import com.example.hraj.models.Theme;

import java.util.List;

public class ThemeHandler {

    Theme activeTheme = basicTheme();
    private List<Theme> themes;
    private final ActivitySettingsBinding binding;

    public ThemeHandler(ActivitySettingsBinding binding, List<Theme> themes) {
        this.themes = themes;
        this.binding = binding;

//        context.setTheme(R.style.Theme_Hraj);
// Step 3: Set the background and text color dynamically
//        View tileView = findViewById(R.id.tileView);
//        tileView.setBackgroundColor(ContextCompat.getColor(contextThemeWrapper, R.color.black));
//        TextView tileTextView = findViewById(R.id.tileTextView);
//        tileTextView.setTextColor(ContextCompat.getColor(contextThemeWrapper, R.color.white));
//
//        View toolbarView = findViewById(R.id.toolbarView);
//        toolbarView.setBackgroundColor(ContextCompat.getColor(contextThemeWrapper, R.color.black));
//        TextView toolbarTextView = findViewById(R.id.toolbarTextView);
//        toolbarTextView.setTextColor(ContextCompat.getColor(contextThemeWrapper, R.color.white));
    }

    private Theme basicTheme() {
        // whole app
        String themeName = "Basic";
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

    public void themeSelected(String themeName) {
        resetBackgrounds();

        switch (themeName) {
            case "BasicBlue":
                binding.thumbnailBasicBlueTheme.setBackgroundResource(R.drawable.selected_theme_border);
                break;
            case "Halloween":
                binding.thumbnailHalloweenTheme.setBackgroundResource(R.drawable.selected_theme_border);
                break;
        }
        Theme theme = findThemeByNameInList(this.themes, themeName);
        setUpAppTheme(theme);
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
    }

    @SuppressLint("ResourceAsColor")
    private void resetBackgrounds() {
//        binding.thumbnailBasicBlueTheme.setBackgroundColor(R.color.white);
//        binding.thumbnailHalloweenTheme.setBackgroundColor(R.color.white);
        binding.thumbnailBasicBlueTheme.setBackground(null);
        binding.thumbnailHalloweenTheme.setBackground(null);
    }
}
