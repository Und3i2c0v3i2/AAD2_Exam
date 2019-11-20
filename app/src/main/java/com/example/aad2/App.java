package com.example.aad2;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


import com.example.aad2.db.DBHelper;
import com.example.aad2.db.DBRepository;
import com.example.aad2.db.DBRepositoryImpl;
import com.example.aad2.prefs.PrefsRepository;
import com.example.aad2.prefs.PrefsRepositoryImpl;

public class App extends Application {


    public static final String CHANNEL_ID = "channel";
    public static final String CHANNEL_NAME = "Notification Channel";
    public static final String CHANNEL_DESC = "Notification Channel Description";

    // for intents
    public static final String OBJECT_ID = "id";

    private static DBRepository dbRepository;
    private static PrefsRepository prefsRepository;


    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper dbHelper = DBHelper.getInstance(this);
        dbRepository = new DBRepositoryImpl(dbHelper);
        registerNotificationChannel(CHANNEL_ID, CHANNEL_NAME, CHANNEL_DESC);
        prefsRepository = new PrefsRepositoryImpl(this);

//        dbHelper.insertSomeDummyData();
    }


    public static DBRepository getDbRepository() {
        return dbRepository;
    }

    public static PrefsRepository getPrefsRepository() {
        return prefsRepository;
    }

    public void registerNotificationChannel(String channelId, String channelName, String channelDescription) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channelDescription);

            NotificationManager manager = this.getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

}
