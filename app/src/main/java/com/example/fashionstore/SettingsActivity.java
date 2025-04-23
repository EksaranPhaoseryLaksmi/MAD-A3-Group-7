package com.example.fashionstore;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Preference signOutButton = findPreference("signOutButton");
        signOutButton.setOnPreferenceClickListener(preference -> {
            // Sign out the user from Firebase
            FirebaseAuth.getInstance().signOut();

            // Redirect to LoginActivity or wherever you want after sign-out
            Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Optional: Close the current activity

            return true;
        });
    }
}

