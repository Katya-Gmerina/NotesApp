package com.example.notesapp.fragment;

import static com.example.notesapp.MainActivity.NOTE_KEY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notesapp.Note;
import com.example.notesapp.NotesRepo;
import com.example.notesapp.R;
import com.example.notesapp.databinding.FragmentNoteInfoBinding;

public class NoteInfoFragment extends Fragment {

    private FragmentNoteInfoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNoteInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Note note = (Note) getArguments().getSerializable(NOTE_KEY);

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
        binding.toolbar.getMenu().findItem(R.id.menu_delete_forever)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem item) {
                        requireActivity().getSupportFragmentManager().popBackStack();
                        NotesRepo.noteList.remove(note);
                        return true;
                    }
                });


        binding.titleView.setText(note.getTitle());
        binding.textView.setText(note.getText());
    }

    @NonNull
    public static NoteInfoFragment newInstance(Note note) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(NOTE_KEY, note);

        NoteInfoFragment noteInfoFragment = new NoteInfoFragment();
        noteInfoFragment.setArguments(bundle);

        return noteInfoFragment;
    }
}
