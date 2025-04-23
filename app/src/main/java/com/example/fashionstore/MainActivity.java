package com.example.fashionstore;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View rootView = findViewById(android.R.id.content); // Root view of the activity
        rootView.setBackgroundResource(R.drawable.fashionstore2); // Replace with your background image resource

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Load default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int id = item.getItemId();
            if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_add_expense) {
                selectedFragment = new ProductFragment();
            } else if (id == R.id.nav_expense_list) {
                selectedFragment = new OrderFragment();
            } else if (id == R.id.nav_settings) {
                selectedFragment = new SettingsFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

            return true;
        });
    }
}
