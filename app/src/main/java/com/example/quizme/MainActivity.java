package com.example.quizme;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.quizme.TaskFragments.ListeningFragment;
import com.example.quizme.TaskFragments.ReadingFragment;
import com.example.quizme.TaskFragments.SpeakingFragment;
import com.example.quizme.TaskFragments.WritingFragment;
import com.google.android.material.navigation.NavigationView;




public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_listening:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ListeningFragment()).commit();
                break;
            case R.id.nav_reading:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ReadingFragment()).commit();
                break;
            case R.id.nav_speaking:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SpeakingFragment()).commit();
                break;
            case R.id.nav_writing:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WritingFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_register:
                startActivity(new Intent(this, SignupActivity.class));
                break;
            case R.id.nav_login:
                startActivity(new Intent(this, SigninActivity.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}