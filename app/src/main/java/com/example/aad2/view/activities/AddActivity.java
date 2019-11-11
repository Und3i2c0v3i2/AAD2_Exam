package com.example.aad2.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.aad2.R;
import com.example.aad2.model.entity.Contact;
import com.example.aad2.presenter.abstr.AddContract;
import com.example.aad2.presenter.abstr.BasePresenter;
import com.example.aad2.presenter.impl.AddPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import static com.example.aad2.utils.AlertDialogUtil.isAboutShowing;

public class AddActivity
        extends BaseActivity
        implements AddContract.View {


    private TextInputLayout firstName;
    private TextInputLayout lastName;
    private TextInputLayout address;
    private TextInputLayout imgUrl;

    private Toolbar toolbar;

    private MaterialButton btnSave;
    private MaterialButton btnCancel;

    private AddContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        presenter = new AddPresenter(this);

        findViews();
        setupBtns();
        setupToolbar();

    }


    private void findViews() {
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        address = findViewById(R.id.address);
        imgUrl = findViewById(R.id.img_url);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);
    }

    private void setupBtns() {
        btnSave.setOnClickListener(actionListener);
        btnCancel.setOnClickListener(actionListener);
    }


    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
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
                navigateBack();
                return true;

        }
        return true;
    }

    private View.OnClickListener actionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_cancel:
                    presenter.actionCancel();
                    break;
                case R.id.btn_save:
                    save();
                    break;
            }
        }
    };

    private void save() {
        Contact contact = new Contact();
        contact.setFirstName(Objects.requireNonNull(firstName.getEditText()).getText().toString());
        contact.setLastName(Objects.requireNonNull(lastName.getEditText()).getText().toString());
        contact.setAddress(Objects.requireNonNull(address.getEditText()).getText().toString());
        contact.setImgUrl(Objects.requireNonNull(imgUrl.getEditText()).getText().toString());

        presenter.actionAdd(contact);
    }


    @Override
    public void navigateBack() {
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();

        if(isAboutShowing) {
            showAboutDialog(this);
        }
    }


    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (AddContract.Presenter) presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();

        // dismiss about dialog if it is open so activity doesn't leak
        if(alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(FIRST_NAME, (Objects.requireNonNull(firstName.getEditText())).getText().toString());
        outState.putString(LAST_NAME, (Objects.requireNonNull(lastName.getEditText())).getText().toString());
        outState.putString(ADDRESS, (Objects.requireNonNull(address.getEditText())).getText().toString());
        outState.putString(IMG_URL, (Objects.requireNonNull(imgUrl.getEditText())).getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Objects.requireNonNull(firstName.getEditText()).setText(savedInstanceState.getString(FIRST_NAME));
        Objects.requireNonNull(lastName.getEditText()).setText(savedInstanceState.getString(LAST_NAME));
        Objects.requireNonNull(address.getEditText()).setText(savedInstanceState.getString(ADDRESS));
        Objects.requireNonNull(imgUrl.getEditText()).setText(savedInstanceState.getString(IMG_URL));
    }
}
