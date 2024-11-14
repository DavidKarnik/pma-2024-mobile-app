package com.example.hraj;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hraj.databinding.ActivityAddGameBinding;
import com.example.hraj.handlers.AddGameHandler;

public class AddGameActivity extends AppCompatActivity {

    private ActivityAddGameBinding addGameBinding;
    private AddGameHandler addGameHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addGameBinding = ActivityAddGameBinding.inflate(getLayoutInflater());
        setContentView(addGameBinding.getRoot());

        addGameHandler = new AddGameHandler(addGameBinding, this);

        addGameBinding.btnSave.setOnClickListener(v -> addGameHandler.saveGame());

        addGameBinding.toolbar.backImage.setOnClickListener(v -> {
            finish();
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        addGameBinding = null; // Uvolnění bindingu pro prevenci úniku paměti
    }
}
