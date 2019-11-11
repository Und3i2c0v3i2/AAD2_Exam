package com.example.aad2.model.prefs;

import androidx.preference.Preference;

public interface PrefsRepository {

    public static final String PREFS_FILE = "com.example.aad2_preferences";

    public static final String RECEIVE_INFO_KEY = "receive_info";
    public static final String INFO_TYPE_KEY = "info_type";
    public static final String CHOICE_NOTIF = "Notifications";
    public static final String CHOICE_TOAST = "Toast Messages";


    void savePrefs(Preference pref, String key);

    boolean isEnabledToast();

    boolean isEnabledNotif();
}
