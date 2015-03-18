package com.example.databaseapplication.app;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Created by Marion on 03/15/15.
 */
public class EmergencyContactHelper extends SQLiteOpenHelper {
    public static final String[] COLUMNS = {"_id","name","phone"};
    public static final String Table_Name = "EMERGENCY_CONTACT";
    public static final String Column_ID = "_id";
    public static final String NAME ="name";
    public static final String phone = "phone";
    public static final String AUTHORITY
            = "com.example.databaseapplication.app";
    public final static String DATABASE_NAME = "Contacts";

    public final static int DATABASE_VERSION = 1;

    public static final String COMPLETE_URI="content://"+AUTHORITY+"/"+Table_Name;
    public static final Uri CONTENT_URI = Uri.parse(COMPLETE_URI);


    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + Table_Name + " (" +
            Column_ID + " INTEGER PRIMARY KEY," + NAME
            + " TEXT NOT NULL,"
            + phone+ " INTEGER NOT NULL )";




    public EmergencyContactHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);


    }




    public Cursor getContactCursor() {
        String query = "select * from "
                + Table_Name;
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