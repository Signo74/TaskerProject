package com.example.tasker.controller.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tasker.model.Comment;
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

    public Task insertTask(int type, String title, String content, Long parent, String image, String location, Date repeatDate, int repeatDays, Date dueDate, int priority, boolean done, List<Comment> comments) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.TYPE_COLUMN, type);
        values.put(dbHelper.TITLE_COLUMN, title);
        values.put(dbHelper.DESCRIPTION_COLUMN, content);
        values.put(dbHelper.PARENT_COLUMN, parent);
        values.put(dbHelper.IMAGE_COLUMN, image);
        values.put(dbHelper.LOCATION_COLUMN, location);
        values.put(dbHelper.REPEAT_DATE_COLUMN, repeatDate.getTime());
        values.put(dbHelper.REPEAT_DAYS_COLUMN, repeatDays);
        values.put(dbHelper.DUE_DATE_COLUMN, dueDate.getTime());
        values.put(dbHelper.PRIORITY_COLUMN, priority);
        values.put(dbHelper.DONE_COLUMN, done);
        values.put(dbHelper.COMMENTS_COLUMN, comments.toString());
        Log.i("[> Values for inserting a new task entry: ", String.valueOf(values.valueSet()));
        database.beginTransaction();

        try {
            long insertID = database.insert(dbHelper.TABLE_NAME, null, values);
            Cursor cursor = database.query(dbHelper.TABLE_NAME, null, dbHelper.ID_COLUMN + " = " + insertID, null, null, null, null);
            cursor.moveToFirst();
            cursor.close();
            database.setTransactionSuccessful();

            Task newTask = new Task(title, content, parent, image, location, repeatDate, repeatDays, dueDate, priority, done, comments);
            newTask.setId(insertID);

            return newTask;
        } finally {
            database.endTransaction();
        }
    }

    public boolean updateTaskById(Task task) {
        ContentValues values = new ContentValues();
        int updateCount = 0;
        values.put(dbHelper.TITLE_COLUMN, task.getTitle());
        values.put(dbHelper.TYPE_COLUMN, task.getType());
        values.put(dbHelper.DESCRIPTION_COLUMN, task.getContent());
        values.put(dbHelper.IMAGE_COLUMN, task.getImage());
        values.put(dbHelper.LOCATION_COLUMN, task.getLocation());
        values.put(dbHelper.PARENT_COLUMN, task.getParent());
        values.put(dbHelper.DUE_DATE_COLUMN, task.getDueDate().getTime());
        values.put(dbHelper.REPEAT_DATE_COLUMN, task.getRepeatDate().getTime());
        values.put(dbHelper.REPEAT_DAYS_COLUMN, task.getRepeatDay());
        values.put(dbHelper.PRIORITY_COLUMN, task.getPriority());
        values.put(dbHelper.DONE_COLUMN, task.isDone());
        values.put(dbHelper.COMMENTS_COLUMN, task.getComments().toString());
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
                values.put(dbHelper.IMAGE_COLUMN, task.getImage());
                values.put(dbHelper.LOCATION_COLUMN, task.getLocation());
                values.put(dbHelper.PARENT_COLUMN, task.getParent());
                values.put(dbHelper.DUE_DATE_COLUMN, task.getDueDate().getTime());
                values.put(dbHelper.REPEAT_DATE_COLUMN, task.getRepeatDate().getTime());
                values.put(dbHelper.REPEAT_DAYS_COLUMN, task.getRepeatDay());
                values.put(dbHelper.PRIORITY_COLUMN, task.getPriority());
                values.put(dbHelper.DONE_COLUMN, task.isDone());
                values.put(dbHelper.COMMENTS_COLUMN, task.getComments().toString());
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

        String title = cursor.getString(0);
        String content = cursor.getString(3);
        Long parent = cursor.getLong(4);
        String image = cursor.getString(5);
        String location = cursor.getString(6);
        Date repeatDate = new Date(cursor.getLong(7));
        Integer repeatDays = cursor.getInt(8);
        Date dueDate = new Date(cursor.getLong(9));
        Integer priority = cursor.getInt(10);
        Boolean done = false;
        if (cursor.getInt(11) == 1) {
            done = true;
        }
        String[] tempComments = cursor.getString(12).split("Comment");
        List<Comment> comments = new ArrayList<Comment>();
        for (String comment : tempComments) {
            comment = comment.replace("{", "");
            comment = comment.replace("}", "");
            comment = comment.replace("'", "");
            String[] params = comment.split(",");
            String author = params[0].substring(params[0].indexOf("="), params[0].length());
            String date = params[1].substring(params[1].indexOf("="), params[1].length());
            String commentTitle = params[2].substring(params[2].indexOf("="), params[2].length());
            String commentContent = params[3].substring(params[3].indexOf("="), params[3].length());
            Long commentParent = Long.getLong(params[4].substring(params[4].indexOf("="), params[4].length()));

            comments.add(new Comment(commentTitle, commentContent, commentParent, author, date));
        }

        Task newTask = new Task(title, content, parent, image, location, repeatDate, repeatDays, dueDate, priority, done, comments);
        newTask.setId(Long.valueOf(cursor.getPosition()));

        //TODO: populate all task fields
        Log.i("[> Creating new task object with title: ", newTask.getTitle());
        return newTask;
    }
}