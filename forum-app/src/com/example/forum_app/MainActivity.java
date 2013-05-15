package com.example.forum_app;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ExpandableListView mExpandableList;
	private Button login;
	private Button register;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Resources res = getResources();

		mExpandableList = (ExpandableListView) findViewById(R.id.expandable_list);

		ArrayList<Parent> arrayParents = new ArrayList<Parent>();
		ArrayList<String> arrayChildren = new ArrayList<String>();

		Parent parent = new Parent();
		parent.setTitle(res.getString(R.string.categories));

		String[] childs = res.getStringArray(R.array.expandableListView1);

		for (int j = 0; j < 5; j++) {
			arrayChildren.add(childs[j]);
		}
		parent.setArrayChildren(arrayChildren);

		arrayParents.add(parent);
		mExpandableList.setAdapter(new MyCustomAdapter(MainActivity.this,
				arrayParents));

		ListView listview = (ListView) findViewById(R.id.listView1);
		String[] values = new String[] { "Mobile", "Application", "für",
				"Slany" };

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}

		listview.setAdapter(new ArrayAdapter<String>(this
				.getApplicationContext(), R.layout.list_view, list));
		
		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);
		
		final Intent login_intent = new Intent(this, LoginActivity.class);
		
		login.setOnClickListener(new OnClickListener() {
			public void onClick(View v) 
	        {   
	            startActivity(login_intent);      
	            finish();
	        }
	    });
	
		}
}