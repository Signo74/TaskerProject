package com.example.tasker.model.base;

/**
 * Created by victorm on 4/14/2014.
 * @type - used to identify the type of entry. See @EtryTypes.
 * @id - unique Id as defined by the row in DB.
 * @title - the title of the entry
 * @content - the content of the entry
 * @category - the @id of the category entry if it exists
 */
public class Entry {

    private Long id;
    private Integer type;
    private String title;
    private String content;
    private String category;

    public Entry(Integer type, String title, String content, String category) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Entry{");
        sb.append("id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
