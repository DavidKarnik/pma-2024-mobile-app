package com.example.hraj;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

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

        setupLiveTextEditedListeners();
    }

    private void setupLiveTextEditedListeners() {
        // Naslouchej změnám textu
        addGameBinding.gameTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addGameBinding.tileItem.tileTitle.setText(s.toString());
//                addGameBinding.tileItem.tileDescription.setText(s);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        addGameBinding.numOfPlayers.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addGameBinding.tileItem.numOfPlayers.setText(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        addGameBinding.shortDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addGameBinding.tileItem.tileDescription.setText(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addGameBinding = null; // Uvolnění bindingu pro prevenci úniku paměti
    }
}
