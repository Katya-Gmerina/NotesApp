package com.example.notesapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notesapp.databinding.FragmentNoteAddBinding;

public class NoteAddFragment extends Fragment {
    private FragmentNoteAddBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNoteAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
