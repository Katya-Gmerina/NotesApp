package com.example.notesapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.notesapp.Note;

@Database(entities = Note.class, version = 2)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
