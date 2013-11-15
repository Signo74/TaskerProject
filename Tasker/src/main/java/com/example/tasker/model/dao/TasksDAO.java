package com.example.tasker.model.dao;

import android.database.sqlite.SQLiteDatabase;

import com.example.tasker.model.TasksDBHelper;

/**
 * Created by victorm on 11/15/13.
 */
public class TasksDAO {
    private SQLiteDatabase database;
    private TasksDBHelper dbHelper;
    private String[] allColumns = { TasksDBHelper.ID_COLUMN, TasksDBHelper.TITLE_COLUMN };
}
