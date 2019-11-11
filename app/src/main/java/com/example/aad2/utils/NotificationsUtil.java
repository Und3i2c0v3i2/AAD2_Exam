package com.example.aad2.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;

import com.example.aad2.R;



public class NotificationsUtil {


    private NotificationsUtil() {}


    public static Notification getSimpleNotification(Context context, Class targetNotificationActivity, String channelId, String title, String text, int smallIcon, int largeIcon, int priority, boolean autoCancel, int notifId, int pendingFlag) {

        Bitmap ic_large = BitmapFactory.decodeResource(context.getResources(), largeIcon);

        Intent intent = new Intent(context, targetNotificationActivity);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, intent, pendingFlag);


        return new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_notifi_1)
                .setContentTitle(title)
                .setContentText(text)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pendingIntent)
                .setAutoCancel(autoCancel)
                .setLargeIcon(ic_large)
                .build();
    }
}
