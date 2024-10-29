package com.example.hraj;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hraj.models.Tile;
import com.example.hraj.utils.TileRepository;

public class AddGameActivity extends AppCompatActivity {

    private TileRepository tileRepository;
    private EditText gameTitleInput, numOfPlayersInput, shortDescriptionInput, fullDescriptionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        tileRepository = new TileRepository(this);

        gameTitleInput = findViewById(R.id.gameTitle);
        numOfPlayersInput = findViewById(R.id.numOfPlayers);
        shortDescriptionInput = findViewById(R.id.shortDescription);
        fullDescriptionInput = findViewById(R.id.fullDescription);

        // Nastavení listeneru pro tlačítko uložit
        Button saveButton = findViewById(R.id.btnSave);
        saveButton.setOnClickListener(v -> saveGame());
    }

    private void saveGame() {
        String gameTitle = gameTitleInput.getText().toString();
        String shortDescription = shortDescriptionInput.getText().toString();
        String fullDescription = fullDescriptionInput.getText().toString();
        int numOfPlayers = Integer.parseInt(numOfPlayersInput.getText().toString());

        Tile newTile = new Tile(gameTitle, shortDescription, fullDescription, numOfPlayers);

        // Asynchronní uložení dlaždice do databáze
        tileRepository.insertTileAsync(newTile, success -> {
            if (success) {
                Toast.makeText(AddGameActivity.this, "Hra uložena!", Toast.LENGTH_SHORT).show();
                finish(); // Zavření aktivity
            } else {
                Toast.makeText(AddGameActivity.this, "Nepodařilo se uložit hru.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
