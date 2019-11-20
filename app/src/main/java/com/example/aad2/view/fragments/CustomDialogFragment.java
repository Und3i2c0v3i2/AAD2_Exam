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
import androidx.databinding.DataBindingUtil;

import com.example.aad2.databinding.DialogLayoutBinding;
import com.example.aad2.view.OnActionPerformedListener;
import com.example.aad2.R;
import com.example.aad2.entity.Phone;
import com.example.aad2.entity.Contact;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import static com.example.aad2.view.OnActionPerformedListener.OBJECT_PARCELABLE;

public class CustomDialogFragment extends AppCompatDialogFragment {

    private OnActionPerformedListener listener;
    private DialogLayoutBinding binding;
    private Phone phone;
    private Contact contact;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil
                .inflate(getActivity().getLayoutInflater(), R.layout.dialog_layout, null, false);

        phone = new Phone();
        binding.spinnerPhone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                phone.setType(parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return getDialog(binding.getRoot());
    }


    private Dialog getDialog(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setView(view)
                .setTitle("Add phone for contact " + contact.getLastName() + " " + contact.getFirstName())
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        phone.setNumber(Objects.requireNonNull(binding.addPhone.getEditText()).getText().toString());
                        phone.setContact(contact);

                        Bundle bundle = new Bundle();
                        bundle.putParcelable(OBJECT_PARCELABLE, phone);

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
