package com.example.aad2.presenter.impl;

import android.content.Context;

import com.example.aad2.App;
import com.example.aad2.model.db.DBRepository;
import com.example.aad2.model.entity.Contact;
import com.example.aad2.model.prefs.PrefsRepository;
import com.example.aad2.presenter.abstr.AddContract;


public class AddPresenter implements AddContract.Presenter {

    private AddContract.View view;
    private DBRepository dbRepository;

    private PrefsRepository prefsRepository;


    public AddPresenter(AddContract.View view) {
        this.view = view;
        dbRepository = App.getDbRepository();
        this.view.setPresenter(this);
        prefsRepository = App.getSharedPrefsRepository();
    }


    @Override
    public void actionAdd(Contact contact) {
        int i = dbRepository.insert(contact);
        if (i > -1) {
            view.navigateBack();
            if(prefsRepository.isEnabledToast()) {
                view.showToast(contact.getFirstName() + " " + contact.getLastName() + " added");
            } else if(prefsRepository.isEnabledNotif()) {
                view.showNotif("Added", contact.getFirstName() + " " + contact.getLastName());
            }
        }
    }

    @Override
    public void actionCancel() {
        view.navigateBack();
    }

    @Override
    public void start() {
        /* empty */
    }

    @Override
    public void actionSettings() {
        view.navigateToSettings();
    }

    @Override
    public void actionAbout(Context context) {
        view.showAboutDialog(context);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}

