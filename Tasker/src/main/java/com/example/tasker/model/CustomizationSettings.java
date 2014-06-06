package com.example.tasker.model;

import com.example.tasker.model.base.Entry;
import com.example.tasker.model.base.EntryTypes;
import com.example.tasker.model.base.SettingsItem;

/**
 * Created by victorm on 11/15/13.
 */
public class CustomizationSettings
    extends Entry {

    CustomizationSettings (String title, String content, Long parent) {
        super(EntryTypes.SETTINGS_ITEM.getEntryId(),  title, content, parent);
    }

    public CustomizationSettings() {
        super(EntryTypes.SETTINGS_ITEM.getEntryId(),  "New", "Content", 0l);
    }

}
