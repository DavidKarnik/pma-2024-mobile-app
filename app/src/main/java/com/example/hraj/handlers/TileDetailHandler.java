package com.example.hraj.handlers;

import com.example.hraj.R;
import com.example.hraj.TileDetailActivity;
import com.example.hraj.databinding.ActivityTileDetailBinding;
import com.example.hraj.models.Theme;

public class TileDetailHandler {

    private ActivityTileDetailBinding tileDetailBinding;
    private TileDetailActivity context;

    private ThemeHandler themeHandler;

    public TileDetailHandler(ActivityTileDetailBinding addGameBinding, TileDetailActivity addGameActivity) {
        this.context = addGameActivity;
        this.tileDetailBinding = addGameBinding;

//        initAddGameBinging(addGameBinding);
        themeHandler = ThemeHandler.getInstance(null, null);

        applyTheme();
    }

    public void applyTheme() {
        Theme theme = themeHandler.getActiveTheme();
        tileDetailBinding.getRoot().setBackgroundResource(getResourceId(theme.getWindowBackground()));

        tileDetailBinding.toolbar.toolbar.setBackgroundResource(getResourceId(theme.getToolbarBackground()));
        tileDetailBinding.toolbar.toolbar.setTitleTextColor(getColorResource(theme.getToolbarTextColor()));

        tileDetailBinding.toolbar.logoImage.setImageResource(getResourceId(theme.getLogoImage()));
    }

    private int getColorResource(String colorName) {
        int colorId = context.getResources().getIdentifier(colorName, "color", context.getPackageName());
        if (colorId == 0) {
            // Pokud barva není nalezena, použijte výchozí bílou nebo jinou barvu
            return context.getResources().getColor(R.color.white, null);
        }
        return context.getResources().getColor(colorId, null);
    }


    private int getResourceId(String resourceName) {
        return context.getResources().getIdentifier(
                resourceName, "drawable", context.getPackageName());
    }
}
