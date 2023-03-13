package com.example.hydrateme.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.hydrateme.R;
import com.example.hydrateme.WelcomeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.format_prefs_screen, rootKey);

        Preference logoutPreference = findPreference("Logout");
        assert logoutPreference != null;
        logoutPreference.setOnPreferenceClickListener(preference -> {
            // Handle Logout button click
            // Perform the necessary actions to log the user out
            // For example: clear user data, navigate to login screen, etc.
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getContext().getApplicationContext(), WelcomeActivity.class);
            startActivity(intent);
            return true;
        });
    }
}