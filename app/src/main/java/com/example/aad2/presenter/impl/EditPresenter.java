package com.example.aad2.presenter.impl;

import android.content.Context;

import com.example.aad2.App;
import com.example.aad2.model.db.DBRepository;
import com.example.aad2.model.entity.Contact;
import com.example.aad2.model.prefs.PrefsRepository;
import com.example.aad2.presenter.abstr.EditContract;


public class EditPresenter implements EditContract.Presenter{

    private EditContract.View view;
    private DBRepository dbRepository;

    private PrefsRepository prefsRepository;

    private Contact contact;


    public EditPresenter(EditContract.View view) {
        this.view = view;
        dbRepository = App.getDbRepository();
        this.view.setPresenter(this);
        prefsRepository = App.getSharedPrefsRepository();
    }

    @Override
    public void getSelected(int id) {
        contact = dbRepository.getById(id);
    }

    @Override
    public void actionEdit(Contact contact) {
        dbRepository.modify(contact);
        view.navigateBack();
        if(prefsRepository.isEnabledToast()) {
            view.showToast(contact.getFirstName() + " " + contact.getLastName() + " modified");
        } else if(prefsRepository.isEnabledNotif()) {
            view.showNotif("Modified", contact.getFirstName() + " " + contact.getLastName());
        }
    }

    @Override
    public void actionCancel() {
        view.navigateBack();
    }

    @Override
    public void start() {
        view.showSelected(contact);
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
