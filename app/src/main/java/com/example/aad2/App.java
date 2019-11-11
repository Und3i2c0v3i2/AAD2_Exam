package com.example.aad2;

import android.app.Application;
import android.content.Context;

import androidx.preference.PreferenceManager;

import com.example.aad2.model.db.DBHelper;
import com.example.aad2.model.db.DBRepository;
import com.example.aad2.model.db.DBRepositoryImpl;
import com.example.aad2.model.prefs.PrefsRepository;
import com.example.aad2.model.prefs.PrefsRepositoryImpl;
import com.example.aad2.utils.NotificationsUtil;

public class App extends Application {


    public static final String CHANNEL_1_ID = "channel_1";
    public static final String CHANNEL_NAME = "Notification Channel";
    public static final String CHANNEL_DESC = "Notification Channel Description";

    protected AppsNotificationManager notificationManager;
    private static DBHelper dbHelper;
    private static DBRepository dbRepository;
    private static PrefsRepository prefsRepository;

    /*
    this onCreate will be started before any activity starts
    and here we can register our channels
     */
    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = AppsNotificationManager.getInstance(this);
        notificationManager.registerNotificationChannel(CHANNEL_1_ID, CHANNEL_NAME, CHANNEL_DESC);

        dbHelper = DBHelper.getInstance(this);
        dbRepository = new DBRepositoryImpl(dbHelper);
        prefsRepository = new PrefsRepositoryImpl(PreferenceManager.getDefaultSharedPreferences(this));

//        dbHelper.insertSomeDummyData();
    }


    public static DBRepository getDbRepository() {
        return dbRepository;
    }

    public static PrefsRepository getSharedPrefsRepository() {
        return prefsRepository;
    }


}
