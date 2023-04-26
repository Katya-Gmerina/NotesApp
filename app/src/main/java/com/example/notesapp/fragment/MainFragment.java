package com.example.notesapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.Note;
import com.example.notesapp.NotesRepo;
import com.example.notesapp.R;
import com.example.notesapp.adapter.NotesAdapter;
import com.example.notesapp.adapter.OnNoteClickedListener;
import com.example.notesapp.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.toolbar.setTitle(R.string.app_name);

        RecyclerView notesRecyclerView = binding.notesRecyclerView;
        NotesAdapter adapter = new NotesAdapter();
        notesRecyclerView.setAdapter(adapter);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        for (Note note : NotesRepo.noteList) {
            adapter.addNote(note);
        }

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_frag, new NoteAddFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        adapter.setNoteClickedListener(new OnNoteClickedListener() {
            @Override
            public void onNoteClicked(Note note) {
                NoteInfoFragment noteInfoFragment = NoteInfoFragment.newInstance(note);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_frag, noteInfoFragment).addToBackStack(null)
                        .commit();
            }
        });
    }
}
