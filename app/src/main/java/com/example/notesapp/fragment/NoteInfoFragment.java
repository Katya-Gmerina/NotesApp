package com.example.notesapp.fragment;

import static com.example.notesapp.MainActivity.NOTE_KEY;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.notesapp.MainViewModel;
import com.example.notesapp.Note;
import com.example.notesapp.R;
import com.example.notesapp.databinding.FragmentNoteInfoBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        Note note = (Note) getArguments().getSerializable(NOTE_KEY);

        binding.toolbar.setTitle(R.string.app_name);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        MenuItem completeItem = binding.toolbar.getMenu().findItem(R.id.menu_complete);
        completeItem.setVisible(false);
        completeItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {

                String title = binding.titleView.getText().toString();
                String text = binding.textView.getText().toString();

                if (!title.isEmpty() && !text.isEmpty()) {
                    note.setTitle(title);
                    note.setText(text);
                    note.setDate(new Date().getTime());

                    mainViewModel.update(note);

                    binding.titleView.clearFocus();
                    binding.textView.clearFocus();

                    InputMethodManager imm =
                            (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(binding.titleView.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(binding.textView.getWindowToken(), 0);
                }
                return true;
            }
        });

        binding.toolbar.getMenu().findItem(R.id.menu_delete).setVisible(true);
        MenuItem deleteItem = binding.toolbar.getMenu().findItem(R.id.menu_delete_forever);
        deleteItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                mainViewModel.delete(note);
                requireActivity().getSupportFragmentManager().popBackStack();
                return true;
            }
        });

        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                completeItem.setVisible(hasFocus);
            }
        };

        binding.titleView.setText(note.getTitle());
        binding.titleView.setOnFocusChangeListener(onFocusChangeListener);

        binding.textView.setText(note.getText());
        binding.textView.setOnFocusChangeListener(onFocusChangeListener);
        binding.dateView.setText(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date(note.getDate())));
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
