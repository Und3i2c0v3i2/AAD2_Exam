package com.example.aad2.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.aad2.R;
import com.example.aad2.databinding.ActivityDetailsBinding;
import com.example.aad2.entity.Contact;
import com.example.aad2.entity.Phone;
import com.example.aad2.prefs.PrefsActivity;
import com.example.aad2.view.OnActionPerformedListener;
import com.example.aad2.view.adapters.RVAdapterPhone;
import com.example.aad2.view.fragments.CustomDialogFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.aad2.App.OBJECT_ID;


public class DetailsActivity
        extends BaseActivity
        implements View.OnClickListener, OnActionPerformedListener {


    private ActivityDetailsBinding binding;
    private Contact contact;
    private List<Phone> list;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        binding.setClickHandler(this);

        if(getIntent() != null) {
            id = getIntent().getIntExtra(OBJECT_ID, -1);
        }

        if(id != -1) {
            contact = dbRepository.getById(id);
            if(contact == null) {
                // just to avoid NPE
                contact = new Contact();
                list = new ArrayList<>();
            } else {
                list = dbRepository.getForeignCollection(contact.getId());
                contact.setPhones(list);
            }

            binding.setContact(contact);
            setupAdapter(list);

            setupToolbar();
        }
    }


    /* ************* ADAPTER **************** */
    private void setupAdapter(List<Phone> list) {
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RVAdapterPhone adapter = new RVAdapterPhone(list);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_add_phone:
                CustomDialogFragment dialogFragment = new CustomDialogFragment();
                dialogFragment.getData(contact);
                dialogFragment.show(getSupportFragmentManager(), "dialog");
                break;

            case R.id.btn_edit:
                Intent intentEdit = new Intent(this, EditActivity.class);
                intentEdit.putExtra(OBJECT_ID, contact.getId());
                startActivity(intentEdit);
                break;

            case R.id.btn_delete:
                int i = dbRepository.delete(contact);
                if(i != -1) {
                    checkPrefs(this, "Deleted", contact.getFirstName() + " " + contact.getLastName());
                }
                onBackPressed();
                finish();
                break;
        }
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


    protected void onResume() {
        super.onResume();

        contact = dbRepository.getById(contact.getId());
        list = dbRepository.getForeignCollection(contact.getId());
        binding.setContact(contact);
        setupAdapter(list);
    }

    @Override
    public void onActionPerformed(Bundle bundle) {
        Phone phone = bundle.getParcelable(OBJECT_PARCELABLE);
        int i = dbRepository.insert(phone);
        if(i != -1) {
            list.add(phone);
            setupAdapter(list);
        }
    }
}
