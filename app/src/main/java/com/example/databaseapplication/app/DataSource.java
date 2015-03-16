package com.example.databaseapplication.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Marion on 03/15/15.
 */
public class DataSource {
private SQLiteDatabase database ;
private  ContentValues values;
    public void createContact(String firstName, String lastName, String phoneNumber, String databaseName, SQLiteOpenHelper helper)
                {
                    database = helper.getWritableDatabase();
     values = new ContentValues();
values.put("firstName",firstName);
        values.put("lastName",lastName);
        values.put("phoneNumber", phoneNumber);
        database.insert(databaseName,null,values);
    }
public void setDB(SQLiteDatabase database)
{
    database = database;
}
    public void close(){
        database.close();
    }
    public ContentValues getValues()
    {
        return values;
    }

    /**
     *
     * @param columns Column names
     * @param databaseTable Database Table
     * @return
     */
    public ArrayList getAllContacts(String[] columns, String databaseTable)
    {
        ArrayList contactList = new ArrayList();
        Cursor cursor = database.query(databaseTable, columns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            contactList.add(cursor.getInt(0));
            cursor.moveToNext();

        }
        cursor.close();
        return contactList;
    }
}
