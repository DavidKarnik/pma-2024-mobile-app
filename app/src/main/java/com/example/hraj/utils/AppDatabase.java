package com.example.hraj.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hraj.models.Theme;
import com.example.hraj.models.ThemeDao;
import com.example.hraj.models.Tile;
import com.example.hraj.models.TileDao;

// !!! Při každé změně struktury databáze povýšit verzy databáze !!!
@Database(entities = {Tile.class, Theme.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TileDao tileDao();

    public abstract ThemeDao themeDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "MY_ROOM_DATABASE")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
