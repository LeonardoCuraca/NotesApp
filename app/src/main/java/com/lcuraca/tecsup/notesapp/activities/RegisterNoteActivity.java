package com.lcuraca.tecsup.notesapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lcuraca.tecsup.notesapp.R;
import com.lcuraca.tecsup.notesapp.repositories.NotesRepository;

public class RegisterNoteActivity extends AppCompatActivity {

    private EditText title, content;
    private Button noteRegButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_note);

        title = findViewById(R.id.title_reg_et);
        content = findViewById(R.id.content_reg_et);

        noteRegButton = findViewById(R.id.register_note_btn);

        noteRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNote();
            }
        });

    }

    public void registerNote() {
        String noteTitle = title.getText().toString();
        String noteContent = content.getText().toString();

        if (noteTitle.isEmpty() || noteContent.isEmpty()) {
            Toast.makeText(this, "Debes completar todos los cambios", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Long userId = sp.getLong("userId", 0);
        NotesRepository.create(userId, noteTitle, noteContent, "all");

        startActivity(new Intent(this, NotesActivity.class));
        finish();
    }

}
