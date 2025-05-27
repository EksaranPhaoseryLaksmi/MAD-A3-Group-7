package com.example.fashionstore;

import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import com.google.firebase.auth.FirebaseAuth;
import android.content.Intent;
import android.view.LayoutInflater;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.bumptech.glide.Glide;
import com.example.fashionstore.R;
import com.google.firebase.auth.FirebaseUser;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        //ListPreference languagePref = findPreference("languagePreference");
       // if (languagePref != null) {
        //    languagePref.setOnPreferenceChangeListener((preference, newValue) -> {
          //      LocaleHelper.persist(requireContext(), newValue.toString());

                // Recreate the activity to apply language immediately
            //    requireActivity().recreate();
              //  return true;
            //});
        //}
        // Get the sign-out preference button
        Preference signOutButton = findPreference("signOutButton");

        // Check if the preference exists
        if (signOutButton != null) {
            signOutButton.setOnPreferenceClickListener(preference -> {
                // Sign out from Firebase
                FirebaseAuth.getInstance().signOut();

                // Redirect to LoginActivity or wherever needed after sign-out
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();  // Optional: Close current activity

                return true;
            });
        }
        Preference versionPref = findPreference("app_version");
        if (versionPref != null) {
            try {
                PackageInfo pInfo = requireContext().getPackageManager().getPackageInfo(requireContext().getPackageName(), 0);
                versionPref.setSummary("Version " + pInfo.versionName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        // Add profile header
        View header = inflater.inflate(R.layout.pref_header_profile, container, false);

        ImageView profileImage = header.findViewById(R.id.profile_image);
        TextView userName = header.findViewById(R.id.user_name);
        TextView userEmail = header.findViewById(R.id.user_email);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userName.setText(user.getDisplayName() != null ? user.getDisplayName() : "User");
            userEmail.setText(user.getEmail());
            if (user.getPhotoUrl() != null) {
                Glide.with(this).load(user.getPhotoUrl()).into(profileImage);
            }
        }

        // Add to the root layout
        if (root instanceof ViewGroup) {
            ((ViewGroup) root).addView(header, 0);
        }

        return root;
    }

}
