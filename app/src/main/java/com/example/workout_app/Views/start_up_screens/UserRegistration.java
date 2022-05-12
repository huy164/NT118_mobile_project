package com.example.workout_app.Views.start_up_screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workout_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class UserRegistration extends AppCompatActivity implements View.OnClickListener {
    private TextView banner,userRegistration;
    private EditText editTextFullName,editTextEmail,editTextPassword,editTextPhoneNumber;
    public Map<String, Object> user = new HashMap<>();
    public  FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        mAuth = FirebaseAuth.getInstance();
        userRegistration=findViewById(R.id.userRegister);
        userRegistration.setOnClickListener(this);

        editTextFullName=findViewById(R.id.fullname);
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        editTextPhoneNumber=findViewById(R.id.phoneNumber);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userRegister:
                userRegistration();
                break;
        }
    }
    public boolean isValid(){
        return true;
    }
    public void userRegistration() {
        //get value from input
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String phoneNumber= editTextPhoneNumber.getText().toString().trim();
        String fullName=editTextFullName.getText().toString().trim();

        // validate input
        if(email.isEmpty()){
            editTextEmail.setError("email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            return;
        }
        if(fullName.isEmpty()){
            editTextFullName.setError("fullname is required");
            editTextFullName.requestFocus();
            return;
        }
//         Regex
//        if(!Pattern.EMAIL_ADDRESS.matcher(email).maches()){
//            editTextEmail.setError("invalid email");
//            editTextEmail.requestFocus();
//            return;
//        }
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            AddUserToStore();
                            Toast.makeText(UserRegistration.this,"register successfully",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(UserRegistration.this,"register failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    //make sure you uninstall the app before run it again after add new dependencies
    private void AddUserToStore() {
        user.put("userId", mAuth.getCurrentUser().getUid());
        user.put("fullName", editTextFullName.getText().toString());
        user.put("email", editTextEmail.getText().toString());
        user.put("phoneNumber", editTextPhoneNumber.getText().toString());
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}