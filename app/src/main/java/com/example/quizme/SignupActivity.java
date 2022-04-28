package com.example.quizme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailTextView, passwordTextView;
    private Button Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        emailTextView = findViewById(R.id.emailBox);
        passwordTextView = findViewById(R.id.passwordBox);
        Btn = findViewById(R.id.submitBtn);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
    }

    private void registerNewUser()
    {
        // Take the value of two edit texts in Strings
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // Validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // create new user or register new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Registration successful!",
                                    Toast.LENGTH_LONG)
                                    .show();

                            //mAuth.signInWithEmailAndPassword(email, password);

                            mAuth.getCurrentUser().sendEmailVerification()
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(getApplicationContext() , "Email verification has been sent",Toast.LENGTH_SHORT).show();
                                        }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.i("TAG", "Fail to sent verification link");
                                }
                            });

                            mAuth.signInWithEmailAndPassword(email,password);
                            // if the user created intent to login activity
                            Intent intent
                                    = new Intent(SignupActivity.this,
                                    MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            // Registration failed
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Registration failed!!"
                                            + " Please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
    }
}