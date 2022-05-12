package com.example.workout_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigatin_view);
        NavController navController = Navigation.findNavController(this,  R.id.nav_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
//        AppBarConfiguration appBarConfiguration=AppBarConfiguration(setOf(R.id.homeFragment,R.id.profileFragent,R.id.settingFragment));
    }
}