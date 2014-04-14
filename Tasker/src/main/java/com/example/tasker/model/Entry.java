package com.example.tasker.model;

/**
 * Created by victorm on 4/14/2014.
 * @type - used to identify the type of entry
 * @id - unique Id as defined by the row in DB.
 * @title - the title of the entry
 * @content - the content of the entry
 * @parent - the @id of the parent entry if it exists
 */
public class Entry {

    private Integer id;
    private Integer type;
    private String title;
    private String content;
    private Long parent;

    public Entry(Integer type, String title, String content, Long parent) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.parent = parent;
    }

    public Integer getId() {
        return id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Entry{");
        sb.append("id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", parent='").append(parent).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
