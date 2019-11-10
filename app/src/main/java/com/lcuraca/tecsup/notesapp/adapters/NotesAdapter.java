package com.lcuraca.tecsup.notesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lcuraca.tecsup.notesapp.R;

import androidx.recyclerview.widget.RecyclerView;

import com.lcuraca.tecsup.notesapp.models.Notes;
import com.lcuraca.tecsup.notesapp.repositories.NotesRepository;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Notes> notes;
    private Context context;

    public NotesAdapter(Context context, List<Notes> notes){
        this.context = context;
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
    public void onBindViewHolder(final NotesAdapter.ViewHolder viewHolder, final int position) {
        final Notes note = this.notes.get(position);
        viewHolder.title_tv.setText(note.getTitle());
        viewHolder.content_tv.setText(note.getContent());

        viewHolder.favorite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean updated = NotesRepository.updateState(context,"favorites", note.getId());
                if (updated) {
                    notes.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, getItemCount());
                }
            }
        });

        viewHolder.archive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean updated = NotesRepository.updateState(context, "archived", note.getId());
                if (updated) {
                    notes.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, getItemCount());
                }
            }
        });

    }

    @Override
    public int getItemCount(){
        return this.notes.size();
    }

}
