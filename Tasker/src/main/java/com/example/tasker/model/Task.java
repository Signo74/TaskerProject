package com.example.tasker.model;

import com.example.tasker.model.base.Entry;
import com.example.tasker.model.base.EntryTypes;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The model for tasks
 * Created by victorm on 11/15/13.
 */
public class Task
    extends Entry {

    private static Calendar calendar = Calendar.getInstance();

    private String imageUrl;
    private String location;
    private Date repeatDate;
    private int repeatDay;
    private int dueDay;
    private int dueMonth;
    private int dueYear;
    private int priority;
    private boolean done;
    private List<Comment> comments;

    Task(String title, String content, String parent) {
        super(EntryTypes.TASK.getEntryId(), title, content, parent);
    }

    public Task(String title, String content, String parent, String imageUrl, String location, Date repeatDate, int repeatDay, Calendar dueDate, int priority, boolean done, List<Comment> comments) {
        this(title, content, parent);
        this.imageUrl = imageUrl;
        this.location = location;
        this.repeatDate = repeatDate;
        this.repeatDay = repeatDay;
        this.dueDay = dueDate.DAY_OF_MONTH;
        this.dueMonth = dueDate.MONTH;
        this.dueYear = dueDate.YEAR;
        this.priority = priority;
        this.done = done;
        this.comments = comments;
    }

    public Task(String title, String content, String parent, String imageUrl, String location, Date repeatDate, int repeatDay, int dueDay, int dueMonth, int dueYear, int priority, boolean done, List<Comment> comments) {
        this(title, content, parent);
        this.imageUrl = imageUrl;
        this.location = location;
        this.repeatDate = repeatDate;
        this.repeatDay = repeatDay;
        this.dueDay = dueDay;
        this.dueMonth = dueMonth;
        this.dueYear = dueYear;
        this.priority = priority;
        this.done = done;
        this.comments = comments;
    }

    public static Calendar getCalendar() {
        return calendar;
    }

    public static void setCalendar(Calendar calendar) {
        Task.calendar = calendar;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
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

    public int getRepeatDay() {
        return repeatDay;
    }

    public void setRepeatDay(int repeatDay) {
        this.repeatDay = repeatDay;
    }

    public int getDueDay() {
        return dueDay;
    }

    public void setDueDay(int dueDay) {
        this.dueDay = dueDay;
    }

    public int getDueMonth() {
        return dueMonth;
    }

    public void setDueMonth(int dueMonth) {
        this.dueMonth = dueMonth;
    }

    public int getDueYear() {
        return dueYear;
    }

    public void setDueYear(int dueYear) {
        this.dueYear = dueYear;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
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
        sb.append(super.toString());
        sb.append("imageUrl='").append(imageUrl).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append(", repeatDate=").append(repeatDate);
        sb.append(", repeatDay=").append(repeatDay);
        sb.append(", dueDay=").append(dueDay);
        sb.append(", dueMonth=").append(dueMonth);
        sb.append(", dueYear=").append(dueYear);
        sb.append(", priority=").append(priority);
        sb.append(", done=").append(done);
        sb.append(", comments=").append(comments);
        sb.append('}');
        return sb.toString();
    }
}
