package com.example.aad2.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringDef;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.example.aad2.db.DBHelper.COLUMN_FOREIGN_ID;


@DatabaseTable(tableName = Phone.TABLE_NAME_PHONE)
public class Phone implements Parcelable {

//    @StringDef({
//            Type.MOBILE,
//            Type.HOME,
//            Type.WORK})
//
//    public @interface Type {
//        String MOBILE = "Mobile";
//        String HOME = "Home";
//        String WORK = "Work";
//    }

    public static final String TABLE_NAME_PHONE = "phone_table";


    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String number;
    @DatabaseField
    private String type;
    @DatabaseField(foreign = true, columnName = COLUMN_FOREIGN_ID)
    private Contact contact;

    protected Phone(Parcel in) {
        id = in.readInt();
        number = in.readString();
        type = in.readString();
    }

    public static final Creator<Phone> CREATOR = new Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(number);
        dest.writeString(type);
    }


    public Phone() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}
