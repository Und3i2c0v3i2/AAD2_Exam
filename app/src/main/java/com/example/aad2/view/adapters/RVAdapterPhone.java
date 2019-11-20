package com.example.aad2.view.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aad2.R;
import com.example.aad2.databinding.HolderPhoneBinding;
import com.example.aad2.entity.Phone;

import java.util.List;

public class RVAdapterPhone extends RecyclerView.Adapter<RVAdapterPhone.ViewHolder> {


    private List<Phone> list;

    public RVAdapterPhone(List<Phone> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        HolderPhoneBinding binding = DataBindingUtil.inflate(inflater, R.layout.holder_phone, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Phone phone = list.get(position);
        holder.holderBinding.setPhone(phone);

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        HolderPhoneBinding holderBinding;

        public ViewHolder(HolderPhoneBinding holderBinding) {
            super(holderBinding.getRoot());
            this.holderBinding = holderBinding;
        }
    }


}