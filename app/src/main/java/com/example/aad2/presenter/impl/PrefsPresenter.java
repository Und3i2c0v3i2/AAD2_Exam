package com.example.aad2.presenter.impl;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.Preference;

import com.example.aad2.App;
import com.example.aad2.model.prefs.PrefsRepository;
import com.example.aad2.presenter.abstr.PrefsContract;


public class PrefsPresenter implements PrefsContract.Presenter {


    private PrefsContract.View view;

    private PrefsRepository prefsRepository;


    public PrefsPresenter(PrefsContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        prefsRepository = App.getSharedPrefsRepository();
    }



    @Override
    public void actionSavePrefs(SharedPreferences sharedPreferences, String key) {
        prefsRepository.savePrefs((Preference) sharedPreferences, key);
    }

    @Override
    public void start() {

    }

    @Override
    public void actionSettings() {

    }

    @Override
    public void actionAbout(Context context) {

    }

    @Override
    public void onDestroy() {

    }
}
