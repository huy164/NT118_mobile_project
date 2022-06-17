package com.example.workout_app.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.workout_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {

    private Button btnResetPassword;
    private EditText editTextEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        mAuth=FirebaseAuth.getInstance();
        editTextEmail=findViewById(R.id.ResetEmail);
        btnResetPassword=findViewById(R.id.Reset);
        btnResetPassword.setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Reset:
                resetPassword();
                break;
        }
    }
    public void resetPassword(){
        if (editTextEmail.getText().toString().isEmpty()) {
            editTextEmail.setError("Email is required");
    }
            else {
        mAuth.sendPasswordResetEmail(editTextEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Email Send", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }
}