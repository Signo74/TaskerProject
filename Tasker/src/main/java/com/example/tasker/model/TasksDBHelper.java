package com.example.tasker.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by victorm on 11/15/13.
 */
public class TasksDBHelper extends SQLiteOpenHelper {

    public static final String TASK_TABLE_NAME = "tasks";
    public static final String DATABASE_NAME = "tasks.db";
    private static int DATABASE_VERSION = 1;
    public static final int ID_COLUMN = 0;
    public static final int TITLE_COLUMN = 1;
    public static final int DESCRIPTION_COLUMN = 2;
    public static final int IMAGE_COLUMN = 3;
    public static final int LOCATION_COLUMN = 4;
    public static final int DUE_DATE_COLUMN = 5;
    public static final int REPEAT_TYPE_COLUMN = 6;
    public static final int REPEAT_DAYS_COLUMN = 7;
    public static final int REPEAT_DATE_COLUMN = 8;
    public static final int COMMENTS_COLUMN = 9;
    //TODO: insert columns for customizations and sharing

    private static final String DATABASE_CREATE = "create table "
            + TASK_TABLE_NAME + "(" + ID_COLUMN
            + " integer primary key autoincrement, " + TITLE_COLUMN
            + " text not null);"

    public TasksDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }
    private void createTable(SQLiteDatabase sqLiteDatabase) {
        //TODO: initialize the DB if it is missing.
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldv, int newv) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE_NAME + ";");
        createTable(sqLiteDatabase);
    }
}
