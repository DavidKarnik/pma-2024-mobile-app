package com.example.hraj.handlers;

import android.view.View;

import com.example.hraj.R;
import com.example.hraj.TileDetailActivity;
import com.example.hraj.databinding.ActivityTileDetailBinding;
import com.example.hraj.models.Theme;
import com.example.hraj.utils.CommonUtils;

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
        showToolbarFunctionImages();
    }

    public void applyTheme() {
        Theme theme = themeHandler.getActiveTheme();
        tileDetailBinding.getRoot().setBackgroundResource(CommonUtils.getResourceId(theme.getWindowBackground()));

        tileDetailBinding.toolbar.toolbar.setBackgroundResource(CommonUtils.getResourceId(theme.getToolbarBackground()));
        tileDetailBinding.toolbar.toolbar.setTitleTextColor(CommonUtils.getColorResource(theme.getToolbarTextColor()));

        tileDetailBinding.toolbar.logoImage.setImageResource(CommonUtils.getResourceId(theme.getLogoImage()));
        tileDetailBinding.toolbar.backImage.setImageResource(CommonUtils.getResourceId(theme.getImageBack()));

    }

    private void showToolbarFunctionImages() {
        tileDetailBinding.toolbar.editIcon.setVisibility(View.VISIBLE);
        tileDetailBinding.toolbar.deleteIcon.setVisibility(View.VISIBLE);
    }
}
