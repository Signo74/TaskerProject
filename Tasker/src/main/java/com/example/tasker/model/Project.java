package com.example.tasker.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by victorm on 11/25/13.
 */
public class Project {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String location;
    private Date dueDate;
    private ArrayList<String> comments;
    private ArrayList<Task> subTasks;
    private SharingSettings shareSettings;
    private CustomizationSettings customizationSettings;
}
