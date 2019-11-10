package com.lcuraca.tecsup.notesapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcuraca.tecsup.notesapp.R;
import com.lcuraca.tecsup.notesapp.adapters.NotesAdapter;
import com.lcuraca.tecsup.notesapp.models.Notes;
import com.lcuraca.tecsup.notesapp.repositories.NotesRepository;

import java.util.List;

public class AllNotesFragment extends Fragment {

    private RecyclerView notes_list;
    private String state;

    public AllNotesFragment(String state) {
        this.state = state;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_notes, container, false);
        notes_list = view.findViewById(R.id.notes_list);
        notes_list.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Notes> notes = NotesRepository.findByState(state);
        notes_list.setAdapter(new NotesAdapter(getContext(), notes));
        return view;
    }
}
