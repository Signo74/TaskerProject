package com.example.tasker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victorm on 2/6/14.
 */
public class ExpandableListGroup {
    private String header;
    private List<String> children = new ArrayList<String>();

    public ExpandableListGroup(String title) {
        this.header = title;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExpandableListGroup{");
        sb.append("header='").append(header).append('\'');
        sb.append(", children=").append(children.toString());
        sb.append('}');
        return sb.toString();
    }
}
