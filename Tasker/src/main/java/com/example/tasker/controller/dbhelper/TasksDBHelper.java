package com.example.tasker.controller.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Date;

/**
 * Created by victorm on 11/15/13.
 */
public class TasksDBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "tasks";
    public static final String DATABASE_NAME = "tasks.db";
    private static int DATABASE_VERSION = 1;
    public static final String ID_COLUMN = "_id";
    public static final String TITLE_COLUMN = "title";
   /* public static final String TYPE_COLUMN = "type";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String IMAGE_COLUMN = "image";
    public static final String LOCATION_COLUMN = "location";
    public static final String DUE_DATE_COLUMN = "duedate";
    public static final String REPEAT_DATE_COLUMN = "repeatdate";
    public static final String REPEAT_DAYS_COLUMN = "repeatdays";
    public static final String COMMENTS_COLUMN = "comments"; */

    //TODO: Modify initial creation string to insert all necessary fields.
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "(" + ID_COLUMN
            + " integer primary key autoincrement, " + TITLE_COLUMN
            + " text not null);";

    public TasksDBHelper(Context context) {
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
        onCreate(sqLiteDatabase);
    }
}
