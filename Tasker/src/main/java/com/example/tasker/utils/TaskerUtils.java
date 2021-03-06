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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by victorm on 11/6/13.
 */
public class TaskerUtils {

    private final static Calendar dueDateCalendar = Calendar.getInstance();

    public void TaskerUtils() {
    }

    public Task quickAddTask(TasksDAO dao, String title){
        Log.d("TaskerUtils.quickAddTask","Adding task with title: " + title);
        Task task = dao.insertTask(EntryTypes.TASK.getEntryId(), title, "", "Today", "", "",  null, 7, dueDateCalendar, 0, false, null);
        return task;
    }

    public void deleteAllTasks(TasksDAO dao){
        Log.d("TaskerUtils.deleteAllTasks","Deleting all tasks from Data base");
        dao.deleteAll();
    }

    public List<Task> getAllTasks(TasksDAO dao){
        List<Task> childItemTitles = dao.getAllTasks();
        Log.d("TaskerUtils.getAllTasks","All tasks in DB: " + childItemTitles);

        return childItemTitles;
    }

}
