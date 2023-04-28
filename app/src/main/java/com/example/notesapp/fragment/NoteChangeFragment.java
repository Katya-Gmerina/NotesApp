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
import androidx.lifecycle.ViewModelProvider;

import com.example.notesapp.MainViewModel;
import com.example.notesapp.Note;
import com.example.notesapp.R;
import com.example.notesapp.databinding.FragmentNoteAddBinding;


public class NoteChangeFragment extends Fragment {

    private FragmentNoteAddBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNoteAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        Note note = (Note) getArguments().getSerializable(NOTE_KEY);

        binding.toolbar.setTitle(R.string.app_name);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
        binding.toolbar.getMenu().findItem(R.id.menu_complete)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem item) {

                        String title = binding.titleView.getText().toString();
                        String text = binding.textView.getText().toString();

                        if (!text.isEmpty()) {
                            mainViewModel.update(note);
                            requireActivity().getSupportFragmentManager().popBackStack();
                        }

                        return true;
                    }
                });
    }

    @NonNull
    public static NoteChangeFragment newInstance(Note note) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(NOTE_KEY, note);

        NoteChangeFragment noteInfoFragment = new NoteChangeFragment();
        noteInfoFragment.setArguments(bundle);

        return noteInfoFragment;
    }
}
