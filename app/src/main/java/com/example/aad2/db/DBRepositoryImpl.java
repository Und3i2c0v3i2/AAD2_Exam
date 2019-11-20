package com.example.aad2.db;


import com.example.aad2.entity.Contact;
import com.example.aad2.entity.Phone;

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
    public List<Contact> getAll() {
        return dbHelper.getAll();
    }

    @Override
    public List<Phone> getForeignCollection(int id) {
        return dbHelper.getForeignCollection(id);
    }

    @Override
    public void deleteForeignCollection(int id) {
        dbHelper.deleteForeignCollection(id);
    }

    @Override
    public int insert(Phone child) {
        return dbHelper.insert(child);
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
    public Contact getById(int id) {
        return dbHelper.getById(id);
    }





}
