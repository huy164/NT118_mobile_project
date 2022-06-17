package com.example.workout_app.Views.start_up_screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workout_app.Activity.MainActivity;
import com.example.workout_app.Activity.ResetPassword;
import com.example.workout_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;
    private FirebaseAuth mAuth;
    private TextView textViewForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        textViewForgotPassword=findViewById(R.id.tv_forgot_password);
        textViewForgotPassword.setOnClickListener(this);

        editTextEmail = findViewById(R.id.signIn_email);
        editTextPassword = findViewById(R.id.signIn_password);

        signIn = findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

        mAuth=FirebaseAuth.getInstance();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(this, UserRegistration.class));
                break;
            case R.id.signIn:
                userLogin();
                break;
            case R.id.tv_forgot_password:
                startActivity(new Intent(this, ResetPassword.class));
                break;
        }
    }


    public void userLogin() {
        String email =editTextEmail.getText().toString().trim();
        String password =editTextPassword.getText().toString().trim();
        if (email.isEmpty()){
            editTextEmail.setError("email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this,"login  successfully",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                }else{
                    Toast.makeText(Login.this,"login  failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}