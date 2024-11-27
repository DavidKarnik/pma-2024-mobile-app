package com.example.hraj.models;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.hraj.utils.AppDatabase;

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
    private static ThemeRepository instance;

    public ThemeRepository(Context context) {
        this.context = context.getApplicationContext(); // Use application context -> prevent memory leaks
        appDatabase = AppDatabase.getInstance(context);
        themeDao = appDatabase.themeDao();
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
//        populateThemeDatabase();
    }

    public static ThemeRepository getInstance(Context context) {
        if (instance == null) {
            instance = new ThemeRepository(context);
        }
        return instance;
    }

    private void populateThemeDatabase() {
        List<Theme> staticThemes = loadThemesStatic();
        for (Theme theme : staticThemes) {
            executorService.execute(() -> themeDao.insertTheme(theme));
        }
    }

    public List<Theme> loadThemesStatic() {
        List<Theme> themeList = new ArrayList<>();
        // whole app
        String themeName = "Halloween";
        String themeTextFont = ""; // font
        String windowBackground = "@drawable/background_gradient_light_orange_red";
        String imageBack = "@drawable/icon_back_64_white";
        // toolbar
        String toolbarBackground = "@color/black";
        String toolbarTextColor = "@color/white";
        String logoImage = "@drawable/hraj_logo";
        String searchImage = "@android:drawable/ic_menu_search";
        // tiles
        String tilesBackground = "@color/black";
        String tilesTextColor = "@color/white";
        Theme theme = new Theme(themeName, themeTextFont, windowBackground, toolbarBackground, toolbarTextColor, logoImage, tilesBackground, tilesTextColor, imageBack);
        theme.setEditIcon("@drawable/icon_edit_100_white");
        theme.setDeleteIcon("@drawable/icon_delete_100_white");
        theme.setSearchIcon(searchImage);
        themeList.add(theme);

        // whole app
        themeName = "BasicBlue";
        themeTextFont = ""; // font
        windowBackground = "@drawable/background_gradient_basic_blue";
        imageBack = "@drawable/icon_back_64";
        // toolbar
        toolbarBackground = "@color/white";
        toolbarTextColor = "@color/black";
        logoImage = "@drawable/hraj_logo_black";
        searchImage = "@drawable/search_icon_black";
        // tiles
        tilesBackground = "@color/white";
        tilesTextColor = "@color/black";
        theme = new Theme(themeName, themeTextFont, windowBackground, toolbarBackground, toolbarTextColor, logoImage, tilesBackground, tilesTextColor, imageBack);
        theme.setEditIcon("@drawable/icon_edit_100");
        theme.setDeleteIcon("@drawable/icon_delete_100");
        theme.setSearchIcon(searchImage);
        themeList.add(theme);

        return themeList;
    }
}
