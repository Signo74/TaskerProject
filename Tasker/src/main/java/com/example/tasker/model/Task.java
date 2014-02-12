package com.example.tasker.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * The model for tasks
 * Created by victorm on 11/15/13.
 */
public class Task {
    private Long id;
    private int type; //describes if this is a Task, To-do, project item, to-do list, project
    private String title;
    private String description;
    private String image;
    private String location;
    private String parent;
    private Date dueDate;
    private Date repeatDate;
    private int repeatDay;
    private int priority;
    private Boolean done;
    private ArrayList<String> comments;
    private ArrayList<Task> subTasks;
    private SharingSettings shareSettings;
    private CustomizationSettings customizationSettings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getRepeatDate() {
        return repeatDate;
    }

    public void setRepeatDate(Date repeatDate) {
        this.repeatDate = repeatDate;
    }

    public int getRepeatDay() {
        return repeatDay;
    }

    public void setRepeatDay(int repeatDay) {
        this.repeatDay = repeatDay;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public ArrayList<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<Task> subTasks) {
        this.subTasks = subTasks;
    }

    public SharingSettings getShareSettings() {
        return shareSettings;
    }

    public void setShareSettings(SharingSettings shareSettings) {
        this.shareSettings = shareSettings;
    }

    public CustomizationSettings getCustomizationSettings() {
        return customizationSettings;
    }

    public void setCustomizationSettings(CustomizationSettings customizationSettings) {
        this.customizationSettings = customizationSettings;
    }

}
