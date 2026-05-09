package com.example.to_doapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class UserInfoActivity extends AppCompatActivity {

    private TextView tvUsername, tvEmail;
    private MaterialCardView btnEditInfo, btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        tvUsername = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);
        btnEditInfo = findViewById(R.id.btnEditInfo);
        btnSignOut = findViewById(R.id.btnSignOut);

        btnEditInfo.setOnClickListener(v -> showEditDialog());

        btnSignOut.setOnClickListener(v -> showSignOutDialog());
    }

    private void showEditDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_user_info, null);
        EditText etEditUsername = dialogView.findViewById(R.id.etEditUsername);
        EditText etEditEmail = dialogView.findViewById(R.id.etEditEmail);
        MaterialCardView btnEditOk = dialogView.findViewById(R.id.btnEditOk);
        MaterialCardView btnEditCancel = dialogView.findViewById(R.id.btnEditCancel);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        // Set the text to match the design in the image exactly
        etEditUsername.setText("Username");
        etEditEmail.setText("Email");

        btnEditOk.setOnClickListener(v -> {
            String newUsername = etEditUsername.getText().toString().trim();
            String newEmail = etEditEmail.getText().toString().trim();

            if (!newUsername.isEmpty() && !newEmail.isEmpty()) {
                tvUsername.setText("Username: " + newUsername);
                tvEmail.setText("Email: " + newEmail);
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        btnEditCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void showSignOutDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_sign_out, null);
        MaterialCardView btnOk = dialogView.findViewById(R.id.btnSignOutOk);
        MaterialCardView btnCancel = dialogView.findViewById(R.id.btnSignOutCancel);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        btnOk.setOnClickListener(v -> {
            dialog.dismiss();
            // Redirect to splash screen (MainActivity) and clear back stack
            Intent intent = new Intent(UserInfoActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
