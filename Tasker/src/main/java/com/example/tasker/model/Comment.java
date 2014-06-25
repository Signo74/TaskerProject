package com.example.tasker.model;

import com.example.tasker.model.base.Entry;
import com.example.tasker.model.base.EntryTypes;

/**
 * Created by victorm on 4/14/2014.
 */
public class Comment
    extends Entry {

    private String author;
    private String datePosted;

    Comment (String title, String content, String parent) {
        super(EntryTypes.COMMENT.getEntryId(),  title, content, parent);
    }

    public Comment(String title, String content, String parent, String author, String datePosted) {
        this(title, content, parent);
        this.author = author;
        this.datePosted = datePosted;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append(super.toString()).append('\'');
        sb.append("author='").append(author).append('\'');
        sb.append(", datePosted='").append(datePosted).append('\'');
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
