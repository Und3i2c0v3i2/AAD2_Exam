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
import com.example.aad2.databinding.ActivityAddBinding;
import com.example.aad2.entity.Contact;
import com.example.aad2.prefs.PrefsActivity;

import java.util.Objects;


public class AddActivity
        extends BaseActivity
        implements View.OnClickListener {


    private ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        binding.setClickHandler(this);

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
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_save:
                Contact contact = createNew();
                int i = dbRepository.insert(contact);
                if(i != -1) {
                    checkPrefs(this, "Added: ", contact.getFirstName() + " " + contact.getLastName());
                }
                onBackPressed();
                finish();
                break;

            case R.id.btn_cancel:
                onBackPressed();
                break;

        }

    }

    private Contact createNew() {
        Contact contact = new Contact();
        contact.setFirstName(binding.inputFirstName.getEditText().getText().toString());
        contact.setLastName(binding.inputLastName.getEditText().getText().toString());
        contact.setAddress(binding.inputAddress.getEditText().getText().toString());
        contact.setImgUrl(binding.inputImgUrl.getEditText().getText().toString());

        return contact;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(FIRST_NAME, binding.inputFirstName.getEditText().getText().toString());
        outState.putString(LAST_NAME, binding.inputLastName.getEditText().getText().toString());
        outState.putString(ADDRESS, binding.inputAddress.getEditText().getText().toString());
        outState.putString(IMG_URL, binding.inputImgUrl.getEditText().getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        binding.inputFirstName.getEditText().setText(savedInstanceState.getString(FIRST_NAME));
        binding.inputLastName.getEditText().setText(savedInstanceState.getString(LAST_NAME));
        binding.inputAddress.getEditText().setText(savedInstanceState.getString(ADDRESS));
        binding.inputImgUrl.getEditText().setText(savedInstanceState.getString(IMG_URL));

    }
}
