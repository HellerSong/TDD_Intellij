package com.pojo;

import java.util.List;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class DropdownTreeNode {
    private String optionValue;
    private String optionHtmlContent;
    private String optionType;
    private String state;
    List<DropdownItem> children;

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public String getOptionHtmlContent() {
        return optionHtmlContent;
    }

    public void setOptionHtmlContent(String optionHtmlContent) {
        this.optionHtmlContent = optionHtmlContent;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
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
