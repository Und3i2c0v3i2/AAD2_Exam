package com.example.aad2.presenter.abstr;

import com.example.aad2.model.entity.Contact;
import com.example.aad2.view.BaseView;

import java.util.List;

public interface ListContract {

    interface Presenter extends BasePresenter {

        void actionSelected(int id);
        void actionAdd();

    }


    interface View extends BaseView {

        void showAll(List<Contact> list);
        void navigateToAdd();
        void navigateToDetails(int id);
    }
}
