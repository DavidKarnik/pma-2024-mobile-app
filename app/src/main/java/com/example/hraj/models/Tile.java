package com.example.hraj.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tiles")
public class Tile {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String title;
    private String numOfPlayers;
    private String shortDescription;
    private String description;

    // Konstruktor používaný Room
    public Tile(String title, String shortDescription, String description, String numOfPlayers) {
        this.title = title;
        this.numOfPlayers = numOfPlayers;
        this.shortDescription = shortDescription;
        this.description = description;
    }

    // Konstruktor ignorovaný Room
    @Ignore
    public Tile(int id, String title, String numOfPlayers, String shortDescription, String description) {
        this.id = id;
        this.title = title;
        this.numOfPlayers = numOfPlayers;
        this.shortDescription = shortDescription;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(String numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getId() {
        return id;
    }
}
