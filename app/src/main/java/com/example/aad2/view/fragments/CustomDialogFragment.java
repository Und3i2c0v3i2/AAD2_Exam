package com.example.aad2.view.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatSpinner;

import com.example.aad2.view.OnActionPerformedListener;
import com.example.aad2.R;
import com.example.aad2.model.entity.Phone;
import com.example.aad2.model.entity.Contact;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import static com.example.aad2.view.OnActionPerformedListener.ACTION_ADD_PHONE;
import static com.example.aad2.view.OnActionPerformedListener.BUNDLE_KEY;
import static com.example.aad2.view.OnActionPerformedListener.PARCELABLE_PHONE;

public class CustomDialogFragment extends AppCompatDialogFragment {

    private TextInputLayout phoneNo;
    private AppCompatSpinner spinner;
    private ArrayAdapter adapter;
    private OnActionPerformedListener listener;
    private String choice;
    private Phone phone = new Phone();
    private Contact contact;

    /* member variable so we can show dialog on rotation if it has been showing */
    public static boolean isShowing;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        phoneNo = view.findViewById(R.id.add_phone);
        spinner = view.findViewById(R.id.spinner_phone);

        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.phones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice = parent.getItemAtPosition(position).toString();
                phone.setType(choice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(savedInstanceState != null)
            phoneNo.getEditText().setText(savedInstanceState.getString("number"));

        return getDialog(view);
    }


    private Dialog getDialog(View view) {

        isShowing = true;

        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setView(view)
                .setTitle("Add phone for contact " + contact.getLastName() + " " + contact.getFirstName())
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        isShowing = false;

                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        isShowing = false;

                        phone.setNumber(Objects.requireNonNull(phoneNo.getEditText()).getText().toString());
                        phone.setContact(contact);

                        Bundle bundle = new Bundle();
                        bundle.putString(BUNDLE_KEY, ACTION_ADD_PHONE);
                        bundle.putParcelable(PARCELABLE_PHONE, phone);

                        listener.onActionPerformed(bundle);
                    }
                });


        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnActionPerformedListener) {
            listener = (OnActionPerformedListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void getData(Contact contact) {
        this.contact = contact;
    }


    @Override
    public void onPause() {
        super.onPause();
        dismiss();
    }

}
