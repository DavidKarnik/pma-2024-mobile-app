package com.example.hraj;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TileDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_detail);

        TextView titleTextView = findViewById(R.id.detailTitle);
        TextView descriptionTextView = findViewById(R.id.detailDescription);
        TextView numOfPlayersTextView = findViewById(R.id.detailNumOfPlayers);

        // Získání dat z intentu
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        int numOfPlayers = intent.getIntExtra("numOfPlayers", 0);

        // Nastavení dat do TextView
        titleTextView.setText(title);
        descriptionTextView.setText(description);
        numOfPlayersTextView.setText("Players: " + numOfPlayers);
    }
}
