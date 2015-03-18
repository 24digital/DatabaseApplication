package com.example.databaseapplication.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marion on 03/15/15.
 */
public class EmergencyContact extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "Contacts";

    private final static int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + EmergencyContract.EmergencyEntry.Table_Name + " (" +
            EmergencyContract.EmergencyEntry.Column_ID + " INTEGER PRIMARY KEY," + EmergencyContract.EmergencyEntry.NAME
            + " TEXT NOT NULL,"
            + EmergencyContract.EmergencyEntry.phone + " INTEGER NOT NULL )";

    private static final String DELETE_ENTRIES = "DROP TABLE  IF EXISTS " + EmergencyContract.EmergencyEntry.Table_Name;


    public EmergencyContact(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        initialize(db);

    }

    private void initialize(SQLiteDatabase db) {


        ContentValues values = new ContentValues();
        values.put(EmergencyContract.EmergencyEntry.NAME, "marion");
        values.put(EmergencyContract.EmergencyEntry.phone, 854345223);


        db.insert(EmergencyContract.EmergencyEntry.Table_Name, null, values);

        values = new ContentValues();
        values.put(EmergencyContract.EmergencyEntry.NAME, "Mike");
        values.put(EmergencyContract.EmergencyEntry.phone, 554342223);

        db.insert(EmergencyContract.EmergencyEntry.Table_Name, null, values);
      
        Cursor cursor = db.query(EmergencyContract.EmergencyEntry.Table_Name,
                EmergencyContract.EmergencyEntry.COLUMNS, null, null, null, null, "name");
    }


    public Cursor getContactCursor() {
        String query = "select * from "
                + EmergencyContract.EmergencyEntry.Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(query, null);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /**  public void loadContacts()
     {
     List view = dataSource.getAllContacts(columns, DATABASE_TABLE);
     }
     */
}