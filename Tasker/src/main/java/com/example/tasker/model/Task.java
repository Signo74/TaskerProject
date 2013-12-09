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
    private Date dueDate;
    private Date repeatDate;
    private String repeatDay;
    private int priority;
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

    public String getRepeatDay() {
        return repeatDay;
    }

    public void setRepeatDay(String repeatDay) {
        this.repeatDay = repeatDay;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append(", dueDate=").append(dueDate);
        sb.append(", repeatDate=").append(repeatDate);
        sb.append(", repeatDay='").append(repeatDay).append('\'');
        sb.append(", priority=").append(priority);
        sb.append(", comments=").append(comments);
        sb.append(", subTasks=").append(subTasks);
        sb.append(", shareSettings=").append(shareSettings);
        sb.append(", customizationSettings=").append(customizationSettings);
        sb.append('}');
        return sb.toString();
    }
}
