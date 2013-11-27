package com.example.tasker.controller.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by victorm on 11/26/13.
 */
public class DrawerItemsDBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "draweritems";
    public static final String DATABASE_NAME = "draweritems.db";
    private static int DATABASE_VERSION = 1;
    public static final String ID_COLUMN = "id";
    public static final String TITLE_COLUMN = "title";
    //TODO: insert columns for customizations and sharing
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "(" + ID_COLUMN
            + " integer primary key autoincrement, " + TITLE_COLUMN
            + " text not null);";

    public DrawerItemsDBHelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    private void createTable(SQLiteDatabase sqLiteDatabase) {
        //TODO: initialize the DB if it is missing.
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldDB, int newDB) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        createTable(sqLiteDatabase);
    }
}
