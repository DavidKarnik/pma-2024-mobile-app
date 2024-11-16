package com.example.hraj.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tiles")
public class Tile {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String title;
    private Integer numOfPlayers;
    private String shortDescription;
    private String description;

    public Tile(String title, String shortDescription, String description, Integer numOfPlayers) {
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

    public Integer getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(Integer numOfPlayers) {
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
