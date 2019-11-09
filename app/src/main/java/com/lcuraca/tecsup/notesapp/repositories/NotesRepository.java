package com.lcuraca.tecsup.notesapp.repositories;

import com.lcuraca.tecsup.notesapp.models.Notes;
import com.orm.SugarRecord;

import java.util.List;

public class NotesRepository {
    public static List<Notes> list(){
        List<Notes> notes = SugarRecord.listAll(Notes.class);
        return notes;
    }

    public static List<Notes> findByState(String type){
        List<Notes> notes = SugarRecord.find(Notes.class, "state =? ", type);
        return notes;
    }

    public static void create(Long userId, String title, String content, String state){
        Notes note = new Notes(userId, title, content, state);
        SugarRecord.save(note);
    }

    public static void updateState(String state, long id){
        Notes note = SugarRecord.findById(Notes.class, id);
        note.getState();
        SugarRecord.save(note);
    }

}
