package com.example.aad2.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aad2.R;
import com.example.aad2.model.entity.Contact;
import com.example.aad2.model.entity.Phone;
import com.example.aad2.presenter.abstr.BasePresenter;
import com.example.aad2.presenter.abstr.DetailsContract;
import com.example.aad2.presenter.impl.DetailsPresenter;
import com.example.aad2.utils.StringBuilderUtil;
import com.example.aad2.view.OnActionPerformedListener;
import com.example.aad2.view.fragments.CustomDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import static com.example.aad2.utils.AlertDialogUtil.isAboutShowing;
import static com.example.aad2.view.fragments.CustomDialogFragment.isShowing;


public class DetailsActivity
        extends BaseActivity
        implements DetailsContract.View, OnActionPerformedListener {


    private ImageView img;
    private TextView lastName,
            firstName,
            address,
            phones;
    private MaterialButton btnEdit,
            btnAddPhone,
            btnDelete;

    private Contact contact;
    int id;

    private DetailsContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("ID", -1);
        }

        presenter = new DetailsPresenter(this);

        findViews();
        setupBtns();
        setupToolbar();
    }


    private void findViews() {
        img = findViewById(R.id.img);
        lastName = findViewById(R.id.title);
        firstName = findViewById(R.id.subtitle);
        address = findViewById(R.id.description);
        phones = findViewById(R.id.phones);
        btnEdit = findViewById(R.id.btn_edit);
        btnAddPhone = findViewById(R.id.btn_add_phone);
        btnDelete = findViewById(R.id.btn_delete);
    }

    private void prepareViews() {

        Picasso.get()
                .load(contact.getImgUrl())
                .resize(0, 320)
                .centerInside()
                .into(img);
        lastName.setText(contact.getLastName());
        firstName.setText(contact.getFirstName());
        address.setText(contact.getAddress());

        StringBuilder builder = StringBuilderUtil.displayPhones((List<Phone>) contact.getPhones());
        phones.setText(builder);
    }

    private void setupBtns() {
        btnEdit.setOnClickListener(actionListener);
        btnDelete.setOnClickListener(actionListener);
        btnAddPhone.setOnClickListener(actionListener);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar())
                .setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_other, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_settings:
                presenter.actionSettings();
                return true;
            case R.id.menu_about:
                presenter.actionAbout(this);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;

        }
        return true;
    }

    private View.OnClickListener actionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_edit:
                    presenter.actionEdit(contact);
                    break;
                case R.id.btn_add_phone:
                    presenter.actionOpenAddPhone();
                    break;
                case R.id.btn_delete:
                    presenter.actionDelete(contact);
                    break;
            }
        }
    };


    @Override
    public void showSelected(Contact contact) {
        this.contact = contact;
        prepareViews();
    }

    @Override
    public void navigateToEdit(Contact contact) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("ID", contact.getId());
        startActivity(intent);
    }

    @Override
    public void navigateToAddPhone() {
        CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.getData(contact);
        dialogFragment.show(getSupportFragmentManager(), "dialog");
    }


    @Override
    public void updateView(Contact contact) {
        this.contact = contact;
        prepareViews();
    }


    @Override
    public void navigateBack() {
        onBackPressed();
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.getSelected(id);
        presenter.start();

        if(isShowing)
            navigateToAddPhone();

        if(isAboutShowing) {
            showAboutDialog(this);
        }
    }

    @Override
    public void onActionPerformed(Bundle bundle) {
        Phone phone = bundle.getParcelable(PARCELABLE_PHONE);
        presenter.actionAddPhone(phone);
        prepareViews();
    }


    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (DetailsContract.Presenter) presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        // dismiss about dialog if it is open so activity doesn't leak
        if(alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }
}
