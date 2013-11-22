package com.example.tasker.model;

/**
 * Created by victorm on 11/21/13.
 */
public class DrawerItems {
    private Long id;
    private String title;
    //Shows whether it is a taskByDate, taskByType, List or Project.
    private String itemType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DrawerItems{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", itemType='").append(itemType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
