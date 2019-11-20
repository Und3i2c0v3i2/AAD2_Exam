package com.example.aad2.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

import static com.example.aad2.db.DBHelper.COLUMN_FOREIGN_ID;


@DatabaseTable(tableName = Contact.TABLE_NAME_CONTACT)
public class Contact implements Parcelable {

    public static final String TABLE_NAME_CONTACT = "contact_table";


    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String imgUrl;
    @DatabaseField
    private String firstName;
    @DatabaseField
    private String lastName;
    @DatabaseField
    private String address;

//    @ForeignCollectionField(foreignFieldName = COLUMN_FOREIGN_ID, eager = true)
    private Collection<Phone> phones;

    protected Contact(Parcel in) {
        id = in.readInt();
        imgUrl = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        address = in.readString();
    }


    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(imgUrl);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(address);
    }


    public Contact() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Collection<Phone> phones) {
        this.phones = phones;
    }

}
