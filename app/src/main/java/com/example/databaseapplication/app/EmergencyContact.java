package com.example.databaseapplication.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * Created by Marion on 03/15/15.
 */
public class EmergencyContact extends SQLiteOpenHelper {
    private DataSource dataSource;
    private final static String DATABASE_NAME= "Contacts";
    private final static String DATABASE_TABLE="EMERGENCY_CONTACTS";
    private final static String[] columns={"_id","name", "number"};
    private final static int DATABASE_VERSION=1;
    private ArrayList<ContentValues> list = new ArrayList();

public EmergencyContact (Context context)
{
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
}
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DATABASE_TABLE +"(_id INTEGER PRIMARY KEY autoincrement, name text not null, number INTEGER not null)");
    insertContact(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public ArrayList insertContact(SQLiteDatabase db)
    {

        ContentValues values = new ContentValues();
        values.put("name","Marion");
        values.put("number","843469878");

        db.insert(DATABASE_TABLE,null,values);
String query = "Select * from"+DATABASE_TABLE;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while(cursor.moveToNext())
        {
           int id = cursor.getInt(0);
    String name = cursor.getString(1);
            String number = cursor.getString(2);

            String message = "ID:"+id+" Name:"+name+" Number"+number;
            Toast toast = Toast.makeText(this,message,Toast.LENGTH_LONG);
            toast.show();
            cursor.moveToNext();
        }


        cursor.close();
      list =  dataSource.getAllContacts(columns,this.DATABASE_TABLE);

        return list;
    }

    public void loadContacts()
    {
      List view = dataSource.getAllContacts(columns, DATABASE_TABLE);
    }
}