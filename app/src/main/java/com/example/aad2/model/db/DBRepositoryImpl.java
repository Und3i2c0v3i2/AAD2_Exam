package com.example.aad2.model.db;


import com.example.aad2.model.entity.Contact;
import com.example.aad2.model.entity.Phone;

import java.util.ArrayList;
import java.util.List;

public class DBRepositoryImpl implements DBRepository {

    private DBHelper dbHelper;

    public DBRepositoryImpl(DBHelper dbHelper) {
        this.dbHelper = dbHelper;

    }

    // CRUD
    @Override
    public int insert(Contact contact) {
        return dbHelper.insert(contact);
    }

    @Override
    public int delete(Contact contact) {
        return dbHelper.delete(contact);
    }

    @Override
    public int modify(Contact contact) {
        return dbHelper.modify(contact);
    }

    @Override
    public List<Contact> getAll() {
        return dbHelper.getAll();
    }

    @Override
    public Contact getById(int id) {
        return dbHelper.getById(id);
    }

    @Override
    public int insert(Phone phone) {
        return dbHelper.insert(phone);
    }

    @Override
    public List<Phone> getByContact(int id) {
        return dbHelper.getByContact(id);
    }


}
