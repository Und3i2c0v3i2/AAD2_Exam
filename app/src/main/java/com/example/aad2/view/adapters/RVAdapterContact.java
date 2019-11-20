package com.example.aad2.view.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aad2.R;
import com.example.aad2.databinding.HolderContactBinding;
import com.example.aad2.entity.Contact;
import com.example.aad2.view.OnActionPerformedListener;

import java.util.List;

import static com.example.aad2.App.OBJECT_ID;

public class RVAdapterContact extends RecyclerView.Adapter<RVAdapterContact.ViewHolder> {


    private List<Contact> list;
    private OnActionPerformedListener listener;

    public RVAdapterContact(List<Contact> list, OnActionPerformedListener listener) {
        this.list = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup contact, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(contact.getContext());
        HolderContactBinding binding = DataBindingUtil.inflate(inflater, R.layout.holder_contact, contact, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contact contact = list.get(position);
        holder.holderBinding.setContact(contact);

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        HolderContactBinding holderBinding;

        public ViewHolder(HolderContactBinding holderBinding) {
            super(holderBinding.getRoot());
            this.holderBinding = holderBinding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(OBJECT_ID, list.get(getAdapterPosition()).getId());
                    listener.onActionPerformed(bundle);
                }
            });
        }
    }


}