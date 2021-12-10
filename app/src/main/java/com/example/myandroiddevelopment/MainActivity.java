package com.example.myandroiddevelopment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();


        NavHostFragment navHostFragment = (NavHostFragment)(this.getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment));
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNav = findViewById(R.id.menubar);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }
}