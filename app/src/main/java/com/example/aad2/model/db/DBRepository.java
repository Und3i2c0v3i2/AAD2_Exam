package com.example.aad2.model.db;

import com.example.aad2.model.entity.Contact;
import com.example.aad2.model.entity.Phone;

import java.util.List;


public interface DBRepository {

    int insert(Contact contact);
    int delete(Contact contact);
    int modify(Contact contact);
    Contact getById(int id);
    List<Contact> getAll();

    int insert(Phone phone);
    List<Phone> getByContact(int id);

}
