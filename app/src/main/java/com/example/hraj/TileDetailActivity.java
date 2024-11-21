package com.example.hraj;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hraj.databinding.ActivityTileDetailBinding;
import com.example.hraj.handlers.TileDetailHandler;

public class TileDetailActivity extends AppCompatActivity {

    private ActivityTileDetailBinding binding;
    private TileDetailHandler tileDetailHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTileDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tileDetailHandler = new TileDetailHandler(binding, this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String shortDescription = intent.getStringExtra("shortDescription");
        String description = intent.getStringExtra("description");
        int numOfPlayers = intent.getIntExtra("numOfPlayers", 0);

        binding.detailTitle.setText(title);
        binding.detailDescription.setText(description);
        binding.detailNumOfPlayers.setText("Players: " + numOfPlayers);

        binding.toolbar.backImage.setOnClickListener(v -> {
            finish();
        });

        binding.toolbar.editIcon.setOnClickListener(v -> {
            tileDetailHandler.showEditDialog(
                    title,
                    shortDescription,
                    description,
                    numOfPlayers
            );
        });


        binding.toolbar.deleteIcon.setOnClickListener(v -> {
            int id = intent.getIntExtra("tileId", -1);
            if (id == -1) {
                Toast.makeText(this, "ID není dostupné nebo je neplatné! (Výchozí hodnota -1)", Toast.LENGTH_SHORT).show();
                return;
            }
            tileDetailHandler.editIconClicked(id);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
