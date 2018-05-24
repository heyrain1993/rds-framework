package com.heyu.framework.model;

import java.util.List;

public class TreeEntity<T> extends DataEntity<T>{


    protected String parentId;

    protected List<T> children;
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
