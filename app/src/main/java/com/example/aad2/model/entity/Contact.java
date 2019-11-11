package com.example.aad2.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;


@DatabaseTable(tableName = Contact.TABLE_NAME_CONTACT)
public class Contact implements Parcelable {

    public static final String TABLE_NAME_CONTACT = "contact_table";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IMG_URL = "img_url";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_CONTACT = "contact";


    @DatabaseField(columnName = COLUMN_ID, generatedId = true)
    private int id;
    @DatabaseField(columnName = COLUMN_IMG_URL)
    private String imgUrl;
    @DatabaseField(columnName = Contact.COLUMN_FIRST_NAME)
    private String firstName;
    @DatabaseField(columnName = Contact.COLUMN_LAST_NAME)
    private String lastName;
    @DatabaseField(columnName = Contact.COLUMN_ADDRESS)
    private String address;

    @ForeignCollectionField(foreignFieldName = Contact.COLUMN_CONTACT, eager = true)
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
