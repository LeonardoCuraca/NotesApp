package com.lcuraca.tecsup.notesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcuraca.tecsup.notesapp.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lcuraca.tecsup.notesapp.models.Notes;
import com.lcuraca.tecsup.notesapp.repositories.NotesRepository;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Notes> notes;

    public NotesAdapter(List<Notes> notes){
        this.notes = notes;
    }

    public void setNotes(List<Notes> notes){
        this.notes = notes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title_tv, content_tv;
        public ImageButton favorite_btn, archive_btn;

        public ViewHolder(View itemView){
            super(itemView);
            title_tv = itemView.findViewById(R.id.title_tv);
            content_tv = itemView.findViewById(R.id.content_tv);
            favorite_btn = itemView.findViewById(R.id.favorite_btn);
            archive_btn = itemView.findViewById(R.id.archive_btn);
        }
    }

    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder viewHolder, final int position) {
        final Notes note = this.notes.get(position);
        viewHolder.title_tv.setText(note.getTitle());
        viewHolder.content_tv.setText(note.getContent());

        viewHolder.favorite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesRepository.updateState("favorite", note.getId());
            }
        });

        viewHolder.archive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesRepository.updateState("archive", note.getId());
            }
        });

    }

    @Override
    public int getItemCount(){
        return this.notes.size();
    }

}
