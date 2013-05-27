package com.example.forum_app;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	final static int NUMBER_NEWEST_POSTS = 2;
	
	private ExpandableListView list_newest_posts;
	private ListView list_categories;
	private Button login;
	private Button register;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Resources res = getResources();

		
		
		list_newest_posts = (ExpandableListView) findViewById(R.id.list_newest_posts);
	
		String query_newest_posts = "SELECT Subject FROM Thread WHERE ThreadID IN ";
		query_newest_posts += "(SELECT DISTINCT ThreadID FROM Post WHERE ThreadID IN ";
		query_newest_posts += "(SELECT ThreadID FROM Post ORDER BY CreateDate DESC FETCH FIRST ";
		query_newest_posts += NUMBER_NEWEST_POSTS; 
		query_newest_posts += " ROWS ONLY))";
		
		List<JSONObject> table_newest_posts = this.sendQuery(query_newest_posts);
		
		ArrayList<Parent> arrayParents = new ArrayList<Parent>();
		ArrayList<TextView> arrayChildren = new ArrayList<TextView>();

		Parent parent = new Parent();
		parent.setTitle(res.getString(R.string.new_posts));

		for (int i = 0; i < NUMBER_NEWEST_POSTS; i++) {
			try {
				TextView child = new TextView(getApplicationContext());
				child.setText(table_newest_posts.get(i).getString("subject"));
				
				child.setOnClickListener(new OnClickListener() {
		            @Override
					public void onClick(View v) {

//						AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//						builder.setMessage("asdasd").setTitle("asdasfasf");
//			           
//						AlertDialog dialog = builder.create();
//						dialog.show();
						finish();

					}
				});
				
				arrayChildren.add(child);
			} catch (JSONException e) {
				Log.d("JSON Fail", "Zugriff auf neueste Posts fehlgeschlagen!");
				e.printStackTrace();
			}
		}
		parent.setArrayChildren(arrayChildren);

		arrayParents.add(parent);
		list_newest_posts.setAdapter(new MyCustomAdapter(MainActivity.this,
				arrayParents));
		
		// the next line is there so the list starts expanded: 
		list_newest_posts.expandGroup(0);
		
		


		list_categories = (ListView) findViewById(R.id.list_categories);
		
		String query_categories = "SELECT * FROM Category";
		
		List<JSONObject> categories = this.sendQuery(query_categories);
		
		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < categories.size(); i++) {
			try {
				list.add(categories.get(i).getString("name"));
			} catch (JSONException e) {
				Log.d("JSON Fail", "Zugriff auf Categories fehlgeschlagen!");
				e.printStackTrace();
			}
		}
		

		list_categories.setAdapter(new ArrayAdapter<String>(this
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
		
		final Intent register_intent = new Intent(this, RegisterActivity.class);
		
		register.setOnClickListener(new OnClickListener() {
			public void onClick(View v) 
	        {   
	            startActivity(register_intent);      
	            finish();
	        }
	    });
	
		}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * sendQuery
     * @param query holds string with full SQL query
     * @return is a list where every item equals one row from query answer
     */
    public List<JSONObject> sendQuery(String query){
    	
    	DBOperator dboperator = DBOperator.getInstance();
    	return dboperator.sendQuery(query);
    	
    }
}
