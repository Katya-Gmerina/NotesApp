package com.example.notesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.Note;
import com.example.notesapp.databinding.ItemNoteBinding;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private OnNoteClickedListener clickedListener;
    private OnNoteLongClickedListener longClickedListener;

    private List<Note> noteList = new ArrayList<>();


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNoteBinding binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.binding.titleView.setText(note.getTitle());
        holder.binding.textView.setText(note.getText());

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickedListener != null) clickedListener.onNoteClicked(note);
            }
        });

        holder.binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickedListener != null) {
                    return longClickedListener.onNoteLongClicked(note);
                }
                return false;
            }
        });
    }

    public void setNoteClickedListener(OnNoteClickedListener clickedListener) {
        this.clickedListener = clickedListener;
    }

    public void setNoteLongClickedListener(OnNoteLongClickedListener longClickedListener) {
        this.longClickedListener = longClickedListener;
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void addNote(Note note) {
        noteList.add(note);
        notifyItemInserted(noteList.size() - 1);
    }

    protected static class NoteViewHolder extends RecyclerView.ViewHolder {

        private ItemNoteBinding binding;

        public NoteViewHolder(@NonNull ItemNoteBinding itemNoteBinding) {
            super(itemNoteBinding.getRoot());

            binding = itemNoteBinding;
        }
    }


}
