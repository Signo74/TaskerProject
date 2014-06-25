package com.example.tasker.model;

import com.example.tasker.model.base.Entry;
import com.example.tasker.model.base.EntryTypes;

import java.util.Date;
import java.util.List;

/**
 * Created by victorm on 4/14/2014.
 */
public class Project
    extends Entry {

    private Long duration;
    private Task projectContent;

    Project(String title, String content, String parent) {
        super(EntryTypes.PROJECT.getEntryId(), title, content, parent);
    }

    public Project(Long duration, String title, String content, String parent, String imageUrl, String location, Date repeatDate, Integer repeatDay, Date dueDate, Integer priority, boolean done, List<Comment> comments) {
        this(title, content, parent);
        this.duration = duration;
        this.projectContent = new Task(title, content, parent, imageUrl, location, repeatDate, repeatDay, dueDate, priority, done, comments);
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Task getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(Task projectContent) {
        this.projectContent = projectContent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("duration=").append(duration);
        sb.append(", projectContent=").append(projectContent);
        sb.append('}');
        return sb.toString();
    }
}
