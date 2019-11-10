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
import com.lcuraca.tecsup.notesapp.repositories.UserRepository;

public class RegisterActivity extends AppCompatActivity {

    private EditText user_reg_et, fullname_reg_et, email_reg_et, password_reg_et;
    private Button register_reg_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_reg_et = findViewById(R.id.user_reg_et);
        fullname_reg_et = findViewById(R.id.fullname_reg_et);
        email_reg_et = findViewById(R.id.email_reg_et);
        password_reg_et = findViewById(R.id.password_reg_et);

        register_reg_btn = findViewById(R.id.register_reg_btn);

        register_reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    public void register(){
        String user = user_reg_et.getText().toString();
        String fullname = fullname_reg_et.getText().toString();
        String email = email_reg_et.getText().toString();
        String password = password_reg_et.getText().toString();

        if(user.isEmpty() || fullname.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Debes completar todos los cambios", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRepository.create(user, fullname, email, password);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
