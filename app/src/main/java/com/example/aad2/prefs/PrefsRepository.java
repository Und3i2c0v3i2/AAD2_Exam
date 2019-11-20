package com.example.aad2.prefs;

public interface PrefsRepository {


    public static final String INFO_TYPE = "info_type";
    public static final String TOAST = "Toast Messages";
    public static final String NOTIF = "Notifications";


    String enabled(String key);

}
