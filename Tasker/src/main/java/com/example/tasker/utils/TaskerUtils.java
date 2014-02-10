package com.example.tasker.utils;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.tasker.R;
import com.example.tasker.controller.dao.TasksDAO;
import com.example.tasker.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by victorm on 11/6/13.
 */
public class TaskerUtils {

    public void TaskerUtils() {
    }

    public void quickAddTask(TasksDAO dao, String title){
        Log.i("[> Quick adding task: ", title);
        Task task = dao.insertTask(title, 0, "", "", "", "Today", "", "", false);
    }

    public void deleteAllTasks(TasksDAO dao){
        Log.d("[> Deleting all task from database: ", "");
        dao.deleteAll();
    }

    public List<Task> getAllTasks(TasksDAO dao){
        List<Task> childItemTitles = dao.getAllTasks();
        Log.i("[> All tasks in DB: ", childItemTitles.toString());

        return childItemTitles;
    }

}
