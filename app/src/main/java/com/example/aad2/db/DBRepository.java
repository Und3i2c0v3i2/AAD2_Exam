package com.example.aad2.db;


import com.example.aad2.entity.Contact;
import com.example.aad2.entity.Phone;

import java.util.List;


public interface DBRepository {

    int insert(Contact contact);
    int delete(Contact contact);
    int modify(Contact contact);
    Contact getById(int id);
    List<Contact> getAll();
    List<Phone> getForeignCollection(int id);
    void deleteForeignCollection(int id);

    int insert(Phone child);


}
