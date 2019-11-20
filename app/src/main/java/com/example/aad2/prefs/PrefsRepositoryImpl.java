package com.example.aad2.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import com.example.aad2.entity.Contact;


public class PrefsRepositoryImpl implements PrefsRepository {

    private SharedPreferences sharedPreferences;


    public PrefsRepositoryImpl(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
   }


    @Override
    public String enabled(String key) {
        return sharedPreferences.getString(key, "");
    }


}
