package com.example.hraj;

public class Tile {
    private String title;
    private Integer numOfPlayers;
    private String description;

    public Tile(String title, String description,Integer numOfPlayers) {
        this.title = title;
        this.numOfPlayers = numOfPlayers;
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
}
