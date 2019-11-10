package com.lcuraca.tecsup.notesapp.repositories;

import android.content.Context;
import android.widget.Toast;

import com.lcuraca.tecsup.notesapp.models.Notes;
import com.orm.SugarRecord;

import java.util.List;

public class NotesRepository {
    public static List<Notes> list(){
        List<Notes> notes = SugarRecord.listAll(Notes.class);
        return notes;
    }

    public static List<Notes> findByState(Long id, String type){
        List<Notes> notes = SugarRecord.find(Notes.class, "id =? and state=? ", String.valueOf(id), type);
        return notes;
    }

    public static void create(Long userId, String title, String content, String state){
        Notes note = new Notes(userId, title, content, state);
        SugarRecord.save(note);
    }

    public static boolean updateState(Context context, String state, long id){
        Notes note = SugarRecord.findById(Notes.class, id);
        if (state.equals(note.getState())){
            note.setState("all");
        } else if (state.equals("archived") && note.getState().equals("favorites")){
            Toast.makeText(context, "No puedes archivar notas favoritas", Toast.LENGTH_SHORT);
            return false;
        } else if (state.equals("favorites") && note.getState().equals("archived")){
            Toast.makeText(context, "Primero desarchiva la nota", Toast.LENGTH_SHORT);
            return false;
        } else {
            if (state.equals("archived")){
                Toast.makeText(context, "Nota añadida a favoritos", Toast.LENGTH_SHORT);
            }
            if (state.equals("favorites")){
                Toast.makeText(context, "Nota archivada", Toast.LENGTH_SHORT);
            }
            note.setState(state);
        }
        SugarRecord.save(note);
        return true;
    }

}
