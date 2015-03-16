package com.example.databaseapplication.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Marion on 03/15/15.
 */
public class EmergencyContact extends SQLiteOpenHelper {
    private DataSource dataSource;
    private final static String DATABASE_NAME= "Contacts";
    private final static String[] columns={"_id","name", "number"};
    public EmergencyContact(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE EMERGENCY_CONTACTS(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, number INTEGER NOT NULLl");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertContact(String firstName, String lastName, String phoneNumber, String databaseName)
    {
        dataSource = new DataSource();
        dataSource.createContact(firstName,lastName,phoneNumber,this.DATABASE_NAME, this);
    }

    public void loadContacts()
    {
      List view = dataSource.getAllContacts(columns, DATABASE_NAME);
    }
}
