package com.example.aad2.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aad2.R;
import com.example.aad2.model.entity.Contact;
import com.example.aad2.presenter.abstr.BasePresenter;
import com.example.aad2.presenter.abstr.ListContract;
import com.example.aad2.presenter.impl.ListPresenter;
import com.example.aad2.view.OnActionPerformedListener;
import com.example.aad2.view.adapter.RVAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import static com.example.aad2.utils.AlertDialogUtil.isAboutShowing;

public class ListActivity
        extends BaseActivity
        implements ListContract.View, OnActionPerformedListener {


    private RVAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView img;

    private ListContract.Presenter presenter;
    private Contact selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        presenter = new ListPresenter(this);

        setupViews();
        setupToolbar();

    }

    private void setupViews() {
        img = findViewById(R.id.img_main);
        Picasso.get()
                .load("https://images.pexels.com/photos/796602/pexels-photo-796602.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
                .fit()
                .into(img);
    }

    private void setupAdapter(List<Contact> list) {
        recyclerView = findViewById(R.id.data_list);
        adapter = new RVAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
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
                presenter.actionSettings();
                return true;
            case R.id.menu_about:
                presenter.actionAbout(this);
                return true;
            case R.id.menu_add:
                presenter.actionAdd();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActionPerformed(Bundle bundle) {

        String key = bundle.getString(BUNDLE_KEY);

        if (OPEN_DETAILS.equals(Objects.requireNonNull(key))) {
            selected = bundle.getParcelable(PARCELABLE_CONTACT);
            presenter.actionSelected(Objects.requireNonNull(selected).getId());
        }
    }


    @Override
    public void showAll(List<Contact> list) {
        setupAdapter(list);
    }

    @Override
    public void navigateToAdd() {
        startActivity(new Intent(this, AddActivity.class));
    }

    @Override
    public void navigateToDetails(int id) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }


    @Override
    public void setPresenter(BasePresenter listPresenter) {
        this.presenter = (ListContract.Presenter) listPresenter;
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
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();

        // dismiss about dialog if it is open so activity doesn't leak
        if(alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }

}
