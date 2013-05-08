package com.example.forum_app;

import java.util.ArrayList;
 
public class Parent {
    private String mTitle;
    private ArrayList<String> mArrayChildren;
 
    public String getTitle() {
        return mTitle;
    }
 
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
 
    public ArrayList<String> getArrayChildren() {
        return mArrayChildren;
    }
 
    public void setArrayChildren(ArrayList<String> mArrayChildren) {
        this.mArrayChildren = mArrayChildren;
    }
}