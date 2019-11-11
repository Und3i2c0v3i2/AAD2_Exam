package com.example.aad2.presenter.impl;

import android.content.Context;

import com.example.aad2.App;
import com.example.aad2.model.db.DBRepository;
import com.example.aad2.presenter.abstr.ListContract;

public class ListPresenter implements ListContract.Presenter {

    private ListContract.View view;
    private DBRepository dbRepository;


    public ListPresenter(ListContract.View view) {
        this.view = view;
        dbRepository = App.getDbRepository();
        this.view.setPresenter(this);
    }


    @Override
    public void start() {
        view.showAll(dbRepository.getAll());
    }

    @Override
    public void actionSelected(int id) {
        view.navigateToDetails(id);
    }

    @Override
    public void actionSettings() {
        view.navigateToSettings();
    }

    @Override
    public void actionAdd() {
        view.navigateToAdd();
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
