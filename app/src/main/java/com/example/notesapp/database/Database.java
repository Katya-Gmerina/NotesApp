package com.example.notesapp.database;

import android.content.Context;

import androidx.room.Room;

public class Database {

    private static AppRoomDatabase appRoomDatabase;

    public static void initDatabase(Context context) {
        if (appRoomDatabase == null) {
            appRoomDatabase = Room.databaseBuilder(context, AppRoomDatabase.class, "notes.db")
                    .allowMainThreadQueries()
                    .build();
        }
    }

    public static AppRoomDatabase getDatabase() {
        return appRoomDatabase;
    }
}
