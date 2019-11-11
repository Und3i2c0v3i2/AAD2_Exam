package com.example.aad2.view.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;


import com.example.aad2.App;
import com.example.aad2.R;
import com.example.aad2.model.entity.Contact;
import com.example.aad2.utils.AlertDialogUtil;
import com.example.aad2.utils.NotificationsUtil;
import com.example.aad2.utils.ToastUtil;
import com.example.aad2.view.BaseView;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseActivity
        extends AppCompatActivity
        implements BaseView {


    protected static final String FIRST_NAME = "first_name";
    protected static final String LAST_NAME = "last_name";
    protected static final String ADDRESS = "address";
    protected static final String IMG_URL = "img_url";


    /*
    save alert dialog as a member variable so we can dismiss it before activity is destroyed,
    or it can cause leak
     */
    protected AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void navigateToSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }


    @Override
    public void showAboutDialog(Context context) {
        alertDialog = AlertDialogUtil.showDialog(this);
        alertDialog.show();
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.getToast(this, msg).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void showNotif(String title, String msg) {
        Notification notification = NotificationsUtil.getSimpleNotification(this, ListActivity.class, App.CHANNEL_1_ID, title, msg, R.drawable.ic_notifi_1, R.drawable.ic_notifi_1, NotificationManager.IMPORTANCE_DEFAULT, true, 1, 0);
        NotificationManagerCompat.from(this).notify(1, notification);
    }

}

