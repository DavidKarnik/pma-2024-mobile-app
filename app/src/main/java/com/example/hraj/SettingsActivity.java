package com.example.hraj;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hraj.adapters.ThemeAdapter;
import com.example.hraj.models.Theme;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private RecyclerView themeRecyclerView;
    private ThemeAdapter themeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

//        themeRecyclerView = findViewById(R.id.themeRecyclerView);
//        themeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//
//        List<Theme> themes = new ArrayList<>();
////        themes.add(new Theme("Light", R.drawable.light_theme_preview));
////        themes.add(new Theme("Dark", R.drawable.dark_theme_preview));
////        themes.add(new Theme("Blue", R.drawable.blue_theme_preview));
//
//        themeAdapter = new ThemeAdapter(themes);
//        themeRecyclerView.setAdapter(themeAdapter);
    }
}
