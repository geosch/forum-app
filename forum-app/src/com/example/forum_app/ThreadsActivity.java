package com.example.forum_app;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



public class ThreadsActivity extends Activity implements OnItemClickListener{

	private TextView header;
	private ListView list_new_threads;
	private Button button_new_thread;
	private List<JSONObject> json_new_threads;
	
	
	
	/** Getter **/
	public List<JSONObject> getJsonNewThreads() { return json_new_threads; };
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_threads);
		
	    Category category = (Category) this.getIntent().getSerializableExtra("category");
		
	    header = (TextView) findViewById(R.id.category_header);
		header.setText(category.getName());

		
		/* ******************************************* *
		 * The normal list for the threads: 
		 * ******************************************* */
		list_new_threads = (ListView) findViewById(R.id.list_new_threads);
		
		String query_new_threads = "SELECT thread.threadid, thread.subject, MAX(post.createdate) AS createdatemax ";
		query_new_threads += "FROM thread INNER JOIN post ON (thread.threadid = post.threadid) ";
		query_new_threads += "WHERE thread.categoryid = " + category.getId() + " ";
		query_new_threads += "GROUP BY thread.threadid, thread.subject ORDER BY createdatemax DESC"; 

		
		// Send the query to the Database:
		try {
			json_new_threads = this.sendQuery(query_new_threads);
			if(json_new_threads == null)
				throw new NullPointerException("The DB query returned null");
		} catch (NullPointerException e) {
			Log.d("ThreadsActivity", "JSON Parser : Zugriff auf Datenbank fehlgeschlagen!");
			e.printStackTrace();
		}
		
		final ArrayList<String> list = new ArrayList<String>();
		
		// From each row of the JSON Table with the categories we extract the name
		// and insert it into the list
		for (int i = 0; i < json_new_threads.size(); i++) {
			try {
				list.add(json_new_threads.get(i).getString("subject"));
			} catch (JSONException e) {
				Log.d("ThreadsActivity", "JSON Parser : Zugriff auf Categories fehlgeschlagen!");
				e.printStackTrace();
			}
		}
		
		// Now we can set the adapter:
		list_new_threads.setAdapter(new ArrayAdapter<String>(this, R.layout.list_view, list));
		
		list_new_threads.setOnItemClickListener(this);
	

	
	
	
	button_new_thread = (Button) findViewById(R.id.new_thread);
	
	button_new_thread.setOnClickListener(new OnClickListener() {
		public void onClick(View v) 
        {   
			Log.d("ThreadsActivity", "New Thread OnClickListener Fired");
        }
    });
	

}
	
	
    /**
    * sendQuery(String)
    * @param query holds string with full SQL query
    * @return is a list where every item equals one row from query answer
    */
    public List<JSONObject> sendQuery(String query){
    	DBOperator dboperator = DBOperator.getInstance();
    	return dboperator.sendQuery(query);
    }	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.threads, menu);
		return true;
	}



	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		//TODO: Start the Activity with the Thread here!
		TextView test = (TextView) arg1;
		Log.d("ThreadsActivity", "Categories OnChildClickListener Fired with " + test.getText());		
	}

}
