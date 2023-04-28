package com.example.notesapp.fragment;

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
import com.example.notesapp.databinding.FragmentNoteInfoBinding;

public class NoteAddFragment extends Fragment {
    private FragmentNoteInfoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNoteInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding.toolbar.setTitle(R.string.app_name);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        MenuItem completeItem = binding.toolbar.getMenu().findItem(R.id.menu_complete);
        completeItem.setVisible(true);
        completeItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {

                String title = binding.titleView.getText().toString();
                String text = binding.textView.getText().toString();

                if (!text.isEmpty()) {
                    Note note = new Note();
                    note.setTitle(title);
                    note.setText(text);

                    mainViewModel.insert(note);

                    requireActivity().getSupportFragmentManager().popBackStack();
                }
                return true;
            }
        });
    }
}
