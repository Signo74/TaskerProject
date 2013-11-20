package com.example.tasker.controller.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tasker.model.Task;
import com.example.tasker.model.TasksDBHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victorm on 11/15/13.
 */
public class TasksDAO {
    private SQLiteDatabase database;
    private TasksDBHelper dbHelper;
    private String[] allColumns = { TasksDBHelper.ID_COLUMN, TasksDBHelper.TITLE_COLUMN};

    public TasksDAO(Context cntx) {
        dbHelper = new TasksDBHelper(cntx);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    //TODO: return Task
    public Task createTask(String title){
        ContentValues values = new ContentValues();
        values.put(dbHelper.TITLE_COLUMN, title);
        long insertID = database.insert(dbHelper.TASK_TABLE_NAME, null, values);
        Cursor cursor = database.query(dbHelper.TASK_TABLE_NAME, allColumns, dbHelper.ID_COLUMN + " = " + insertID, null, null, null, null);
        cursor.moveToFirst();
        Task newTask = cursorToTask(cursor);
        cursor.close();
        return newTask;
    }

    public void deleteTask(Task task) {
        long id = task.getId();
        database.delete(dbHelper.TASK_TABLE_NAME, dbHelper.ID_COLUMN + "=" + id, null);
    }

    /*
     * TODO: create functions for the following common tasks:
     * 1) get all tasks - DONE
     * 2) get a set of selected tasks
     * 3) delete all tasks
     * 4) delete a set of selected tasks
     * 5) re-order tasks
     * 6) modify a single task
     */

    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<Task>();
        Cursor cursor = database.query(dbHelper.TASK_TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Task task = cursorToTask(cursor);
            allTasks.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        return allTasks;
    }

    private Task cursorToTask(Cursor cursor) {
        Task task = new Task();
        task.setId(cursor.getLong(0));
        task.setTitle(cursor.getString(1));
        return task;
    }
}