package com.example.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void toRegister(View view){
        Intent intent = new Intent(this, signupActivity.class);
        startActivity(intent);
    }

     public void toLogin(View view){
        Intent intent = new Intent(this, signinActivity.class);
        startActivity(intent);
    }
}