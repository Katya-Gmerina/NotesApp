package com.example.notesapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.PropertyKey;

import java.io.Serializable;

@Entity
public class Note implements Serializable {

    @PrimaryKey
    private long id;

    private String title;
    private String text;

    public Note(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
