package com.pojo;

import java.util.List;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class DropdownTreeNode {
    private String id;
    private String text;
    private String state;
    List<DropdownItem> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<DropdownItem> getChildren() {
        return children;
    }

    public void setChildren(List<DropdownItem> children) {
        this.children = children;
    }
}
