package com.example.notesapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notesapp.Note;
import com.example.notesapp.databinding.FragmentNoteInfoBinding;

public class NoteInfoFragment extends Fragment {

    public static final String TITLE_KEY = "TITLE_KEY";
    public static final String TEXT_KEY = "TEXT_KEY";
    private FragmentNoteInfoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNoteInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        String title = getArguments().getString(TITLE_KEY);
        String text = getArguments().getString(TEXT_KEY);

        binding.titleView.setText(title);
        binding.textView.setText(text);
    }

    public static NoteInfoFragment newInstance(Note note) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE_KEY, note.getTitle());
        bundle.putString(TEXT_KEY, note.getText());

        NoteInfoFragment noteInfoFragment = new NoteInfoFragment();
        noteInfoFragment.setArguments(bundle);

        return noteInfoFragment;
    }
}
