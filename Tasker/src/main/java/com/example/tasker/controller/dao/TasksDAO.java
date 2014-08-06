package com.example.tasker.controller.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tasker.model.Comment;
import com.example.tasker.model.Task;
import com.example.tasker.controller.dbhelper.TasksDBHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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

    public Task insertTask(int type, String title, String content, String parent, String image, String location, Date repeatDate, int repeatDays, Calendar dueDate, int priority, boolean done, List<Comment> comments) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.TITLE_COLUMN, title);
        values.put(dbHelper.TYPE_COLUMN, type);
        values.put(dbHelper.DESCRIPTION_COLUMN, content);
        values.put(dbHelper.PARENT_COLUMN, parent);
        values.put(dbHelper.IMAGE_COLUMN, image);
        values.put(dbHelper.LOCATION_COLUMN, location);
        values.put(dbHelper.DUE_DAY_COLUMN, dueDate.DAY_OF_MONTH);
        values.put(dbHelper.DUE_MONTH_COLUMN, dueDate.MONTH);
        values.put(dbHelper.DUE_YEAR_COLUMN, dueDate.YEAR);
        if (repeatDate != null) {
            values.put(dbHelper.REPEAT_DATE_COLUMN, repeatDate.getTime());
        } else {
            values.put(dbHelper.REPEAT_DATE_COLUMN, 0);
        }
        values.put(dbHelper.REPEAT_DAYS_COLUMN, repeatDays);
        values.put(dbHelper.DONE_COLUMN, done);
        values.put(dbHelper.PRIORITY_COLUMN, priority);
        if (comments != null ) {
            values.put(dbHelper.COMMENTS_COLUMN, comments.toString());
        } else {
            values.put(dbHelper.COMMENTS_COLUMN, "");
        }
        Log.d("TasksDAO.insertTask", "These values " + String.valueOf(values.valueSet()) + " will be inserted in the new entry.");

        try {
            database.beginTransaction();
            long insertID = database.insert(dbHelper.TABLE_NAME, null, values);
            Cursor cursor = database.query(dbHelper.TABLE_NAME, null, dbHelper.ID_COLUMN + " = " + insertID, null, null, null, null);
            cursor.moveToFirst();
            cursor.close();
            database.setTransactionSuccessful();

            Task newTask = new Task(title, content, parent, image, location, repeatDate, repeatDays, dueDate, priority, done, comments);
            newTask.setId(insertID);

            return newTask;
        } catch (Exception ex) {
            Log.e("TasksDAO.insertTask", "Error: " + ex + " was thrown while inserting task in DB.");
            return null;
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
        values.put(dbHelper.IMAGE_COLUMN, task.getImageUrl());
        values.put(dbHelper.LOCATION_COLUMN, task.getLocation());
        values.put(dbHelper.PARENT_COLUMN, task.getCategory());
        values.put(dbHelper.DUE_DAY_COLUMN, task.getDueDay());
        values.put(dbHelper.DUE_MONTH_COLUMN, task.getDueMonth());
        values.put(dbHelper.DUE_YEAR_COLUMN, task.getDueYear());
        values.put(dbHelper.REPEAT_DATE_COLUMN, task.getRepeatDate().getTime());
        values.put(dbHelper.REPEAT_DAYS_COLUMN, task.getRepeatDay());
        values.put(dbHelper.PRIORITY_COLUMN, task.getPriority());
        values.put(dbHelper.DONE_COLUMN, task.isDone());
        values.put(dbHelper.COMMENTS_COLUMN, task.getComments().toString());
        Log.d("TasksDAO.updateTaskById", "Values " + values.valueSet() + " for updating existing entry with Id: " + task.getId());
        database.beginTransaction();
        try {
            Log.d("TasksDAO.updateTaskById", "Updating task entry with Id "+ task.getId());
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
                values.put(dbHelper.IMAGE_COLUMN, task.getImageUrl());
                values.put(dbHelper.LOCATION_COLUMN, task.getLocation());
                values.put(dbHelper.PARENT_COLUMN, task.getCategory());
                values.put(dbHelper.DUE_DAY_COLUMN, task.getDueDay());
                values.put(dbHelper.DUE_MONTH_COLUMN, task.getDueMonth());
                values.put(dbHelper.DUE_YEAR_COLUMN, task.getDueYear());
                values.put(dbHelper.REPEAT_DATE_COLUMN, task.getRepeatDate().getTime());
                values.put(dbHelper.REPEAT_DAYS_COLUMN, task.getRepeatDay());
                values.put(dbHelper.PRIORITY_COLUMN, task.getPriority());
                values.put(dbHelper.DONE_COLUMN, task.isDone());
                values.put(dbHelper.COMMENTS_COLUMN, task.getComments().toString());
                Log.d("TasksDAO.updateListOfTasksById", "Updating task entry with id " + task.getId() +" with values {}" + values.valueSet());
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
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Task task = cursorToTask(cursor);
                allTasks.add(task);
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            Log.e("Tasls.DAO.getAllTasks", "Error " + ex +" was thrown while processing all tasks.");
            return null;
        } finally {
            cursor.close();
        }
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
        try {
            String title = cursor.getString(1);
            //Not needed
            Integer type = cursor.getInt(2);
            String content = cursor.getString(3);
            String parent = cursor.getString(4);
            String image = cursor.getString(5);
            String location = cursor.getString(6);
            int dueDay = cursor.getInt(7);
            int dueMonth = cursor.getInt(8);
            int dueYear = cursor.getInt(9);
            Date repeatDate = new Date(cursor.getLong(10));
            Integer repeatDays = cursor.getInt(11);;
            Boolean done = false;
            if (cursor.getInt(12) == 1) {
                done = true;
            }
            Integer priority = cursor.getInt(13);

            List<Comment> comments = new ArrayList<Comment>();
            if (cursor.getString(12).indexOf("Comment") > -1) {
                String[] tempComments = cursor.getString(14).split("Comment");
                for (String comment : tempComments) {
                    comment = comment.replace("{", "");
                    comment = comment.replace("}", "");
                    comment = comment.replace("'", "");
                    String[] params = comment.split(",");
                    String author = params[0].substring(params[0].indexOf("="), params[0].length());
                    String date = params[1].substring(params[1].indexOf("="), params[1].length());
                    String commentTitle = params[2].substring(params[2].indexOf("="), params[2].length());
                    String commentContent = params[3].substring(params[3].indexOf("="), params[3].length());
                    String commentParent = params[4].substring(params[4].indexOf("="), params[4].length());

                    comments.add(new Comment(commentTitle, commentContent, commentParent, author, date));
                }
            }

            Task newTask = new Task(title, content, parent, image, location, repeatDate, repeatDays, dueDay, dueMonth, dueYear, priority, done, comments);
            newTask.setId(Long.valueOf(cursor.getPosition()));

            Log.d("TasksDAO.cursorToTask","Creating new task with id " + newTask.getId() + ". Task: " + newTask);
            return newTask;
        } catch (Exception ex) {
            Log.e("TasksDAO.cursorToTask", "Exception " + ex +" thrown while creating new task");
            return null;
        }
    }
}