package com.example.databaseapplication.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marion on 3/18/2015.
 */
public class MyDAO implements Serializable {
    private SQLiteDatabase database;
    private EmergencyContactHelper helper;
    private String[] allColumns= {EmergencyContactHelper.Column_ID, EmergencyContactHelper.NAME, EmergencyContactHelper.phone};

    public MyDAO(Context context)
    {
        helper = new EmergencyContactHelper(context);
    }

    public void open()throws SQLException{
        database = helper.getWritableDatabase();
    }
    public void close()
    {
        helper.close();
    }

    public Contact createContact(String name, long number)
    {
        ContentValues values = new ContentValues();
        values.put(EmergencyContactHelper.NAME,name);
        values.put(EmergencyContactHelper.phone,number);

        long insertID = database.insert(EmergencyContactHelper.Table_Name, null, values);

        Cursor cursor = database.query(EmergencyContactHelper.Table_Name,
                allColumns,null, null,
                null,null,null);
cursor.moveToFirst();
        Contact contact = new Contact();
 contact.setName(cursor.getString(0));
        contact.setNumber(cursor.getLong(1));
        return contact;
    }



    public List<Contact> getAllContacts()
    {
        List<Contact> contacts = new ArrayList<Contact>();

        Cursor cursor = database.query(EmergencyContactHelper.Table_Name, allColumns,null,null,null,null,null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast())
        {
            Contact contact = new Contact();
                    contact.setName(cursor.getString(1));
            contact.setNumber(cursor.getLong(2));
            contact.setName(cursor.getString(1));
            contact.setNumber(cursor.getLong(2));
            contacts.add(contact);
            cursor.moveToNext();
        }
        cursor.close();
        return contacts;
    }

    public void deleteComment(Contact contact) {
        long id = contact.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(EmergencyContactHelper.Table_Name, EmergencyContactHelper.Column_ID
                + " = " + id, null);
    }
}
