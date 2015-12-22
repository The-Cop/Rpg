package com.thecop.rpg.display.interaction;

/**
 * Created by TheCop on 20.12.2015.
 */
public class MenuItem {
    private String controlString;
    private String description;
    //TODO stub for menu item; think later about useful implementation
    private Object object;

    public MenuItem(String controlString, String description) {
        this.controlString = controlString;
        this.description = description;
    }

    public MenuItem(String controlString, String description, Object object) {
        this.controlString = controlString;
        this.description = description;
        this.object = object;
    }

    public String getControlString() {
        return controlString;
    }

    public void setControlString(String controlString) {
        this.controlString = controlString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
