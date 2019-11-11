package com.example.aad2.model.prefs;

import android.content.SharedPreferences;

import androidx.preference.Preference;
import androidx.preference.PreferenceManager;


public class PrefsRepositoryImpl implements PrefsRepository {

    private SharedPreferences sharedPreferences;


    public PrefsRepositoryImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    private boolean isEnabled() {
        return sharedPreferences.getBoolean(RECEIVE_INFO_KEY, false);
    }

    private boolean isPicked() {
        return isEnabled() && !sharedPreferences.getString(INFO_TYPE_KEY, "").equals("");
    }

    @Override
    public boolean isEnabledToast() {
        return isPicked() && sharedPreferences.getString(INFO_TYPE_KEY, "").equals(CHOICE_TOAST);
    }

    @Override
    public boolean isEnabledNotif() {
        return isPicked() && sharedPreferences.getString(INFO_TYPE_KEY, "").equals(CHOICE_NOTIF);
    }

    @Override
    public void savePrefs(Preference pref, String key) {

//        String info_type = sharedPreferences.getString(key, "");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, pref.getSummary().toString());
        editor.apply();
//        PreferenceManager.
//        pref.findPreference(key);
//        if (pref != null)
//            pref.setSummary(info_type);
    }

}
