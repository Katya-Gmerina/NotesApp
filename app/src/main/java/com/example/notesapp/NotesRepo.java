package com.example.notesapp;

import com.example.notesapp.database.Database;
import com.example.notesapp.database.NoteDao;

import java.util.List;

public class NotesRepo implements Repo<Note> {

    private final NoteDao noteDao = Database.getDatabase().noteDao();

    public List<Note> getAll() {
        return noteDao.getAllNotes();
    }

    public void insert(Note note) {
        noteDao.insert(note);
    }

    public void update(Note note) {
        noteDao.update(note);
    }

    public void delete(Note note) {
        noteDao.delete(note);
    }
}
