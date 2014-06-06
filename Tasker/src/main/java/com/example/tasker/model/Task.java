package com.example.tasker.model;

import com.example.tasker.model.base.Entry;
import com.example.tasker.model.base.EntryTypes;

import java.util.Date;
import java.util.List;

/**
 * The model for tasks
 * Created by victorm on 11/15/13.
 */
public class Task
    extends Entry {

    private String imageUrl;
    private String location;
    private Date repeatDate;
    private Integer repeatDay;
    private Date dueDate;
    private Integer priority;
    private boolean done;
    private List<Comment> comments;


    Task(String title, String content, Long parent) {
        super(EntryTypes.TASK.getEntryId(), title, content, parent);
    }



    public Task(String title, String content, Long parent, String imageUrl, String location, Date repeatDate, Integer repeatDay, Date dueDate, Integer priority, boolean done, List<Comment> comments) {
        this(title, content, parent);
        this.imageUrl = imageUrl;
        this.location = location;
        this.repeatDate = repeatDate;
        this.repeatDay = repeatDay;
        this.dueDate = dueDate;
        this.priority = priority;
        this.done = done;
        this.comments = comments;
    }

    public String getImage() {
        return imageUrl;
    }

    public void setImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getRepeatDate() {
        return repeatDate;
    }

    public void setRepeatDate(Date repeatDate) {
        this.repeatDate = repeatDate;
    }

    public Integer getRepeatDay() {
        return repeatDay;
    }

    public void setRepeatDay(Integer repeatDay) {
        this.repeatDay = repeatDay;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("image=").append(imageUrl);
        sb.append(", location='").append(location).append('\'');
        sb.append(", repeatDate=").append(repeatDate);
        sb.append(", repeatDay=").append(repeatDay);
        sb.append(", dueDate=").append(dueDate);
        sb.append(", priority=").append(priority);
        sb.append(", done=").append(done);
        sb.append(", comments=").append(comments);
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
