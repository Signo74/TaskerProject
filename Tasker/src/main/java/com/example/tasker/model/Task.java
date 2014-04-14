package com.example.tasker.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The model for tasks
 * Created by victorm on 11/15/13.
 */
public class Task
    extends Entry{

    private Image image;
    private String location;
    private Date repeatDate;
    private Integer repeatDay;
    private Integer priority;
    private boolean done;
    private List<Comment> comments;
    private SharingSettings shareSettings;
    private CustomizationSettings customizationSettings;

    Task(String title, String content, Long parent) {
        super(EntryTypes.TASK.getEntryId(), title, content, parent);
    }

    public Task(String title, String content, Long parent, Image image, String location, Date dueDate, Date repeatDate, Integer repeatDay, Integer priority, boolean done, List<Comment> comments, SharingSettings shareSettings, CustomizationSettings customizationSettings) {
        this(title, content, parent);
        this.image = image;
        this.location = location;
        this.dueDate = dueDate;
        this.repeatDate = repeatDate;
        this.repeatDay = repeatDay;
        this.priority = priority;
        this.done = done;
        this.comments = comments;
        this.shareSettings = shareSettings;
        this.customizationSettings = customizationSettings;
    }
}
