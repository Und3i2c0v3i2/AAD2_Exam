package com.example.aad2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;

public class AppsNotificationManager {


    private Context context;

    //TODO memory leak; static field and reference to a context
    private static AppsNotificationManager instance;

    private NotificationManagerCompat notificationManagerCompat;
    private NotificationManager notificationManager;

    /*
        private constructor
     */
    private AppsNotificationManager(Context context) {
        this.context = context;
//        notificationManagerCompat = NotificationManagerCompat.from(context);
//        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }


    /*
        factory
     */
    public static AppsNotificationManager getInstance(Context context) {
        if (instance == null) {
            instance = new AppsNotificationManager(context);
        }

        return instance;
    }


    /*
        register channel
     */
    void registerNotificationChannel(String channelId, String channelName, String channelDescription) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channelDescription);

            /* register channels */
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }


}
