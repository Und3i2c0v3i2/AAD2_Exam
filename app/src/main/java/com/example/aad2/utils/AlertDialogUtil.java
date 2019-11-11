package com.example.aad2.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.aad2.R;

public class AlertDialogUtil {


    /* for for deciding if we should show dialog when rotation change */
    public static boolean isAboutShowing;

    public static AlertDialog showDialog(Context context) {

        isAboutShowing = true;
        AlertDialog.Builder builder =
                new AlertDialog.Builder(context)
                        .setMessage("Example from AAD2 exam:\nThis application was made for practice purpose only.\nIvana")
                        .setTitle("About")
                        .setIcon(R.drawable.ic_info_outline_black_24dp)
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
