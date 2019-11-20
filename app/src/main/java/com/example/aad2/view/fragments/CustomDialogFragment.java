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

//    private TextInputLayout phoneNo;
//    private AppCompatSpinner spinner;
//    private ArrayAdapter adapter;
    private OnActionPerformedListener listener;
    private DialogLayoutBinding binding;
//    private String choice;
    private Phone phone;
    private Contact contact;

    /* member variable so we can show dialog on rotation if it has been showing */
    public static boolean isShowing;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil
                .inflate(getActivity().getLayoutInflater(), R.layout.dialog_layout, null, false);

//        binding.setClickHandler(this);
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


//        Dialog dialog = new Dialog(context);
//        dialog.setContentView(binding.getRoot());
//        dialog.show();
//
//
//        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_layout, null);

//        phoneNo = view.findViewById(R.id.add_phone);
//        spinner = view.findViewById(R.id.spinner_phone);
//
//        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.phone_types, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);


//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                choice = parent.getItemAtPosition(position).toString();
//                phone.setType(choice);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        if(savedInstanceState != null)
//            phoneNo.getEditText().setText(savedInstanceState.getString("number"));

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
