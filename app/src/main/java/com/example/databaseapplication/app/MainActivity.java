package com.example.databaseapplication.app;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Comment;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.TooManyListenersException;

public class MainActivity extends ListActivity

{
    private ListView view;
    private MyDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = getListView();




        view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id)
            {
                ViewGroup vg = (ViewGroup) view;
                TextView nameView = (TextView) vg.getChildAt(0);
                String name = nameView.getText().toString();
                LinearLayout linearLayout = (LinearLayout) vg.getChildAt(1);
                TextView phoneView = (TextView) linearLayout.getChildAt(0);
                String phoneNum = phoneView.getText().toString();
                TextView phoneTypeView = (TextView) linearLayout.getChildAt(1);
                String phoneType = phoneTypeView.getText().toString();

                String message
                        = "position = "  + position + ",\n"
                        + "id = "        +  id      + ",\n"
                        + "name = "      + name     + ",\n"
                        + "phoneNum = "  + phoneNum + ",\n"
                        + "phoneType = " + phoneType;
                Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG);
                toast.show();

                return true;
            }
        });

        dao = new MyDAO(this);
        dao.createContact("John", 84135);
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



            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                   contact = (Contact) getListAdapter().getItem(0);
                   dao.deleteComment(contact);
                   adapter.remove(contact);
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