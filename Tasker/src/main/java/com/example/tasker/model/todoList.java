package com.example.tasker.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by victorm on 11/22/13.
 */
public class TodoList {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String location;
    private Date dueDate;
    private Date repeatDate;
    private String repeatDay;
    private ArrayList<String> comments;
    private ArrayList<Task> subTasks;
    private TaskSharingSettings shareSettings;
    private TaskCustomizationSettings customizationSettings;
}
