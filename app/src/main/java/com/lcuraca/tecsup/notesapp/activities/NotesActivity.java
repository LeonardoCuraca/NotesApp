package com.lcuraca.tecsup.notesapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lcuraca.tecsup.notesapp.R;
import com.lcuraca.tecsup.notesapp.fragments.AllNotesFragment;

public class NotesActivity extends AppCompatActivity {

    private Button FirstButton, SecondButton, ThirdButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        FirstButton = findViewById(R.id.fragment1_button);
        SecondButton = findViewById(R.id.fragment2_button);
        ThirdButton = findViewById(R.id.fragment3_button);

        FirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFirstFragment();
            }
        });

        SecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSecondFragment();
            }
        });

        ThirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showThirdFragment();
            }
        });

    }

    private void showFirstFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new AllNotesFragment();
        fm.beginTransaction().replace(R.id.content, fragment).commit();
    }

    private void showSecondFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new AllNotesFragment();
        fm.beginTransaction().replace(R.id.content, fragment).commit();
    }

    private void showThirdFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new AllNotesFragment();
        fm.beginTransaction().replace(R.id.content, fragment).commit();
    }

}
