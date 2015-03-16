package com.example.databaseapplication.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marion on 03/15/15.
 */
public class EmergencyContact extends SQLiteOpenHelper {
    private DataSource dataSource;
    private final static String DATABASE_NAME= "Contacts";
    private final static String[] columns={"_id","name", "number"};
    private ArrayList<ContentValues> list = new ArrayList();
    public EmergencyContact(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE EMERGENCY_CONTACTS(_id INTEGER PRIMARY KEY ");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public ArrayList insertContact(String firstName, String lastName, String phoneNumber, String databaseName)
    {
        dataSource = new DataSource();
        dataSource.createContact(firstName,lastName,phoneNumber,this.DATABASE_NAME, this);
      list =  dataSource.getAllContacts(columns,this.DATABASE_NAME);

        return list;
    }

    public void loadContacts()
    {
      List view = dataSource.getAllContacts(columns, DATABASE_NAME);
    }
}
