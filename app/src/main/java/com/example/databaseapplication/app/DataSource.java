package com.example.databaseapplication.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Marion on 03/15/15.
 */
public class DataSource {
private SQLiteDatabase database ;

    public void createContact(String firstName, String lastName, String phoneNumber, String databaseName, SQLiteOpenHelper helper)
                {
                    database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
values.put("firstName",firstName);
        values.put("lastName",lastName);
        values.put("phoneNumber", phoneNumber);
        database.insert(databaseName,null,values);
    }

    public void close(){
        database.close();
    }

    public List getAllContacts(String[] columns, String databaseTable)
    {
        List contactList = new LinkedList();
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
