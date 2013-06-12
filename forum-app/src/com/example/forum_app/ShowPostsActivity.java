package com.example.forum_app;

import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowPostsActivity extends Activity
{	
	private List<JSONObject> json_posts;
	private Integer threadid = 0;
	private ExpandablePanel ex_post_panel;
	private LinearLayout ll;
	private Integer loged_in_userid = -1;
	static final private Integer POST_ID_INC_VALUE = 100000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_posts);
		
		ForumThread thread = (ForumThread) this.getIntent().getSerializableExtra("thread");
		
		this.threadid = thread.getId();
		if (User.getInstance() != null)
		{
			this.loged_in_userid = User.getInstance().getUserid();
		}
		Log.d("ShowPostsTest: ", "[Activity] loged_in_userid: " + this.loged_in_userid.toString());
		Log.d("ShowPostsTest: ", "[Activity] threadid: " + this.threadid.toString());
		String query_posts = 
		"SELECT forumuser.userid, forumuser.nickname, thread.subject, post.postid, post.content, post.createdate, thread.subject " 
		+ " FROM post INNER JOIN thread on(post.threadid = thread.threadid)"
		+ " INNER JOIN forumuser on (post.userid = forumuser.userid)"
		+ " WHERE thread.threadid = " + threadid
		+ " ORDER BY post.createdate";
		// Send the query to the Database:
		DBOperator db = DBOperator.getInstance();
		
		try 
		{
			json_posts = db.sendQuery(query_posts);
			if(json_posts == null)
			{
			   throw new NullPointerException("The DB query returned null");
			}
		} 
		catch (NullPointerException e) 
		{
			Log.d("ShowPostsActivity", "JSON Parser : Zugriff auf Datenbank fehlgeschlagen!");
			e.printStackTrace();
		}
		
		//call the main layout from xml
		ll = (LinearLayout)findViewById(R.id.main_layout_id);
		Integer userid = 0;
		String content = "";
		Integer postid = 0;
		String subject = "";
		String nickname = "";
		for (int i = 0; i < json_posts.size(); i++) 
		{
			try 
			{
				userid = json_posts.get(i).getInt("userid");
				content = json_posts.get(i).getString("content");
				postid = json_posts.get(i).getInt("postid");
				subject = json_posts.get(i).getString("subject");
				nickname = json_posts.get(i).getString("nickname");
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
			// change follow posts headers
			if(i > 0)
			{
				subject = "[RE] " + subject;
			}
			LinearLayout inflate_view = new LinearLayout(this);
	        inflate_view =  (LinearLayout) getLayoutInflater().inflate(R.layout.postpanel, null);
	        ex_post_panel = (ExpandablePanel)inflate_view.getChildAt(0);
	        ll.addView(inflate_view);
	       
	        LinearLayout header = new LinearLayout(this);
	        header = (LinearLayout)ex_post_panel.getChildAt(0);
	        TextView tvusername = new TextView(this);
	        tvusername = (TextView)header.getChildAt(0);
	        tvusername.setText(nickname);
	        
	        TextView tvthreadtitle = new TextView(this);
	        tvthreadtitle = (TextView)header.getChildAt(1);
	        tvthreadtitle.setText(subject);
	        
	        
	        Button btedit = new Button(this);
	        btedit = (Button)header.getChildAt(2);
	        
	        Log.d("ShowPostsTest: ", "[Activity] userid: " + userid.toString());
	        Log.d("ShowPostsTest: ", "[Activity] this.loged_in_userid: " + this.loged_in_userid.toString());
	        if(!userid.equals(this.loged_in_userid))
	        {
	        	Log.d("ShowPostsTest: ", "[Activity] gone: " + this.loged_in_userid.toString());
		        btedit.setVisibility(View.GONE);
	        }
	        btedit.setId(POST_ID_INC_VALUE + postid);
	        
	        TextView tvcontent = new TextView(this);
	        tvcontent = (TextView)ex_post_panel.getChildAt(1);
	        tvcontent.setText(content);
	        
	        
	        ex_post_panel.setOnExpandListener(new ExpandablePanel.OnExpandListener() {
			    public void onCollapse(View handle, View content) {
			    	Button btn = (Button)handle;
			    	btn.setBackgroundResource(R.drawable.expander_ic_minimized);
			        ex_post_panel.setCollapsedHeight(20);
			     }
			     public void onExpand(View handle, View content) {
			     	Button btn = (Button)handle;
			     	btn.setBackgroundResource(R.drawable.expander_ic_maximized);
			     	ex_post_panel.setCollapsedHeight(20);
		     }
		     });
		
		}

		Button button_new_post = (Button) findViewById(R.id.new_post);	
		LinearLayout button_parent = (LinearLayout) button_new_post.getParent();
		if(User.getInstance() == null)
		{
			button_parent.setVisibility(View.GONE);
		}
		else
		{
			button_parent.setVisibility(View.VISIBLE);
			final Intent post_form_intent = new Intent(this, PostFormActivity.class);
			button_new_post.setOnClickListener(new OnClickListener() {
				public void onClick(View v) 
				{   
					Log.d("ShowPosts", "New Post onClick fired");
					post_form_intent.putExtra("categoryID", -1);
					post_form_intent.putExtra("threadID", threadid);
					post_form_intent.putExtra("postID", -1);

					startActivityForResult(post_form_intent, 1);
				}
			});
		}
		
	}
	
	public void setThreadId(Integer threadid)
	{
		this.threadid = threadid;
	}
	
	public void setUserId(Integer userid)
	{
		this.loged_in_userid = userid;
	}
	
	public void editPost(View view) {
		Button edit = (Button)view;
		Integer postid = edit.getId() - POST_ID_INC_VALUE;
		Log.d("ShowPosts", "postid: " + postid.toString());
		
		final Intent post_form_intent = new Intent(this, PostFormActivity.class);
		
		post_form_intent.putExtra("categoryID", -1);
		post_form_intent.putExtra("threadID", -1);
		post_form_intent.putExtra("postID", postid);

		startActivityForResult(post_form_intent, 1);

		Log.d("ShowPosts", "New Post OnClickListener Fired");
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("ShowPosts", "onActivityResult started");	
		finish();
		startActivity(getIntent());
	}
}