package com.example.myapplicationt.fragment;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.myapplicationt.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}