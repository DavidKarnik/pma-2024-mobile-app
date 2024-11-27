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
    public String imageBack;
    // toolbar
    public String toolbarBackground;
    public String toolbarTextColor;
    public String logoImage;
    public String searchIcon;
    public String editIcon;
    public String deleteIcon;
    // tiles
    public String tilesBackground;
    public String tilesTextColor;

    public Theme(String themeName, String themeTextFont, String windowBackground, String toolbarBackground, String toolbarTextColor, String logoImage, String tilesBackground, String tilesTextColor, String imageBack) {
        this.themeName = themeName;
        this.themeTextFont = themeTextFont;
        this.windowBackground = windowBackground;
        this.toolbarBackground = toolbarBackground;
        this.toolbarTextColor = toolbarTextColor;
        this.logoImage = logoImage;
        this.tilesBackground = tilesBackground;
        this.tilesTextColor = tilesTextColor;
        this.imageBack = imageBack;
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

    public String getLogoImage() {
        return logoImage;
    }

    public String getSearchIcon() {
        return searchIcon;
    }

    public void setSearchIcon(String searchIcon) {
        this.searchIcon = searchIcon;
    }

    public String getTilesBackground() {
        return tilesBackground;
    }

    public String getTilesTextColor() {
        return tilesTextColor;
    }

    public int getPreviewImage() {
        return 0;
    }

    public String getImageBack() {
        return imageBack;
    }

    public String getEditIcon() {
        return editIcon;
    }

    public void setEditIcon(String editIcon) {
        this.editIcon = editIcon;
    }

    public String getDeleteIcon() {
        return deleteIcon;
    }

    public void setDeleteIcon(String deleteIcon) {
        this.deleteIcon = deleteIcon;
    }
}
