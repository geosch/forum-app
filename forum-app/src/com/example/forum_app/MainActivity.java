package com.example.forum_app;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
 
public class MainActivity extends Activity {
    private ExpandableListView mExpandableList;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();

        ListView listview = (ListView) findViewById(R.id.listView1);
        String[] values = new String[] { "Mobile", "Application", "für",
            "Slany"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
          list.add(values[i]);
        }
       
        
        
        ListAdapter adapter = new ArrayAdapter<String>(this.getApplicationContext(), 
        					R.layout.list_view, list); //pass own xml file to adapter instead of simple_list_xxx
        
        listview.setAdapter(adapter);
        
        mExpandableList = (ExpandableListView)findViewById(R.id.expandable_list);
 
        ArrayList<Parent> arrayParents = new ArrayList<Parent>();
        ArrayList<String> arrayChildren = new ArrayList<String>();
 
        Parent parent = new Parent();
        parent.setTitle(res.getString(R.string.categories));
             
        String[] childs = res.getStringArray(R.array.expandableListView1);
            arrayChildren = new ArrayList<String>();
            for (int j = 0; j < 5; j++) {
                arrayChildren.add(childs[j]);
            }
            parent.setArrayChildren(arrayChildren);
 
            //in this array we add the Parent object. We will use the arrayParents at the setAdapter
            arrayParents.add(parent);
        
 
        //sets the adapter that provides data to the list.
        mExpandableList.setAdapter(new MyCustomAdapter(MainActivity.this,arrayParents));
    }
}