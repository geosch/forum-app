package com.example.forum_app;

import java.util.ArrayList;

import android.widget.TextView;
 
public class Parent {
    private String title;
    private ArrayList<TextView> array_children;
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String t) {
        this.title = t;
    }
 
    public ArrayList<TextView> getArrayChildren() {
        return array_children;
    }
 
    public void setArrayChildren(ArrayList<TextView> children) {
        this.array_children = children;
    }
}