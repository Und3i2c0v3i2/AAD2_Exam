package com.example.aad2.presenter.abstr;

import android.content.SharedPreferences;

import com.example.aad2.view.BaseView;

public interface PrefsContract {

    interface Presenter extends BasePresenter {

        void actionSavePrefs(SharedPreferences sharedPreferences, String key);

    }


    interface View extends BaseView {

//        void setSummary();
    }
}
