package com.example.aad2.presenter.abstr;

import android.content.Context;

public interface BasePresenter {

    void start();

    void actionSettings();
    void actionAbout(Context context);

    void onDestroy();
}
