package com.example.hraj.handlers;

import android.widget.Toast;

import com.example.hraj.AddGameActivity;
import com.example.hraj.R;
import com.example.hraj.databinding.ActivityAddGameBinding;
import com.example.hraj.models.Theme;
import com.example.hraj.models.Tile;
import com.example.hraj.models.TileRepository;

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
                Toast.makeText(context, "Hra uložena!", Toast.LENGTH_SHORT).show();
                context.finish(); // Zavření aktivity
            } else {
                Toast.makeText(context, "Nepodařilo se uložit hru.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void applyTheme() {
        Theme theme = themeHandler.getActiveTheme();
//        addGameBinding.addGameLayout.setBackgroundColor(getColorResource(theme.getWindowBackground()));
//        addGameBinding.toolbar.toolbar.setBackgroundColor(getColorResource(theme.getToolbarBackground()));

        addGameBinding.getRoot().setBackgroundResource(getResourceId(theme.getWindowBackground()));

        addGameBinding.toolbar.toolbar.setBackgroundResource(getResourceId(theme.getToolbarBackground()));
        addGameBinding.toolbar.toolbar.setTitleTextColor(getColorResource(theme.getToolbarTextColor()));

        addGameBinding.toolbar.logoImage.setImageResource(getResourceId(theme.getLogoImage()));
    }

    // Helper method to get color resource by name
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
