package com.example.aad2.util;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.aad2.R;


public class AlertDialogUtil {

    /* for deciding if we should show dialog on rotation change */
    public static boolean isAboutShowing;

    public static AlertDialog showDialog(Context context) {

        isAboutShowing = true;
        AlertDialog.Builder builder =
                new AlertDialog.Builder(context)
                        .setMessage("Exam AAD2:\nThis application was made for FTN Informatika final Exam.\nIvana Kilibarda")
                        .setTitle("About")
                        .setIcon(R.drawable.ic_about)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                isAboutShowing = false;
                            }
                        });


        return builder.create();
    }
}
