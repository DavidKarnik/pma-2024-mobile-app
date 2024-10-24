package com.example.hraj.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hraj.models.Tile;
import com.example.hraj.models.TileDao;

@Database(entities = {Tile.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TileDao tileDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "MY_ROOM_DATABASE").build();
                }
            }
        }
        return INSTANCE;
    }
}
