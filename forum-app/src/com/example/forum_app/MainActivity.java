package com.example.forum_app;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
 
import java.util.ArrayList;


import com.example.forum_app.R.array;
 
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
       
        
        
        ListAdapter adapter = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1, list);
      /*  {
        	@Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                //YOUR CHOICE OF COLOR
                textView.setTextColor(Color.BLACK);

                return view;
            }
        };*/
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