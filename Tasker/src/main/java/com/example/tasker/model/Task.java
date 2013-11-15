package com.example.tasker.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * The model for tasks
 * Created by victorm on 11/15/13.
 */
public class Task {

    private String title;
    private String description;
    private String image;
    private String location;
    private Date dueDate;
    private ArrayList<String> comments;
    private ArrayList<Task> subTasks;
    private TaskSharingSettings shareSettings;
    private TaskCustomizationSettings customizationSettings;

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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
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

    public TaskSharingSettings getShareSettings() {
        return shareSettings;
    }

    public void setShareSettings(TaskSharingSettings shareSettings) {
        this.shareSettings = shareSettings;
    }

    public TaskCustomizationSettings getCustomizationSettings() {
        return customizationSettings;
    }

    public void setCustomizationSettings(TaskCustomizationSettings customizationSettings) {
        this.customizationSettings = customizationSettings;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang.builder.ToStringBuilder(this)
                .append("title", title)
                .append("description", description)
                .append("image", image)
                .append("location", location)
                .append("dueDate", dueDate)
                .append("comments", comments)
                .append("subTasks", subTasks)
                .append("shareSettings", shareSettings)
                .append("customizationSettings", customizationSettings)
                .toString();
    }
}
