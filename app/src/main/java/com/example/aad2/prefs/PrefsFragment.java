package com.example.aad2.prefs;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.aad2.App;
import com.example.aad2.R;

import static com.example.aad2.prefs.PrefsRepository.INFO_TYPE;


public class PrefsFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private SharedPreferences.OnSharedPreferenceChangeListener listener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    if (key.equals(INFO_TYPE)) {
                        String info_type = sharedPreferences.getString(key, "");
                        Preference pref = findPreference(INFO_TYPE);
                        if (pref != null)
                            pref.setSummary(info_type);
                    }
                }
            };


    /* REGISTER & UNREGISTER LISTENER */
    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);

        Preference pref = findPreference(INFO_TYPE);
        if (pref != null)
            pref.setSummary(getPreferenceScreen().getSharedPreferences().getString(INFO_TYPE, ""));
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(listener);
    }
}
