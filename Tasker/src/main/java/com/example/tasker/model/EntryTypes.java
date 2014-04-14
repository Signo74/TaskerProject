package com.example.tasker.model;

/**
 * Created by victorm on 4/14/2014.
 */
public enum EntryTypes {
    //1-100 base entries
    TASK(1),
    PROJECT(2),
    COMMENT(3),
    IMAGE(4),
    TODO_LIST(5),

    //100 - 200 task sub entries
    SUB_TASK(101),
    TODO(102);

    private int entryId;

    EntryTypes(int value) {
        this.entryId = value;
    }

    public int getEntryId() {
        return entryId;
    }
}
