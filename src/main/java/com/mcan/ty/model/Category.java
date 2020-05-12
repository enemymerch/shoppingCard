package com.mcan.ty.model;

public class Category {
    private String title;
    private Category parent;

    public Category(String title) {
        this.title = title;
    }

    public boolean equals(Category category) {
        return this.title.equals(category.getTitle());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public boolean isEqualOrParent(Category category) {
        if (category == null) {
            return false;
        } else if (category.getTitle().equals(this.title)) {
            return true;
        } else {
            return this.isEqualOrParent(category.getParent());
        }
    }
}
