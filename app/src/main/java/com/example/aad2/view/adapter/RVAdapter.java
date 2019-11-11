package com.example.aad2.view.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.aad2.model.entity.Contact;
import com.example.aad2.view.OnActionPerformedListener;
import com.example.aad2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.aad2.view.OnActionPerformedListener.OPEN_DETAILS;
import static com.example.aad2.view.OnActionPerformedListener.BUNDLE_KEY;
import static com.example.aad2.view.OnActionPerformedListener.PARCELABLE_CONTACT;

public class RVAdapter extends Adapter<RVAdapter.ViewHolder> {

    private List<Contact> contacts;
    private OnActionPerformedListener listener;

    public RVAdapter(List<Contact> list, OnActionPerformedListener listener) {
        contacts = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.firstName.setText(contacts.get(position).getFirstName());
        holder.lastName.setText(contacts.get(position).getLastName());
        Picasso.get()
                .load(contacts.get(position).getImgUrl())
                .resize(150, 150)
                .centerInside()
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        if (contacts != null)
            return contacts.size();
        else
            return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lastName;
        private TextView firstName;
        private ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lastName = itemView.findViewById(R.id.title);
            firstName = itemView.findViewById(R.id.subtitle);
            img = itemView.findViewById(R.id.view_holder_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    Contact contact = contacts.get(getAdapterPosition());
                    bundle.putString(BUNDLE_KEY, OPEN_DETAILS);
                    bundle.putParcelable(PARCELABLE_CONTACT, contact);
//
                    listener.onActionPerformed(bundle);
                }
            });
        }
    }
}
