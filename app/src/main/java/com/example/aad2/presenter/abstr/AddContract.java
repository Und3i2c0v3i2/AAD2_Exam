package com.example.aad2.presenter.abstr;

import com.example.aad2.model.entity.Contact;
import com.example.aad2.view.BaseView;

public interface AddContract {

    interface Presenter extends BasePresenter {

        void actionAdd(Contact contact);
        void actionCancel();
    }


    interface View extends BaseView {

        void navigateBack();
    }
}
