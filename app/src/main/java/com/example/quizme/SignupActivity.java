package com.example.quizme;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText nameSignUp, emailTextViewSignUp, passwordTextViewSignUp;
    private Button submitBtnSignUp, login;
    private ImageView banner;

    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        database = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        emailTextViewSignUp = findViewById(R.id.emailBox);
        passwordTextViewSignUp = findViewById(R.id.passwordBox);
        nameSignUp = findViewById(R.id.nameSignUp);
        submitBtnSignUp = findViewById(R.id.submitBtnSignUp);
        submitBtnSignUp.setOnClickListener(this);
        login = findViewById(R.id.loginBtnSignUp);
        login.setOnClickListener(this);
//        banner = findViewById(R.id.banner);
//        banner.setOnClickListener(this);

    }

    private void registerNewUser()
    {
        // Take the value of two edit texts in Strings
        String name, email, password;

        name = nameSignUp.getText().toString();
        email = emailTextViewSignUp.getText().toString();
        password = passwordTextViewSignUp.getText().toString();

        User user = new User(name, email,password);


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

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTextViewSignUp.setError("Please provide valid email!");
            emailTextViewSignUp.requestFocus();
            return;
        }

        if(password.length() < 6) {
            passwordTextViewSignUp.setError("Minimum password length should be 6 characters!");
            passwordTextViewSignUp.requestFocus();
            return;
        }


        // create new user or register new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            String uid= task.getResult().getUser().getUid();
                             database
                                     .collection("users")
                                     .document(uid)
                                     .set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     if(task.isSuccessful()){
                                         mAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                             @Override
                                             public void  onSuccess(Void unused) {
                                                 Toast.makeText(getApplicationContext() , "Email verification has been sent",Toast.LENGTH_SHORT).show();
                                             }
                                         }).addOnFailureListener(new OnFailureListener() {
                                             @Override
                                             public void onFailure(@NonNull Exception e) {
                                                 Log.i("TAG", "Fail to sent verification link");
                                             }
                                         });
//                            mAuth.signInWithEmailAndPassword(email,password);
                                         // if the user created intent to login activity
                                         Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                                         startActivity(intent);
//                                        progressBar.setVisibility(View.VISIBLE);
                                         // redirect to login layout!
                                     }
                                     else{
                                         Toast.makeText(
                                                 getApplicationContext(),
                                                 "Registration failed!!"
                                                         + " Please try again later",
                                                 Toast.LENGTH_LONG)
                                                 .show();
                                     }
                                 }
                             });

                            //mAuth.signInWithEmailAndPassword(email, password);

                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();
//                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginBtnSignUp:
                startActivity(new Intent(this, SigninActivity.class));
                break;
//            case R.id.banner:
//                startActivity(new Intent(this, MainActivity.class));
//                break;
            case R.id.submitBtnSignUp:
                registerNewUser();
                break;
        }


    }
}