package com.example.aad2.presenter.abstr;

import com.example.aad2.model.entity.Contact;
import com.example.aad2.model.entity.Phone;
import com.example.aad2.view.BaseView;


public interface DetailsContract {

    interface Presenter extends BasePresenter {

        void getSelected(int id);
        void actionDelete(Contact contact);
        void actionEdit(Contact contact);
        void actionAddPhone(Phone phone);
        void actionOpenAddPhone();
    }


    interface View extends BaseView {

        void showSelected(Contact contact);
        void navigateToEdit(Contact contact);
        void navigateToAddPhone();
        void updateView(Contact contact);
        void navigateBack();
    }
}
