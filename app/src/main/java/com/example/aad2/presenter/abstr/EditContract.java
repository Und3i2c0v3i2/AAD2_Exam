package com.example.aad2.presenter.abstr;

import com.example.aad2.model.entity.Contact;
import com.example.aad2.view.BaseView;

public interface EditContract {

    interface Presenter extends BasePresenter {

        void getSelected(int id);
        void actionEdit(Contact contact);
        void actionCancel();
    }


    interface View extends BaseView {

        void showSelected(Contact contact);
        void navigateBack();
    }
}
