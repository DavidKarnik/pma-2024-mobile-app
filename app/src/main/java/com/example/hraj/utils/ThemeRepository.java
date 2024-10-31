package com.example.hraj.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.hraj.models.Theme;
import com.example.hraj.models.ThemeDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThemeRepository {
    private AppDatabase appDatabase;
    private ThemeDao themeDao;
    private Context context;
    private ExecutorService executorService;
    private Handler mainHandler;

    public ThemeRepository(Context context) {
        this.context = context;
        appDatabase = AppDatabase.getInstance(context);
        themeDao = appDatabase.themeDao();
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());

//        populateThemeDatabase();
    }

    private void populateThemeDatabase() {
        List<Theme> staticThemes = loadThemesStatic();
        for (Theme theme : staticThemes) {
            executorService.execute(() -> themeDao.insertTheme(theme));
        }
    }

    private List<Theme> loadThemesStatic() {
        List<Theme> themeList = new ArrayList<>();
        // whole app
        String themeName = "Halloween";
        String themeTextFont = ""; // font
        String windowBackground = "@drawable/background_gradient_light_orange_red";
        // toolbar
        String toolbarBackground = "@color/black";
        String toolbarTextColor = "@color/white";
        // tiles
        String tilesBackground = "@color/black";
        String tilesTextColor = "@color/white";
        themeList.add(new Theme(themeName, themeTextFont, windowBackground, toolbarBackground, toolbarTextColor, tilesBackground, tilesTextColor));

        return themeList;
    }
}
