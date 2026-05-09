package com.example.to_doapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class TodoActivity extends AppCompatActivity {

    private LinearLayout containerTasks;
    private MaterialCardView fabAdd, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        containerTasks = findViewById(R.id.containerTasks);
        fabAdd = findViewById(R.id.fabAdd);
        btnNext = findViewById(R.id.btnNext);

        // Add some initial sample tasks
        addTaskView("To-Do item");
        addTaskView("To-Do item");
        addTaskView("To-Do item");

        // Add task button
        fabAdd.setOnClickListener(v -> showTaskDialog(null, null));

        // Next button to Developer Screen
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(TodoActivity.this, DeveloperActivity.class);
            startActivity(intent);
        });
    }

    private void addTaskView(String taskName) {
        View taskView = LayoutInflater.from(this).inflate(R.layout.item_todo, containerTasks, false);
        TextView tvTask = taskView.findViewById(R.id.tvTodoItem);
        ImageView ivEdit = taskView.findViewById(R.id.ivEdit);
        ImageView ivDelete = taskView.findViewById(R.id.ivDelete);

        tvTask.setText(taskName);

        ivEdit.setOnClickListener(v -> showTaskDialog(tvTask, (View) taskView));
        ivDelete.setOnClickListener(v -> containerTasks.removeView(taskView));

        containerTasks.addView(taskView);
    }

    private void showTaskDialog(TextView tvToUpdate, View viewToRemoveOnUpdate) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_task, null);
        EditText etTaskName = dialogView.findViewById(R.id.etTaskName);
        MaterialCardView btnOk = dialogView.findViewById(R.id.btnOk);
        MaterialCardView btnCancel = dialogView.findViewById(R.id.btnCancel);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        // Make the background transparent to show custom rounded corners and elevation
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        if (tvToUpdate != null) {
            etTaskName.setText(tvToUpdate.getText().toString());
            // Move cursor to the end
            etTaskName.setSelection(etTaskName.getText().length());
        }

        btnOk.setOnClickListener(v -> {
            String name = etTaskName.getText().toString().trim();
            if (!name.isEmpty()) {
                if (tvToUpdate != null) {
                    tvToUpdate.setText(name);
                } else {
                    addTaskView(name);
                }
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Task name cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
