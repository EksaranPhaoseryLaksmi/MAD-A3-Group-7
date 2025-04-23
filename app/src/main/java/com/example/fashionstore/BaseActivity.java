package com.example.fashionstore;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(newBase);
        String language = prefs.getString("languagePreference", "en"); // default to English
        super.attachBaseContext(LocaleHelper.setLocale(newBase, language));
    }

}
