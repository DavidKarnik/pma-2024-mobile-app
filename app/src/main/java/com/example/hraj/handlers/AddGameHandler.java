package com.example.hraj.handlers;

import android.util.Log;

import com.example.hraj.AddGameActivity;
import com.example.hraj.databinding.ActivityAddGameBinding;
import com.example.hraj.models.Theme;
import com.example.hraj.models.Tile;
import com.example.hraj.models.TileRepository;
import com.example.hraj.utils.CommonUtils;

public class AddGameHandler {

    private ActivityAddGameBinding addGameBinding;
    private AddGameActivity context;
    private TileRepository tileRepository;
    private ThemeHandler themeHandler;

    public AddGameHandler(ActivityAddGameBinding addGameBinding, AddGameActivity addGameActivity) {
        this.context = addGameActivity;
        this.addGameBinding = addGameBinding;

        tileRepository = new TileRepository(addGameActivity);

//        initAddGameBinging(addGameBinding);
        themeHandler = ThemeHandler.getInstance(null, null);

        applyTheme();
    }

    public void saveGame() {
        String gameTitle = addGameBinding.gameTitle.getText().toString();
        String shortDescription = addGameBinding.shortDescription.getText().toString();
        String fullDescription = addGameBinding.fullDescription.getText().toString();
        int numOfPlayers = Integer.parseInt(addGameBinding.numOfPlayers.getText().toString());

        Tile newTile = new Tile(gameTitle, shortDescription, fullDescription, numOfPlayers);

        // Asynchronní uložení dlaždice do databáze
        tileRepository.insertTileAsync(newTile, success -> {
            if (success) {
                // toto běží asynchronně na jiném jádře než hlavním uni .. nejde volat Toast -> chyba
//                Toast.makeText(context, "Hra uložena!", Toast.LENGTH_SHORT).show();
                Log.d("ADDGAME", "Game added successfully!");
                // spustí dataHasChanged v main aktivitě
                TileHandler.getInstance().clearTileList();
                context.finish(); // Zavření aktivity
            } else {
//                Toast.makeText(context, "Nepodařilo se uložit hru.", Toast.LENGTH_SHORT).show();
                Log.e("ADDGAME", "Game added failed!");
            }
        });
    }

    public void applyTheme() {
        Theme theme = themeHandler.getActiveTheme();
//        addGameBinding.addGameLayout.setBackgroundColor(CommonUtils.getColorResource(theme.getWindowBackground()));
//        addGameBinding.toolbar.toolbar.setBackgroundColor(CommonUtils.getColorResource(theme.getToolbarBackground()));

        addGameBinding.getRoot().setBackgroundResource(CommonUtils.getResourceId(theme.getWindowBackground()));

        addGameBinding.toolbar.toolbar.setBackgroundResource(CommonUtils.getResourceId(theme.getToolbarBackground()));
        addGameBinding.toolbar.toolbar.setTitleTextColor(CommonUtils.getColorResource(theme.getToolbarTextColor()));

        addGameBinding.toolbar.logoImage.setImageResource(CommonUtils.getResourceId(theme.getLogoImage()));
        addGameBinding.toolbar.backImage.setImageResource(CommonUtils.getResourceId(theme.getImageBack()));
    }
}
