package com.example.databaseapplication.app;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.support.v4.widget.SimpleCursorAdapter;

import java.util.ArrayList;


public class MainActivity extends ListActivity {
    //List view to hold entities
    private static final String[] COLUMNS =
            {"_id", "name", "phone_num" };
    private static final int[] VIEWS =
            { R.id.name, R.id.phone};

   private ListView listView;
    private EmergencyContact contact;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       listView = getListView();

      contact = new EmergencyContact(this);


        try
        {
            Cursor cursor = contact.getContactCursor();
            SimpleCursorAdapter adapter =
                    new SimpleCursorAdapter(this,
                            R.layout.emergency_contact, cursor,
                            COLUMNS, VIEWS, 0);
            setListAdapter(adapter);
        }
        catch (Exception ex)
        { ex.printStackTrace();}
    }



        //   SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.id.list_item,cursor,COLUMNS,VIEWS ,0);
       // setListAdapter(cursorAdapter);








    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    listView = (ListView)    savedInstanceState.get("list");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
