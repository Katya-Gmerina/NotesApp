package com.example.notesapp;

import java.util.List;

public interface Repo<T> {

    List<T> getAll();

    void insert(T t);

    void update(T t);

    void delete(T t);
}
