package com.example.fashionstore;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import java.util.Locale;

public class LocaleHelper {

    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    // Method to persist the selected language
    public static void persist(Context context, String language) {
        SharedPreferences prefs = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        prefs.edit().putString(SELECTED_LANGUAGE, language).apply();
    }

    // Updated setLocale method that accepts both Context and language
    public static Context setLocale(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = res.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale);
            return context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
            return context;
        }
    }

    // Get the saved language from SharedPreferences
    public static String getLanguage(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        return prefs.getString(SELECTED_LANGUAGE, "en"); // Default is "en"
    }
}
