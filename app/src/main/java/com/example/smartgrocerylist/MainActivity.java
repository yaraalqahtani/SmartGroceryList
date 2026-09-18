package com.example.smartgrocerylist;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.smartgrocerylist.ui.home.HomeFragment;
import com.example.smartgrocerylist.ui.mylist.MyListFragment;

public class MainActivity extends AppCompatActivity {

    private final Fragment homeFragment = new HomeFragment();
    private final Fragment myListFragment = new MyListFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        if (savedInstanceState == null) {
            showFragment(homeFragment);
        }

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                showFragment(homeFragment);
                return true;
            } else if (id == R.id.nav_my_list) {
                showFragment(myListFragment);
                return true;
            }
            return false;
        });
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
