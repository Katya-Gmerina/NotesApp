package com.example.notesapp;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final NotesRepo notesRepo = new NotesRepo();

    public List<Note> getAllNotes() {
        return notesRepo.getAll();
    }

    public void insert(Note note) {
        notesRepo.insert(note);
    }

    public void update(Note note) {
        notesRepo.update(note);
    }

    public void delete(Note note) {
        notesRepo.delete(note);
    }
}
