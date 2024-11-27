package com.example.hraj.handlers;

import com.example.hraj.models.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileHandler {
    private static TileHandler instance; // Singleton instance

    private List<Tile> tileList;        // Aktuální seznam
    private List<Tile> originalTileList; // Původní (originální) seznam

    // Soukromý konstruktor pro singleton
    private TileHandler() {
        this.tileList = new ArrayList<>();
        this.originalTileList = new ArrayList<>();
    }

    // Metoda pro získání instance (Singleton pattern)
    public static TileHandler getInstance() {
        if (instance == null) {
            instance = new TileHandler();
        }
        return instance;
    }

    // Inicializace s počátečním seznamem (volat pouze jednou, např. v MainActivity)
    public void initialize(List<Tile> initialTileList) {
        if (originalTileList.isEmpty()) { // Pouze pokud není seznam již inicializován
            this.tileList = new ArrayList<>(initialTileList);
            this.originalTileList = new ArrayList<>(initialTileList);
        }
    }

    // Získání aktuálního seznamu
    public List<Tile> getShownTileList() {
        return tileList;
    }

    // Získání originálního seznamu
    public List<Tile> getOriginalTileList() {
        return originalTileList;
    }

    // Resetování aktuálního seznamu na původní stav
    public void resetTileList() {
        tileList.clear();
        tileList.addAll(originalTileList);
    }

    // Vyčištění aktuálního seznamu
    // TODO ošetření stavu -> když je databáze po startu úplně prázdná a přidá se hra (Tile)
    //  zavolá se toto, ale oba listy jsou tedy stále prázdné, nedojde k refresh recyclerview
    //  a nezobrazí se ta první nově přidaná Tile
    public void clearTileList() {
        tileList.clear();
    }

    // Kontrola, zda došlo ke změnám v seznamu
    public boolean isTileListChanged() {
        return !tileList.equals(originalTileList);
    }

    // Aktualizace aktuálního seznamu
    public void updateTileList(List<Tile> newTileList) {
        tileList.clear();
        tileList.addAll(newTileList);
    }
}
