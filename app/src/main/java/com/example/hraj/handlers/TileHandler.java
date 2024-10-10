package com.example.hraj.handlers;

import com.example.hraj.models.Tile;
import java.util.ArrayList;
import java.util.List;

public class TileHandler {
    private List<Tile> tileList;
    private List<Tile> originalTileList;

    public TileHandler(List<Tile> tileList) {
        this.tileList = tileList;
        this.originalTileList = new ArrayList<>(tileList); // Kopie původního seznamu
    }


    public List<Tile> getShownTileList() {
        return tileList;
    }

    // Vrátí aktuální seznam
    public List<Tile> getOriginalTileList() {
        return originalTileList;
    }

    // Resetuje tileList na původní seznam
    public void resetTileList() {
        tileList.clear();
        tileList.addAll(originalTileList);
    }

    // Kontrola, zda tileList odpovídá původnímu seznamu
    public boolean isTileListUnchanged() {
        return tileList.equals(originalTileList);
    }

    public boolean isTileListChanged() {
        return !tileList.equals(originalTileList);
    }

    // Aktualizuje tileList podle nového seznamu
    public void updateTileList(List<Tile> newTileList) {
        tileList.clear();
        tileList.addAll(newTileList);
    }
}
