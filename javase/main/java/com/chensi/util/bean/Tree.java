package com.chensi.util.bean;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private String id;

    private String name;

    private String parentId;

    private String iconOpen;

    private String iconClose;

    private boolean checked;

    private boolean open;

    private boolean isParent;

    private String imgPath;

    private boolean drag;

    private boolean drop;

    private boolean clicked;

    private String level;

    private String termSn;

    private List<Tree> children = new ArrayList<Tree>();

    public boolean getDrag() {
        return this.drag;
    }

    public void setDrag(boolean drag) {
        this.drag = drag;
    }

    public boolean getDrop() {
        return this.drop;
    }

    public void setDrop(boolean drop) {
        this.drop = drop;
    }

    public String getImgPath() {
        return this.imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<Tree> getChildren() {
        return this.children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public String getIconClose() {
        return this.iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    public String getIconOpen() {
        return this.iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public boolean isIsParent() {
        return this.isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public boolean isClicked() {
        return this.clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public String getTermSn() {
        return this.termSn;
    }

    public void setTermSn(String termSn) {
        this.termSn = termSn;
    }
}

