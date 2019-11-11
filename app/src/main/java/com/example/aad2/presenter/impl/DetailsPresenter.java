package com.example.aad2.presenter.impl;

import android.content.Context;

import com.example.aad2.App;
import com.example.aad2.model.db.DBRepository;
import com.example.aad2.model.entity.Contact;
import com.example.aad2.model.entity.Phone;
import com.example.aad2.model.prefs.PrefsRepository;
import com.example.aad2.presenter.abstr.DetailsContract;


public class DetailsPresenter implements DetailsContract.Presenter {


    private DetailsContract.View view;
    private DBRepository dbRepository;

    private PrefsRepository prefsRepository;

    private Contact contact;


    public DetailsPresenter(DetailsContract.View view) {
        this.view = view;
        dbRepository = App.getDbRepository();
        this.view.setPresenter(this);
        prefsRepository = App.getSharedPrefsRepository();
    }


    @Override
    public void getSelected(int id) {
        contact = dbRepository.getById(id);
        contact.setPhones(dbRepository.getByContact(id));
    }


    @Override
    public void actionDelete(Contact contact) {
        dbRepository.delete(contact);
        view.navigateBack();
        if(prefsRepository.isEnabledToast()) {
            view.showToast(contact.getFirstName() + " " + contact.getLastName() + " deleted");
        } else if(prefsRepository.isEnabledNotif()) {
            view.showNotif("Deleted", contact.getFirstName() + " " + contact.getLastName());
        }
    }

    @Override
    public void actionEdit(Contact contact) {
        view.navigateToEdit(contact);
    }

    @Override
    public void actionAddPhone(Phone phone) {
        dbRepository.insert(phone);
        getSelected(contact.getId());
        view.updateView(contact);
    }

    @Override
    public void actionOpenAddPhone() {
        view.navigateToAddPhone();
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
