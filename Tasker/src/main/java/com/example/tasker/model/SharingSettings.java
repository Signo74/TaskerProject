package com.example.tasker.model;

import com.example.tasker.model.base.Entry;
import com.example.tasker.model.base.EntryTypes;

/**
 * Created by victorm on 11/15/13.
 */
public class SharingSettings
    extends Entry {

    SharingSettings (String title, String content, String parent) {
        super(EntryTypes.SETTINGS_ITEM.getEntryId(),  title, content, parent);
    }
}
