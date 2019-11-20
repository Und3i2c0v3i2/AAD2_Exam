package com.example.aad2.view.activities;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.aad2.App;
import com.example.aad2.db.DBRepository;
import com.example.aad2.prefs.PrefsRepository;
import com.example.aad2.util.AlertDialogUtil;
import com.example.aad2.util.NotificationsUtil;
import com.example.aad2.util.ToastUtil;

import static com.example.aad2.prefs.PrefsRepository.NOTIF;
import static com.example.aad2.prefs.PrefsRepository.TOAST;
import static com.example.aad2.util.AlertDialogUtil.isAboutShowing;


public abstract class BaseActivity extends AppCompatActivity {


    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String ADDRESS = "address";
    public static final String IMG_URL = "img_url";


    protected DBRepository dbRepository;
    protected PrefsRepository prefsRepository;

    protected AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbRepository = App.getDbRepository();
        prefsRepository = App.getPrefsRepository();
    }


    /* ********** CHECK WHETHER TO SHOW NOTIFICATIONS/TOASTS ************* */
    public void checkPrefs(Context context, String title, String text) {
        String s = prefsRepository.enabled(PrefsRepository.INFO_TYPE);
        if (s.equals(TOAST)) {
            ToastUtil.showToast(context, title + ": " + text);
        }else if (s.equals(NOTIF)) {
            NotificationsUtil.getNotification(context, ListActivity.class, title, text);
        }
    }


    /* ******************* ALERT DIALOG *************** */

    public void showAboutDialog(Context context) {
        alertDialog = AlertDialogUtil.showDialog(context);
        alertDialog.show();
    }


    @Override
    protected void onResume() {
        super.onResume();

        if(isAboutShowing) {
            showAboutDialog(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // dismiss alert dialog if it is open so activity doesn't leak
        if(alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }



}

