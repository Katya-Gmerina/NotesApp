package com.example.notesapp;

import java.util.ArrayList;
import java.util.List;

public class NotesRepo {
    public static List<Note> noteList = new ArrayList<>();

    static {
        noteList.add(new Note("Кушать", "Пора"));
        noteList.add(new Note("Ютубчик", "Посмотреть"));
        noteList.add(new Note("Кошку", "Погладить"));
    }

    public static void addNote(Note note) {
        noteList.add(note);
    }

    public static void updateNote(Note note, int index) {
        noteList.set(index, note);
    }
}
