package com.example.to_doapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    EditText etFullName, etEmail, etUsername, etPassword;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(v -> {

            String fullName = etFullName.getText().toString();
            String email = etEmail.getText().toString();
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if(fullName.isEmpty() || email.isEmpty()
                    || username.isEmpty() || password.isEmpty()) {

                Toast.makeText(this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this,
                        "Signup Successful",
                        Toast.LENGTH_SHORT).show();
                
                Intent intent = new Intent(SignupActivity.this, TodoActivity.class);
                startActivity(intent);
                finish();

            }

        });
    }
}