package com.example.to_doapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;

public class DeveloperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        MaterialCardView btnNextToUser = findViewById(R.id.btnNextToUser);
        MaterialCardView btnExit = findViewById(R.id.btnExit);

        btnNextToUser.setOnClickListener(v -> {
            Intent intent = new Intent(DeveloperActivity.this, UserInfoActivity.class);
            startActivity(intent);
        });

        btnExit.setOnClickListener(v -> finish());
    }
}