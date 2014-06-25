package com.example.tasker.model.base;

/**
 * Created by victorm on 4/22/2014.
 */
public class SettingsItem
    extends Entry {

    //0 - direct (checkbox, toggle, etc.)
    //1 - opens a dialog, list, etc
    //2 - hybrid (has a direct option and another button for a side setting)
    private Integer itemType;
    private Boolean isOn;
    private Object itemControlObject;

    SettingsItem (String title, String content, String parent) {
        super(EntryTypes.SETTINGS_ITEM.getEntryId(),  title, content, parent);
    }

    public SettingsItem(String title, String content, String parent, Integer itemType, Boolean isOn, Object itemControlObject) {
        this(title, content, parent);
        this.itemType = itemType;
        this.isOn = isOn;
        this.itemControlObject = itemControlObject;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Boolean getIsOn() {
        return isOn;
    }

    public void setIsOn(Boolean isOn) {
        this.isOn = isOn;
    }

    public Object getItemControlObject() {
        return itemControlObject;
    }

    public void setItemControlObject(Object itemControlObject) {
        this.itemControlObject = itemControlObject;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SettingsItem{");
        sb.append("itemType=").append(itemType);
        sb.append(", isOn=").append(isOn);
        sb.append(", itemControlObject=").append(itemControlObject);
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
