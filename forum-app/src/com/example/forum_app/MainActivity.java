package com.example.forum_app;


import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnChildClickListener, OnItemClickListener{
	
	// Define here how many "Newest Posts" should be displayed:
	final static int NUMBER_NEWEST_POSTS = 3;
	
	private ArrayList<Category> categories_obj;
	private ExpandableListView list_newest_posts;
	private ListView list_categories;
	private Button login;
	private Button register;
	private int userid = 0;
	private static User user;
	private static final int REGISTER_ACTIVITY = 1;
	private static final int LOGIN_ACTIVITY = 2;

	private List<JSONObject> json_categories;
	private List<JSONObject> json_newest_posts;

	
	private View.OnClickListener logoutlistener;
	private View.OnClickListener loginlistener;
	
	
	/** Getter **/
	public List<JSONObject> getJsonNewestPosts() { return json_newest_posts; };
	public List<JSONObject> getJsonCategories() { return json_categories; };
	public int getMaxNumberNewesPosts() { return NUMBER_NEWEST_POSTS; };
	public ArrayList<Category> getCategoriesObj() { return categories_obj; };
	
	
    /**
    * onCreate(Bundle)
    * Starts up the Activity
    */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Resources res = getResources();


		
		
		
		/* ******************************************* *
		 * The expandable list for the newest posts: 
		 * ******************************************* */
		list_newest_posts = (ExpandableListView) findViewById(R.id.list_newest_posts);
	
		String query_newest_posts = "SELECT Subject, Threadid FROM Thread WHERE ThreadID IN ";
		query_newest_posts += "(SELECT DISTINCT ThreadID FROM Post WHERE ThreadID IN ";
		query_newest_posts += "(SELECT ThreadID FROM Post ORDER BY CreateDate DESC FETCH FIRST ";
		query_newest_posts += NUMBER_NEWEST_POSTS; 
		query_newest_posts += " ROWS ONLY))";
		
		// Send the query to the Database:
		try {
			json_newest_posts = this.sendQuery(query_newest_posts);
			if(json_newest_posts == null)
				throw new NullPointerException("The DB query returned null");
		} catch (NullPointerException e) {
			Log.d("MainActivity", "JSON Parser : Zugriff auf Datenbank fehlgeschlagen!");
			e.printStackTrace();
		}

		// This is needed for the Custom Adapter to handle the expandable list:
		ArrayList<Parent> arrayParents = new ArrayList<Parent>();
		ArrayList<TextView> arrayChildren = new ArrayList<TextView>();
		Parent parent = new Parent();
		parent.setTitle(res.getString(R.string.new_posts));

		// If there are less newer posts than in NUMBER_NEWEST_POSTS, display the
		// ones that are available anyway:
		int max = ((json_newest_posts.size() <= NUMBER_NEWEST_POSTS)
						? json_newest_posts.size() : NUMBER_NEWEST_POSTS);
		
		// From each row of the JSON Table with the newest posts we extract the subject
		// and insert it into the child-array. Then we set the parent correctly:
		for (int i = 0; i < max; i++) {
			try {
				TextView child = new TextView(getApplicationContext());
				child.setText(json_newest_posts.get(i).getString("subject"));		
				arrayChildren.add(child);
			} catch (JSONException e) {
				Log.d("MainActivity", "JSON Parser : Zugriff auf neueste Posts fehlgeschlagen!");
				e.printStackTrace();
			}
		}
		parent.setArrayChildren(arrayChildren);
		arrayParents.add(parent);
		
		// Now we can set the adapter:
		MyCustomAdapter adapter = new MyCustomAdapter(MainActivity.this, arrayParents);
		list_newest_posts.setAdapter(adapter);
		
		list_newest_posts.setOnChildClickListener(this);
		
		// the next line is there so the list starts expanded: 
		list_newest_posts.expandGroup(0);
		
		

		/* ******************************************* *
		 * The normal list for the categories: 
		 * ******************************************* */
		list_categories = (ListView) findViewById(R.id.list_categories);
		categories_obj =  new ArrayList<Category>();
		
		
		String query_categories = "SELECT * FROM Category";
		// Send the query to the Database:
		try {
			json_categories = this.sendQuery(query_categories);
			if(json_categories == null)
				throw new NullPointerException("The DB query returned null");
		} catch (NullPointerException e) {
			Log.d("MainActivity", "JSON Parser : Zugriff auf Datenbank fehlgeschlagen!");
			e.printStackTrace();
		}
		
		
		
		// From each row of the JSON Table with the categories we extract the name
		// and insert it into the list
		for (int i = 0; i < json_categories.size(); i++) {
			try {
				String name = json_categories.get(i).getString("name");
				int id = Integer.parseInt(json_categories.get(i).getString("categoryid"));
				String description = json_categories.get(i).getString("description");
				categories_obj.add(new Category(name, id, description));
			} catch (JSONException e) {
				Log.d("MainActivity", "JSON Parser : Zugriff auf Categories fehlgeschlagen!");
				e.printStackTrace();
			}
		}
		
		Log.d("MainActivity", categories_obj.toString());
		
		// Now we can set the adapter:
		list_categories.setAdapter(new ArrayAdapter<Category>(this, R.layout.list_view, this.categories_obj));
		
		list_categories.setOnItemClickListener(this);

		

		/* ******************************************* *
		 * The The bottom Bar with Login and Register: 
		 * ******************************************* */
		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);
		
		final Intent login_intent = new Intent(this, LoginActivity.class);
		final Intent register_intent = new Intent(this, RegisterActivity.class);
		

		logoutlistener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				User.destroyInstance();
				login.setText(R.string.login);
				login.setOnClickListener(loginlistener);
				register.setVisibility(View.VISIBLE);
				}
		};
		
		loginlistener = new OnClickListener() {

			public void onClick(View v) 
	        {   
				Log.d("MainActivity", "Login OnClickListener Fired");
	            startActivityForResult(login_intent, LOGIN_ACTIVITY);      
	        }
	    };
		
		login.setOnClickListener(loginlistener);
		
		register.setOnClickListener(new OnClickListener() {
			public void onClick(View v) 
	        {   


	            startActivityForResult(register_intent, REGISTER_ACTIVITY);      

	        }
	    });
	
	}
	
	
    /**
    * onCreateOptionsMenu(Menu)
    * Inflate the menu; this adds items to the action bar if it is present.
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	  if (requestCode == REGISTER_ACTIVITY || requestCode == LOGIN_ACTIVITY) {
    		  user = User.getInstance();
    	      if(resultCode == RESULT_OK && user != null){    
    	    	 Log.d("Main: ", "OnActivityResult!");
    	         
    	         changeLoginToLogout();
    	         Log.d("Main: ", "Returned from Register Activity with uID: " + user.getUserid());
    	         Toast.makeText(getApplicationContext(), "Your are now logged in!", Toast.LENGTH_SHORT).show();
    	         login.setText("Logout");
    	         register.setVisibility(View.INVISIBLE);
    	     }
    	     if (resultCode == RESULT_CANCELED) {    
    	         //Write your code if there's no result
    	     }
    	  }
    	}//onActivityResult

    /**
    * onChildClick(ExpandableListView, View, int, int, long)
    * This is the OnClickListener for the Newest Posts List
    */
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		
		String subject = new String();
		Integer threadid = -1;
		try {
			subject = json_newest_posts.get(childPosition).getString("subject");
			threadid = json_newest_posts.get(childPosition).getInt("threadid");
		} catch (JSONException e) {
			Log.d("IntentShowPosts", "JSON Parser : Zugriff auf neueste Posts fehlgeschlagen!");
			e.printStackTrace();
		}
		ForumThread thread = new ForumThread(threadid, subject);
		
		Log.d("IntentShowPosts", "newest posts onChildClick with " + thread.getId());
		Log.d("IntentShowPosts", thread.toString());
		
		final Intent post_intent = new Intent(this, ShowPostsActivity.class);
		post_intent.putExtra("thread", thread);
		
		startActivity(post_intent);	
		
		return false; // set to true (95% sure ;-) )
	}
	
	
    /**
    * onItemClick(AdapterView<?>, View, int, long)
    * This is the OnClickListener for the Categories List
    */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		TextView category = (TextView) arg1;
		Category selectedCategory = (Category) arg0.getAdapter().getItem(arg2);

		final Intent threads_intent = new Intent(this, ThreadsActivity.class);
		threads_intent.putExtra("categoryID", category.getText());
		if (selectedCategory == null)
			Log.d("MainActivity", "selectedCategoty null");
		threads_intent.putExtra("category", selectedCategory);
		startActivity(threads_intent);
		Log.d("MainActivity", "Categories OnChildClickListener Fired with " + category.getText());
	}
	
	private void changeLoginToLogout() {
		if(user != null)
			login.setOnClickListener(logoutlistener);
	}
}
