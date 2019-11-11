package com.example.aad2.view;


import android.content.Context;


import com.example.aad2.presenter.abstr.BasePresenter;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

    void navigateToSettings();

    void showAboutDialog(Context context);
    void showToast(String msg);
    void showNotif(String title, String msg);
}
