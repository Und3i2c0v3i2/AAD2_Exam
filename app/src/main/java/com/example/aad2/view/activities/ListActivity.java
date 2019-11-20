package com.example.aad2.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aad2.R;
import com.example.aad2.databinding.ActivityListBinding;
import com.example.aad2.entity.Contact;
import com.example.aad2.prefs.PrefsActivity;
import com.example.aad2.view.OnActionPerformedListener;
import com.example.aad2.view.adapters.RVAdapterContact;

import java.util.List;

import static com.example.aad2.App.OBJECT_ID;

public class ListActivity
        extends BaseActivity
        implements OnActionPerformedListener, View.OnClickListener {


    // retrofitRepository, dbRepository, prefsRepository ---> BaseActivity

    private ActivityListBinding binding;

    private List<Contact> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        setupToolbar();
        setupButtons();

        // get from database
        list = dbRepository.getAll();
        setupAdapter(list);

    }



    /* ************* ADAPTER **************** */
    private void setupAdapter(List<Contact> list) {
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RVAdapterContact adapter = new RVAdapterContact(list, this);
        recyclerView.setAdapter(adapter);
    }


    /* ************************ ADAPTER LISTENER ************** */
    @Override
    public void onActionPerformed(Bundle bundle) {
        int id = bundle.getInt(OBJECT_ID);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(OBJECT_ID, id);
        startActivity(intent);
    }


    /* ************* TOOLBAR & MENU ************** */

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) binding.toolbar;
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

            case R.id.menu_add:
                startActivity(new Intent(this, AddActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    /* ************* BUTTONS ************** */
    private void setupButtons() {
        binding.setClickHandler(this);
    }

    @Override
    public void onClick(View v) {
        // search view listener if included in this activity
        switch (v.getId()) {

            case 1:
                break;

            case 2:
                break;
        }
    }





    /* ********************** LIFECYCLE ************************** */

    @Override
    protected void onResume() {
        super.onResume();

        list = dbRepository.getAll();
        setupAdapter(list);
    }


}
