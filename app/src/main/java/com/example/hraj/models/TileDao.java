package com.example.hraj.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TileDao {
    @Insert
    void insertTile(Tile tile);

    @Update
    void update(Tile tile);

    @Delete
    void delete(Tile tile);

    // Načítání všech dlaždic z databáze
    @Query("SELECT * FROM tiles")
    List<Tile> getAllTiles();

    // Vkládání více dlaždic do databáze
    @Insert
    void insertAll(Tile... tiles);

    // Načtení dlaždic podle ID
    @Query("SELECT * FROM tiles WHERE id = :tileId")
    Tile getTileById(int tileId);

    // Vymazání všech dlaždic z databáze
    @Query("DELETE FROM tiles")
    void deleteAllTiles();
}
