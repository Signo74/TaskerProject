package com.example.tasker.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * The model for tasks
 * Created by victorm on 11/15/13.
 */
public class Task {
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append(", dueDate=").append(dueDate);
        sb.append(", comments=").append(comments);
        sb.append(", subTasks=").append(subTasks);
        sb.append(", shareSettings=").append(shareSettings);
        sb.append(", customizationSettings=").append(customizationSettings);
        sb.append('}');
        return sb.toString();
    }
}
