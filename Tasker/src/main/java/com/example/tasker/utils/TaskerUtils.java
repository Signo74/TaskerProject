package com.example.tasker.utils;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.tasker.R;
import com.example.tasker.controller.dao.TasksDAO;
import com.example.tasker.model.CustomizationSettings;
import com.example.tasker.model.SharingSettings;
import com.example.tasker.model.Task;
import com.example.tasker.model.base.EntryTypes;

import java.util.ArrayList;
import java.util.Date;
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
        Task task = dao.insertTask(EntryTypes.TASK.getEntryId(), title, "", 0l, "", "",  new Date(), 7, null , 0, false, null);
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
