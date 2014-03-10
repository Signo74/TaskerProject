package com.example.tasker.controller.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tasker.model.CustomizationSettings;
import com.example.tasker.model.SharingSettings;
import com.example.tasker.model.Task;
import com.example.tasker.controller.dbhelper.TasksDBHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by victorm on 11/15/13.
 */
public class TasksDAO {
    private SQLiteDatabase database;
    private TasksDBHelper dbHelper;

    public TasksDAO(Context cntx) {
        dbHelper = new TasksDBHelper(cntx);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /**
     *
     * @param type
     * @param title
     * @param description
     * @param image
     * @param location
     * @param parent
     * @param dueDate
     * @param repeatDate
     * @param repeatDays
     * @param priority
     * @param done
     * @param comments
     * @param shareSettings
     * @param customizationSettings
     * @return
     */
    public Task insertTask(int type, String title, String description, String image, String location, String parent, Date dueDate, Date repeatDate, int repeatDays, int priority, boolean done, ArrayList<String> comments, SharingSettings shareSettings, CustomizationSettings customizationSettings) {
        ContentValues values = new ContentValues();
        Task newTask = new Task();
        values.put(dbHelper.TITLE_COLUMN, title);
        values.put(dbHelper.TYPE_COLUMN, type);
        values.put(dbHelper.DESCRIPTION_COLUMN, description);
        values.put(dbHelper.IMAGE_COLUMN, image);
        values.put(dbHelper.LOCATION_COLUMN, location);
        values.put(dbHelper.PARENT_COLUMN, parent);
        values.put(dbHelper.DUE_DATE_COLUMN, dueDate.getTime());
        values.put(dbHelper.REPEAT_DATE_COLUMN, repeatDate.getTime());
        values.put(dbHelper.REPEAT_DAYS_COLUMN, repeatDays);
        values.put(dbHelper.PRIORITY_COLUMN, priority);
        values.put(dbHelper.DONE_COLUMN, done);
        values.put(dbHelper.COMMENTS_COLUMN, comments.toString());
        values.put(dbHelper.SHARING_SETTINGS_COLUMN, shareSettings.toString());
        values.put(dbHelper.CUSTOMIZATION_SETTINGS_COLUMN, customizationSettings.toString());
        Log.i("[> Values for inserting a new task entry: ", String.valueOf(values.valueSet()));
        database.beginTransaction();
        try {
            long insertID = database.insert(dbHelper.TABLE_NAME, null, values);
            Cursor cursor = database.query(dbHelper.TABLE_NAME, null, dbHelper.ID_COLUMN + " = " + insertID, null, null, null, null);
            cursor.moveToFirst();
            newTask = cursorToTask(cursor);
            cursor.close();
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        return newTask;
    }

    public boolean updateTaskById(Task task) {
        ContentValues values = new ContentValues();
        int updateCount = 0;
        values.put(dbHelper.TITLE_COLUMN, task.getTitle());
        values.put(dbHelper.TYPE_COLUMN, task.getType());
        values.put(dbHelper.DESCRIPTION_COLUMN, task.getDescription());
        values.put(dbHelper.IMAGE_COLUMN, task.getImage());
        values.put(dbHelper.LOCATION_COLUMN, task.getLocation());
        values.put(dbHelper.PARENT_COLUMN, task.getParent());
        values.put(dbHelper.DUE_DATE_COLUMN, task.getDueDate().getTime());
        values.put(dbHelper.REPEAT_DATE_COLUMN, task.getRepeatDate().getTime());
        values.put(dbHelper.REPEAT_DAYS_COLUMN, task.getRepeatDay());
        values.put(dbHelper.PRIORITY_COLUMN, task.getPriority());
        values.put(dbHelper.DONE_COLUMN, task.isDone());
        values.put(dbHelper.COMMENTS_COLUMN, task.getComments().toString());
        values.put(dbHelper.SHARING_SETTINGS_COLUMN, task.getShareSettings().toString());
        values.put(dbHelper.CUSTOMIZATION_SETTINGS_COLUMN, task.getCustomizationSettings().toString());
        Log.i("[> Values for updating existing task entry: ", String.valueOf(values.valueSet()));

        database.beginTransaction();
        try {
            Log.i("[> Updating task entry: ", task.getId().toString());
            updateCount = database.update(dbHelper.TABLE_NAME, values, dbHelper.ID_COLUMN + " = " + task.getId(), null);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }

        return updateCount > 0;
    }

    public int updateListOfTasksById(List<Task> tasks) {
        ListIterator<Task> iterator = tasks.listIterator();
        int updateCount = 0;
        database.beginTransaction();
        try {
            while (iterator.hasNext()) {
                Task task = iterator.next();
                ContentValues values = new ContentValues();
                values.put(dbHelper.TITLE_COLUMN, task.getTitle());
                values.put(dbHelper.TYPE_COLUMN, task.getType());
                values.put(dbHelper.DESCRIPTION_COLUMN, task.getDescription());
                values.put(dbHelper.IMAGE_COLUMN, task.getImage());
                values.put(dbHelper.LOCATION_COLUMN, task.getLocation());
                values.put(dbHelper.PARENT_COLUMN, task.getParent());
                values.put(dbHelper.DUE_DATE_COLUMN, task.getDueDate().getTime());
                values.put(dbHelper.REPEAT_DATE_COLUMN, task.getRepeatDate().getTime());
                values.put(dbHelper.REPEAT_DAYS_COLUMN, task.getRepeatDay());
                values.put(dbHelper.PRIORITY_COLUMN, task.getPriority());
                values.put(dbHelper.DONE_COLUMN, task.isDone());
                values.put(dbHelper.COMMENTS_COLUMN, task.getComments().toString());
                values.put(dbHelper.SHARING_SETTINGS_COLUMN, task.getShareSettings().toString());
                values.put(dbHelper.CUSTOMIZATION_SETTINGS_COLUMN, task.getCustomizationSettings().toString());
                Log.i("[> Values for updating existing task entry: ", String.valueOf(values.valueSet()));
                Log.i("[> Updating task entry: ", task.getId().toString());
                updateCount += database.update(dbHelper.TABLE_NAME, values, dbHelper.ID_COLUMN + " = " + task.getId(), null);
                database.setTransactionSuccessful();
            }
        } finally {
            database.endTransaction();
        }

        return updateCount;
    }

    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<Task>();
        Cursor cursor = database.query(dbHelper.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Task task = cursorToTask(cursor);
            allTasks.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        return allTasks;
    }

    public List<Task> getListOfSubTasks(String parent) {
        List<Task> tasks = new ArrayList<Task>();
        database.beginTransaction();
        try {
            Cursor cursor = database.query(dbHelper.TABLE_NAME, null, "parent = '"+ parent + "'", null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Task task = cursorToTask(cursor);
                tasks.add(task);
                cursor.moveToNext();
            }
            cursor.close();
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        return tasks;
    }

    public void deleteTask(Task task) {
        long id = task.getId();
        database.beginTransaction();
        try {
            database.delete(dbHelper.TABLE_NAME, dbHelper.ID_COLUMN + "=" + id, null);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    public void deleteSubtasks(String parent) {
        database.beginTransaction();
        try {
            database.delete(dbHelper.TABLE_NAME, dbHelper.PARENT_COLUMN + "=" + parent, null);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    public void deleteListOfTasks(List<Task> tasks) {
        ListIterator<Task> iterator = tasks.listIterator();
        while (iterator.hasNext()) {
            deleteTask(iterator.next());
        }
    }

    public void deleteAll(){
        database.beginTransaction();
        try {
            database.delete(dbHelper.TABLE_NAME, null, null);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    private Task cursorToTask(Cursor cursor) {
        Task task = new Task();
        task.setId(cursor.getLong(0));
        task.setTitle(cursor.getString(1));
        //TODO: populate all task fields
        Log.i("[> Creating new task object with title: ", task.getTitle());
        return task;
    }
}