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
import com.lcuraca.tecsup.notesapp.models.User;
import com.lcuraca.tecsup.notesapp.repositories.UserRepository;


public class MainActivity extends AppCompatActivity {

    private EditText user_et, password_et;
    private Button login_btn, register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_et = findViewById(R.id.user_et);
        password_et = findViewById(R.id.password_et);

        login_btn = findViewById(R.id.login_btn);
        register_btn = findViewById(R.id.register_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterForm();
            }
        });
    }

    public void login(){
        String user = user_et.getText().toString();
        String password = password_et.getText().toString();

        User newUser = UserRepository.login(user, password);

        if (newUser == null) {
            Toast.makeText(this, "Usuario y/o clave inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().putBoolean("isLogged", true).putString("username", newUser.getFullname()).apply();

        startActivity(new Intent(this, NotesActivity.class));
        finish();

    }

    public void showRegisterForm(){
        startActivity(new Intent(this, RegisterActivity.class));
    }

}
