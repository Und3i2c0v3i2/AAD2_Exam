package com.example.aad2.view;


import android.os.Bundle;

public interface OnActionPerformedListener {

    /* define keys for all actions used with a Bundle */

    String BUNDLE_KEY = "bundle_key";

    // data
    String PARCELABLE_CONTACT = "parcelable_contact";
    String PARCELABLE_PHONE = "parcelable_phone";

    // navigation options
    String OPEN_DETAILS = "open_details";
//    String OPEN_PREFS = "open_preferences";
//    String OPEN_ADD = "open_add";
//    String OPEN_EDIT = "open_edit";

    // actions
    String ACTION_EDIT_CONTACT = "action_edit_contact";
    String ACTION_ADD_PHONE = "action_add_phone";

    void onActionPerformed(Bundle bundle);
}
