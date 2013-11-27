package com.example.tasker.controller.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tasker.controller.dbhelper.DrawerItemsDBHelper;
import com.example.tasker.controller.dbhelper.TasksDBHelper;
import com.example.tasker.model.Task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victorm on 11/26/13.
 */
public class DrawerItemsDAO {
    private SQLiteDatabase database;
    private DrawerItemsDBHelper dbHelper;
    private String[] allColumns = {TasksDBHelper.ID_COLUMN, TasksDBHelper.TITLE_COLUMN};

    public DrawerItemsDAO(Context cntx) {
        dbHelper = new DrawerItemsDBHelper(cntx);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //TODO: return Task
    public Task insertDrawerItem(String title) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.TITLE_COLUMN, title);
        long insertID = database.insert(dbHelper.TABLE_NAME, null, values);
        Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, dbHelper.ID_COLUMN + " = " + insertID, null, null, null, null);
        cursor.moveToFirst();
        Task newTask = cursorToDrawerItem(cursor);
        cursor.close();
        return newTask;
    }

    public void deleteDrawerItem(Task task) {
        long id = task.getId();
        database.delete(dbHelper.TABLE_NAME, dbHelper.ID_COLUMN + "=" + id, null);
    }

    public List<Task> getAllDrawerItems() {
        List<Task> allTasks = new ArrayList<Task>();
        Cursor cursor = database.query(dbHelper.TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Task task = cursorToDrawerItem(cursor);
            allTasks.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        return allTasks;
    }

    private Task cursorToDrawerItem(Cursor cursor) {
        Task task = new Task();
        task.setId(cursor.getLong(0));
        task.setTitle(cursor.getString(1));
        return task;
    }
}
