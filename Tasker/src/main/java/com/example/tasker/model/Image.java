package com.example.tasker.model;

import com.example.tasker.model.base.Entry;
import com.example.tasker.model.base.EntryTypes;

/**
 * Created by victorm on 4/14/2014.
 */
public class Image
    extends Entry {

    private String imageURL;
    private String locationMetadata;

    Image(String title, String content, String parent) {
        super(EntryTypes.IMAGE.getEntryId(), title, content, parent);
    }

    public Image(String title, String content, String parent, String imageURL, String locationMetadata) {
        this(title, content, parent);
        this.imageURL = imageURL;
        this.locationMetadata = locationMetadata;
    }
}