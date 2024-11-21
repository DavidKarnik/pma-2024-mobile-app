package com.example.hraj.handlers;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hraj.R;
import com.example.hraj.TileDetailActivity;
import com.example.hraj.databinding.ActivityTileDetailBinding;
import com.example.hraj.models.Theme;
import com.example.hraj.models.TileRepository;
import com.example.hraj.utils.CommonUtils;

public class TileDetailHandler {

    private ActivityTileDetailBinding tileDetailBinding;
    private TileDetailActivity context;
    private TileRepository tileRepository;
    private ThemeHandler themeHandler;

    public TileDetailHandler(ActivityTileDetailBinding tileDetailBinding, TileDetailActivity addGameActivity) {
        this.context = addGameActivity;
        this.tileDetailBinding = tileDetailBinding;

        tileRepository = TileRepository.getInstance(context);

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

    public void showEditDialog(String currentTitle, String currentShortDescription, String currentDescription, int currentNumOfPlayers) {
        // Načtení layoutu dialogu
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_tile, null);

        EditText editTitle = dialogView.findViewById(R.id.editTitle);
        EditText editShortDescription = dialogView.findViewById(R.id.editShortDescription);
        EditText editDescription = dialogView.findViewById(R.id.editDescription);
        EditText editNumOfPlayers = dialogView.findViewById(R.id.editNumOfPlayers);

        // Předvyplnění aktuálními hodnotami
        editTitle.setText(currentTitle);
        editShortDescription.setText(currentShortDescription);
        editDescription.setText(currentDescription);
        editNumOfPlayers.setText(String.valueOf(currentNumOfPlayers));

        // Vytvoření dialogu
        new AlertDialog.Builder(context)
                .setTitle("Edit Tile")
                .setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    // Získání hodnot z dialogu
                    String newTitle = editTitle.getText().toString();
                    String newDescription = editDescription.getText().toString();
                    int newNumOfPlayers = Integer.parseInt(editNumOfPlayers.getText().toString());

                    // Aktualizace UI v aktivitě přes binding
                    tileDetailBinding.detailTitle.setText(newTitle);
                    tileDetailBinding.detailDescription.setText(newDescription);
                    tileDetailBinding.detailNumOfPlayers.setText("Players: " + newNumOfPlayers);

                    // Další akce, např. uložení do databáze
                    saveEditedTile(newTitle, newDescription, newNumOfPlayers);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    public void saveEditedTile(String title, String description, int numOfPlayers) {
        // Např. uložení do databáze nebo jiné akce
        Toast.makeText(context, "Tile updated: " + title, Toast.LENGTH_SHORT).show();
    }


    public void editIconClicked(int id) {
        tileRepository.deleteTileByIdAsync(id, success -> {
            if (success) {
                Toast.makeText(context, "Hra s ID " + id + " byla vymazána!", Toast.LENGTH_SHORT).show();
                // updated Tile List -> spustí notifyDataHasChanged v main
                TileHandler.getInstance().clearTileList();
                context.finish(); // Zavření aktivity
            } else {
                Toast.makeText(context, "Nepodařilo se vymazat hru s ID " + id + ".", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
