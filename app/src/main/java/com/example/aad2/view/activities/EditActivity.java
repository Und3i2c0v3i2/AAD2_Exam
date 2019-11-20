package com.example.aad2.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.aad2.R;
import com.example.aad2.databinding.ActivityEditBinding;
import com.example.aad2.entity.Contact;
import com.example.aad2.prefs.PrefsActivity;

import java.util.Objects;

import static com.example.aad2.App.OBJECT_ID;

public class EditActivity
        extends BaseActivity
        implements View.OnClickListener {

    private ActivityEditBinding binding;
    private int id;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit);
        binding.setClickHandler(this);

        if(getIntent() != null) {
            id = getIntent().getIntExtra(OBJECT_ID, -1);
        }

        if(id != -1) {
            contact = dbRepository.getById(id);
            if (contact == null) {
                // just to avoid NPE
                contact = new Contact();
            }

            binding.setContact(contact);
        }

        setupToolbar();
    }


    /* ************* TOOLBAR & MENU ************** */

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) binding.toolbar;
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
                startActivity(new Intent(this, PrefsActivity.class));
                return true;

            case R.id.menu_about:
                showAboutDialog(this);
                return true;

            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_edit:
                update();
                int i = dbRepository.modify(contact);
                if(i != -1) {
                    checkPrefs(this, "Updated ", contact.getFirstName() + " " + contact.getLastName());
                }
                onBackPressed();
                finish();
                break;

            case R.id.btn_cancel:
                onBackPressed();
                break;

        }
    }

    private void update() {

        contact.setFirstName(binding.firstNameEdit.getEditText().getText().toString());
        contact.setLastName(binding.lastNameEdit.getEditText().getText().toString());
        contact.setAddress(binding.addressEdit.getEditText().getText().toString());
        contact.setImgUrl(binding.imgUrlEdit.getEditText().getText().toString());

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(FIRST_NAME, binding.firstNameEdit.getEditText().getText().toString());
        outState.putString(LAST_NAME, binding.lastNameEdit.getEditText().getText().toString());
        outState.putString(ADDRESS, binding.addressEdit.getEditText().getText().toString());
        outState.putString(IMG_URL, binding.imgUrlEdit.getEditText().getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        binding.firstNameEdit.getEditText().setText(savedInstanceState.getString(FIRST_NAME));
        binding.lastNameEdit.getEditText().setText(savedInstanceState.getString(LAST_NAME));
        binding.addressEdit.getEditText().setText(savedInstanceState.getString(ADDRESS));
        binding.imgUrlEdit.getEditText().setText(savedInstanceState.getString(IMG_URL));

    }
}
