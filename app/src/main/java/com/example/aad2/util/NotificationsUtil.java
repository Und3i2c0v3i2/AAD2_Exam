package com.example.aad2.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.aad2.R;

import static com.example.aad2.App.CHANNEL_ID;

public class NotificationsUtil {


    public static void getNotification(Context context, Class targetNotificationActivity, String title, String text) {

        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, targetNotificationActivity);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, intent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notif)
                .setContentTitle(title)
                .setContentText(text)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        Notification n = builder.build();
        assert manager != null;
        manager.notify(1, n);
    }
}
