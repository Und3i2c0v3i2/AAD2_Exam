package com.example.aad2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.aad2.entity.Contact;
import com.example.aad2.entity.Phone;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBHelper extends OrmLiteSqliteOpenHelper {


    private static final String DATABASE_NAME = "ormlite.db";
    private static final int DATABASE_VERSION = 1;

    public static final String COLUMN_FOREIGN_ID = "foreign_id";

    private Dao<Contact, Integer> contactDao;
    private Dao<Phone, Integer> phoneDao;

    private static DBHelper instance;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        try {
            contactDao = getContactDao();
            phoneDao = getPhoneDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Contact.class);
            TableUtils.createTable(connectionSource, Phone.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Contact.class, true);
            TableUtils.dropTable(connectionSource, Phone.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBHelper getInstance(Context context) {

        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }


    public Dao<Contact, Integer> getContactDao() throws SQLException {
        if (contactDao == null) {
            contactDao = getDao(Contact.class);
        }

        return contactDao;
    }

    public Dao<Phone, Integer> getPhoneDao() throws SQLException {
        if (phoneDao == null) {
            phoneDao = getDao(Phone.class);
        }

        return phoneDao;
    }


    @Override
    public void close() {
        contactDao = null;
        phoneDao = null;
        super.close();
    }


    public int insert(Contact contact) {
        // return value (number of rows modified) can be used for toast or something like that
        int i = -1;
        if (contact != null) {

            if (checkIfExists(contact) == null) {

                try {
                    i = contactDao.create(contact);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return i;
    }

    public int modify(Contact contact) {

        int i = -1;
        if (contact != null) {

            try {
                i = contactDao.update(contact);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return i;
    }

    // If custom Id (ex. JSON)
    // to check if element is already in DB before inserting if we have some JSON id/unique field
    private Contact checkIfExists(Contact contact) {

        Contact exist = null;

        try {
            QueryBuilder<Contact, Integer> qb = contactDao.queryBuilder();
            Where<Contact, Integer> where = qb.where();
            where.eq("firstName", contact.getFirstName())
                    .and()
                    .eq("lastName", contact.getLastName())
                    .and()
                    .eq("address", contact.getAddress());
            PreparedQuery<Contact> pq = qb.prepare();
            exist = contactDao.queryForFirst(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    // If generated Id
    public Contact getById(int id) {

        Contact contact = null;
        try {
            contact = contactDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return contact;
    }


    public List<Contact> getAll() {

        List<Contact> list = new ArrayList<>();
        try {
            list = contactDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // If generated Id
    public int delete(Contact contact) {

        int i = -1;

        try {
            i = contactDao.delete(contact);
            deleteForeignCollection(contact.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }


    public int insert(Phone phone) {
        // return value (number of rows modified) can be used for toast or something like that
        int i = -1;
        if (phone != null) {
            try {
                i = phoneDao.create(phone);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return i;
    }


    public List<Phone> getForeignCollection(int id) {

        List<Phone> list = new ArrayList<>();

        try {
            list = phoneDao.queryForEq(COLUMN_FOREIGN_ID, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public void deleteForeignCollection(int id) {

        try {
            DeleteBuilder builder = phoneDao.deleteBuilder();
            builder.where()
                    .eq(COLUMN_FOREIGN_ID, id);
            builder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




    public void insertSomeDummyData() {

        List<Contact> list = new ArrayList<>();

        Contact contact = new Contact();
        contact.setImgUrl("https://images.pexels.com/photos/2747600/pexels-photo-2747600.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        contact.setFirstName("Sara");
        contact.setLastName("Maric");
        contact.setAddress("Candyland");
        list.add(contact);
        Contact contact1 = new Contact();
        contact1.setImgUrl("https://images.pexels.com/photos/3078091/pexels-photo-3078091.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        contact1.setFirstName("Vladimir");
        contact1.setLastName("Dobrilovic");
        contact1.setAddress("Na kraj sela, zuta kuca, ti ti ti ...");
        list.add(contact1);
        Contact contact2 = new Contact();
        contact2.setImgUrl("https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        contact2.setFirstName("Milan");
        contact2.setLastName("Stevanovic");
        contact2.setAddress("Pecinci");
        list.add(contact2);
        Contact contact3 = new Contact();
        contact3.setImgUrl("https://images.pexels.com/photos/712521/pexels-photo-712521.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        contact3.setFirstName("Lidija");
        contact3.setLastName("Jovanovic");
        contact3.setAddress("Beograd");
        list.add(contact3);
        Contact contact4 = new Contact();
        contact4.setImgUrl("https://images.pexels.com/photos/1239291/pexels-photo-1239291.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        contact4.setFirstName("Gospodjica Mia");
        contact4.setLastName("Not your business!");
        contact4.setAddress("In your dreams");
        list.add(contact4);

        for (int i = 0; i < list.size(); i++) {
            insert(list.get(i));
        }

    }


}