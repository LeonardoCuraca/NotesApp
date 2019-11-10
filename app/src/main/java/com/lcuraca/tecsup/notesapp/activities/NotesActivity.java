package com.lcuraca.tecsup.notesapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lcuraca.tecsup.notesapp.R;
import com.lcuraca.tecsup.notesapp.fragments.AllNotesFragment;
import com.lcuraca.tecsup.notesapp.models.User;
import com.lcuraca.tecsup.notesapp.repositories.UserRepository;

public class NotesActivity extends AppCompatActivity {

    private FloatingActionButton addNote;
    private TextView username;
    private Long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottonNavView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.fragment1:
                        showFirstFragment();
                        break;
                    case R.id.fragment2:
                        showSecondFragment();
                        break;
                    case R.id.fragment3:
                        item.setChecked(true);
                        showThirdFragment();
                        break;
                }
                return true;
            }
        });

        showFirstFragment();

        username = findViewById(R.id.username);

        addNote = findViewById(R.id.addNote);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNoteRegister();
            }
        });

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        userId = sp.getLong("userId", 0);
        User user = UserRepository.findUserById(userId);

        if (user != null) {
            username.setText("Bienvenido(a) " + user.getFullname());
        }

    }

    private void showFirstFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new AllNotesFragment("all");
        fm.beginTransaction().replace(R.id.content, fragment).commit();
    }

    private void showSecondFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new AllNotesFragment("favorites");
        fm.beginTransaction().replace(R.id.content, fragment).commit();
    }

    private void showThirdFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new AllNotesFragment("archived");
        fm.beginTransaction().replace(R.id.content, fragment).commit();
    }

    private void callNoteRegister() {
        startActivity(new Intent(this, RegisterNoteActivity.class));
        finish();
    }

}
