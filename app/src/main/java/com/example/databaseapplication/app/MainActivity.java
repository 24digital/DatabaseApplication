package com.example.databaseapplication.app;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import org.w3c.dom.Comment;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class MainActivity extends ListActivity

{
    private MyDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new MyDAO(this);
        try {
            dao.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Contact> values = dao.getAllContacts();

        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Contact> adapter = (ArrayAdapter<Contact>) getListAdapter();
        Contact contact = null;
        switch (view.getId()) {
            case R.id.add:
                String[] co = new String[] { "Marion", "84891" };

                // save the new comment to the database
               contact  = dao.createContact(co[0],Integer.parseInt(co[1]));
                adapter.add(contact);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                   contact = (Contact) getListAdapter().getItem(0);
                  //  dao.deleteComment(comment);
                  //  adapter.remove(comment);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        try {
            dao.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
      dao.close();
        super.onPause();
    }
}