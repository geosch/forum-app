package com.example.forum_app;

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
import android.widget.Button;
import android.widget.EditText;


public class PostFormActivity extends Activity {

	private EditText edit_text_title;
	private EditText edit_text_content;
	private Button button_post;
	private Integer user_id;
	private Integer category_id;
	private Integer thread_id;
	private Integer post_id;
	
	
    /**
    * onCreate(Bundle)
    * Starts up the Activity
    */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("PostFormActivity", "Post onCreate Fired");
		setContentView(R.layout.activity_post_form);
		
	    button_post = (Button) findViewById(R.id.button_post);
	    edit_text_content = (EditText) findViewById(R.id.post_content);
	    edit_text_title = (EditText) findViewById(R.id.post_title);

	    

		thread_id = (Integer) this.getIntent().getSerializableExtra("threadID");
		category_id = (Integer) this.getIntent().getSerializableExtra("categoryID");
		post_id = (Integer) this.getIntent().getSerializableExtra("postID");

		Log.d("PostFormActivity", "Post getSerializableExtra Fired");
		
		user_id = User.getInstance().getUserid();

		if(category_id  == -1) {
			edit_text_title.setFocusable(false);
			edit_text_title.setFocusableInTouchMode(false);
			edit_text_title.setEnabled(false);
			
			if(post_id != -1){
				edit_text_title.setText("Edit: ");
				
				button_post.setText("Save Changes");
				
				String content;
				String query = "SELECT content FROM post WHERE postid = '" + post_id + "'";
				List<JSONObject> json = DBOperator.getInstance().sendQuery(query);
				try {
					content = json.get(0).getString("content");
					edit_text_content.setText(content);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			else{
				edit_text_title.setText("Answer: ");
				button_post.setText("Post Answer");
			}
		}
		
		
		button_post.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				Log.d("PostFormActivity", "Post OnClickListener Fired");
				String statement;
				
				if(post_id != -1)	{
					String content = edit_text_content.getText().toString();
					statement = "UPDATE post SET content='" + content + "' WHERE postid='";
					statement += post_id + "'";
					DBOperator.getInstance().sendInsert(statement);
				}
				else  {
					if(thread_id == -1) {
						String title = edit_text_title.getText().toString();
						statement = "INSERT INTO thread(userid,categoryid,subject) VALUES('";
						statement += user_id + "','" + category_id + "','" + title + "')";
						statement += " returning threadid";
						List<JSONObject> json = DBOperator.getInstance().sendInsert(statement);
						try {
							thread_id = json.get(0).getInt("threadid");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					String content = edit_text_content.getText().toString();
					statement = "INSERT INTO post(threadid,userid,content,postorder,createdate) VALUES('";
					statement += thread_id + "','" + user_id + "','" + content + "','0',localtimestamp)";
					DBOperator.getInstance().sendInsert(statement);
				}
				
				Intent return_intent = new Intent();
				setResult(RESULT_OK, return_intent);
				finish();
				//startActivityForResult(return_intent, RESULT_OK);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_form, menu);
		return true;
	}


}
