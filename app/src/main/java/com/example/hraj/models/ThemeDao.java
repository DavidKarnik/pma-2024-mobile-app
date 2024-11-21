package com.example.hraj.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ThemeDao {
    @Insert
    void insertTheme(Theme theme);

    @Update
    void update(Theme theme);

    @Delete
    void delete(Theme theme);

    // Načítání všech témat z databáze
    @Query("SELECT * FROM themes")
    List<Theme> getAllThemes();

    // Vkládání více témat do databáze
    @Insert
    void insertAll(Theme... themes);

    // Načtení téma podle ID
    @Query("SELECT * FROM themes WHERE id = :themeId")
    Theme getThemeById(int themeId);

    // Vymazání všech témat z databáze
    @Query("DELETE FROM themes")
    void deleteAllThemes();
}
