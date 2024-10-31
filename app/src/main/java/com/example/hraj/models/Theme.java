package com.example.hraj.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "themes")
public class Theme {
    @PrimaryKey(autoGenerate = true)
    public int id;
    // whole app
    public String themeName;
    public String themeTextFont;
    public String windowBackground;
    // toolbar
    public String toolbarBackground;
    public String toolbarTextColor;
    // tiles
    public String tilesBackground;
    public String tilesTextColor;

    public Theme(String themeName, String themeTextFont, String windowBackground, String toolbarBackground, String toolbarTextColor, String tilesBackground, String tilesTextColor) {
        this.themeName = themeName;
        this.themeTextFont = themeTextFont;
        this.windowBackground = windowBackground;
        this.toolbarBackground = toolbarBackground;
        this.toolbarTextColor = toolbarTextColor;
        this.tilesBackground = tilesBackground;
        this.tilesTextColor = tilesTextColor;
    }

    public String getThemeName() {
        return themeName;
    }

    public String getThemeTextFont() {
        return themeTextFont;
    }

    public String getWindowBackground() {
        return windowBackground;
    }

    public String getToolbarBackground() {
        return toolbarBackground;
    }

    public String getToolbarTextColor() {
        return toolbarTextColor;
    }

    public String getTilesBackground() {
        return tilesBackground;
    }

    public String getTilesTextColor() {
        return null;
    }

    public int getPreviewImage() {
        return 0;
    }
}
