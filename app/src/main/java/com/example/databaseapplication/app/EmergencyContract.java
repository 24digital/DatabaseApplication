package com.example.databaseapplication.app;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by marionmorris on 3/17/15.
 */
public class EmergencyContract {

    public EmergencyContract(){}

    public static abstract class EmergencyEntry implements BaseColumns{
        public static final String[] COLUMNS = {"_id","name","phone"};
    public static final String Table_Name = "EMERGENCY_CONTACT";
        public static final String Column_ID = "_id";
        public static final String NAME ="name";
        public static final String phone = "phone";
        public static final String AUTHORITY
                = "com.example.databaseapplication.app";

        public static final String COMPLETE_URI="conntent://"+AUTHORITY+"/"+Table_Name;
        public static final Uri CONTENT_URI = Uri.parse(COMPLETE_URI);

    }
}
