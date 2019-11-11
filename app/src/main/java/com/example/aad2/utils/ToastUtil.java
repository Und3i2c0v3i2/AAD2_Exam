package com.example.aad2.utils;

import android.content.Context;
import android.widget.Toast;


public class ToastUtil {

  private ToastUtil() {}

  public static void toastError(final Context context, final String message) {
    getToast(context, message).show();
  }

  public static Toast getToast(Context context, String message) {
    return Toast.makeText(context, message, Toast.LENGTH_SHORT);
  }

}
