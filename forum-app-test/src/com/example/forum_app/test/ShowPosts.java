package com.example.forum_app.test;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import com.example.forum_app.*;
import com.example.forum_app.R;

import org.json.JSONObject;
import org.junit.Test;
import com.jayway.android.robotium.solo.Solo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.LinearLayout;

public class ShowPosts extends ActivityInstrumentationTestCase2<MainActivity>{
	
	private ShowPostsActivity spactivity;
	private Button btRegister;
	private EditText etNickname;
	private EditText etPassword;
	private EditText etPasswordConfirm;
	private Spinner spCountry;
	private EditText etEmail;
	private Spinner spGender;
	private TextView tvRegisterError; 
	
	private Resources res;
	
	private Solo solo;
	private EditText email;
	private EditText password;
	private LoginActivity lactivity;
	
	private MainActivity mactivity;
	private Integer userid;
	private Integer categoryid;
	private Integer threadid;
	private Integer postid;
	
	@SuppressWarnings("deprecation")
	public ShowPosts() {
		super(MainActivity.class);
	}
	
	
	@Override
	protected void setUp() throws Exception {
        
		Log.d("ShowPostsTest: ", "setup");
		User.destroyInstance();
		DBOperator dboperator = DBOperator.getInstance();
		// create new user
		List<JSONObject> answer_user = dboperator.sendInsert("INSERT INTO ForumUser(NickName, Password, Country, Gender, Email) " + 
		                      "VALUES ('TestPostuser', '123456789', 'Austria', 'male', 'testpostuser@tester.at') RETURNING userid;");
		try {
						
			if(answer_user != null)
			{
				if(answer_user.get(0).getInt("success") == 0)
				{
					Log.d("ShowPostsTest: ", "user success = 0");
				}
				else if(answer_user.get(0).getInt("success") == 1)
				{
					Log.d("ShowPostsTest: ", answer_user.toString());
					this.userid = answer_user.get(0).getInt("userid");
					Log.d("ShowPostsTest: ", "Userid: " + this.userid.toString());
				}
				
			}
			else
			{
				Log.d("ShowPostsTest: ", "Answer = null");
			}
		} catch(Exception e) {
			Log.d("ShowPostsTest: ", e.getMessage());
			Log.d("ShowPostsTest: ", answer_user.toString());
		}
		
		
		// insert category
		List<JSONObject> answer_category = dboperator.sendInsert("INSERT INTO Category(Name, Description) VALUES ('TestPostCategory', 'TestPostCategoryDescription') RETURNING categoryid;");
		try {
						
			if(answer_category != null)
			{
				if(answer_category.get(0).getInt("success") == 0)
				{
					Log.d("ShowPostsTest: ", "category success = 0");
				}
				else if(answer_category.get(0).getInt("success") == 1)
				{
					Log.d("ShowPostsTest: ", answer_category.toString());
					this.categoryid = answer_category.get(0).getInt("categoryid");
					Log.d("ShowPostsTest: ", "CategoryID: " + this.categoryid.toString());
				}
				
			}
			else
			{
				Log.d("ShowPostsTest: ", "Answer = null");
			}
		} catch(Exception e) {
			Log.d("ShowPostsTest: ", e.getMessage());
			Log.d("ShowPostsTest: ", answer_category.toString());
		}
		
		
		// insert thread
		List<JSONObject> answer_thread =  dboperator.sendInsert("INSERT INTO Thread(UserID, CategoryID, Subject) VALUES (" + this.userid + " ," + this.categoryid + " ,'TestPostThreadSubject') RETURNING threadid;");
		try {
			
			if(answer_thread != null)
			{
				if(answer_thread.get(0).getInt("success") == 0)
				{
					Log.d("ShowPostsTest: ", "thread success = 0");
				}
				else if(answer_thread.get(0).getInt("success") == 1)
				{
					Log.d("ShowPostsTest: ", answer_thread.toString());
					this.threadid = answer_thread.get(0).getInt("threadid");
					Log.d("ShowPostsTest: ", "ThreadID: " + this.threadid.toString());
				}
				
			}
			else
			{
				Log.d("ShowPostsTest: ", "Answer = null");
			}
		} catch(Exception e) {
			Log.d("ShowPostsTest: ", e.getMessage());
			Log.d("ShowPostsTest: ", answer_thread.toString());
		}
		
		
		// insert Post
		List<JSONObject> answer_post = dboperator.sendInsert("INSERT INTO Post(ThreadID, UserID, PostOrder, Content, CreateDate) " + 
                              "VALUES (" + this.threadid + ", " + 
                              this.userid + ", " +
                              "0," +
				              " 'TestPostContent', " + 
				              " '1950-09-28 01:00') RETURNING postid;"
                              );
		try {
			
			if(answer_post != null)
			{
				if(answer_post.get(0).getInt("success") == 0)
				{
					Log.d("ShowPostsTest: ", "post success = 0");
				}
				else if(answer_post.get(0).getInt("success") == 1)
				{
					Log.d("ShowPostsTest: ", answer_post.toString());
					this.postid = answer_post.get(0).getInt("postid");
					Log.d("ShowPostsTest: ", "PostID: " + this.postid.toString());
				}
				
			}
			else
			{
				Log.d("ShowPostsTest: ", "Answer = null");
			}
		} catch(Exception e) {
			Log.d("ShowPostsTest: ", e.getMessage());
			Log.d("ShowPostsTest: ", answer_post.toString());
		}
		
		Log.d("ShowPostsTest: ", "INSERT INTO Post(ThreadID, UserID, Content, CreateDate) " + 
                "VALUES (" + this.threadid + ", " + 
                this.userid + ", " +
	              "'TestPostContent', " + 
	              "'1950-09-28 01:00');");
		mactivity = getActivity();
    	//solo = new Solo(getInstrumentation(), mactivity);
    	solo = new Solo(getInstrumentation(), getActivity());
	} // end of setUp() method definition
	
	
	@Override
	protected void tearDown() throws Exception {
		//super.tearDown();
		Log.d("ShowPostsTest: ", "teardown");
		
		// delete testdata
		DBOperator dboperator = DBOperator.getInstance();
		dboperator.sendDelete("DELETE FROM post WHERE userid = " + this.userid + ";");
		Log.d("ShowPostsTest: ", "DELETE FROM post WHERE userid = " + this.userid + ";");
		dboperator.sendDelete("DELETE FROM thread WHERE threadid = " + this.threadid + ";");
		Log.d("ShowPostsTest: ", "DELETE FROM thread WHERE threadid = " + this.threadid + ";");
		dboperator.sendDelete("DELETE FROM category WHERE categoryid = " + this.categoryid + ";");
		Log.d("ShowPostsTest: ", "DELETE FROM category WHERE categoryid = " + this.categoryid + ";");
		dboperator.sendDelete("DELETE FROM forumuser WHERE userid = " + this.userid + ";");
		Log.d("ShowPostsTest: ", "DELETE FROM forumuser WHERE userid = " + this.userid + ";");
		
		User.destroyInstance();
		solo.finishOpenedActivities();
	}
	
  public void testACorrectFirstPostLoggedIn() throws Throwable {
		solo.waitForActivity("MainActivity");
		
	    solo.clickOnButton("Login");
	    solo.waitForActivity("LoginActivity");
	    
	    this.lactivity = (LoginActivity) solo.getCurrentActivity();
	    
	    this.email = (EditText) this.lactivity.findViewById(com.example.forum_app.R.id.email);
	    this.password = (EditText) this.lactivity.findViewById(com.example.forum_app.R.id.password);
	    
	    solo.enterText(email, "testpostuser@tester.at");
	    solo.enterText(password, "123456789");
		
		solo.clickOnButton("Login");
		solo.waitForActivity("MainActivity");
		
		solo.clickOnText("TestPostCategory");
		solo.waitForActivity("ThreadActivity");
		
		solo.clickOnText("TestPostThreadSubject");
		
		solo.waitForActivity("ShowPostsActivity");
		
		this.spactivity = (ShowPostsActivity)solo.getCurrentActivity();
		
		LinearLayout main_layout_id;
		main_layout_id = (LinearLayout)this.spactivity.findViewById(com.example.forum_app.R.id.main_layout_id);
		
		LinearLayout ll_exp_panel;
		ll_exp_panel = (LinearLayout)main_layout_id.getChildAt(0);
		
		ExpandablePanel ex_post_panel = (ExpandablePanel) ll_exp_panel.getChildAt(0);
		
		LinearLayout ll_exp_panel_header;
		ll_exp_panel_header = (LinearLayout)ex_post_panel.getChildAt(0);
		
		Button btedit;
        btedit = (Button)ll_exp_panel_header.getChildAt(2);
		
        Integer test = btedit.getVisibility();
        Integer id = btedit.getId();
        Log.d("ShowPostsTest: ", "visibility: " + test.toString());
        Log.d("ShowPostsTest: ", "View.GONE: " + View.GONE);
        Log.d("ShowPostsTest: ", "buttonid: " + id.toString());
        
		Assert.assertEquals(true, (btedit.getVisibility() != View.GONE));
        
		
	}

	public void testBCorrectFirstPostNotLoggedIn() throws Throwable {
		// TestCase
		solo.waitForActivity("MainActivity");
		
		solo.clickOnText("TestPostCategory");
		solo.waitForActivity("ThreadActivity");
		
		solo.clickOnText("TestPostThreadSubject");
		
		solo.waitForActivity("ShowPostsActivity");
		
		this.spactivity = (ShowPostsActivity)solo.getCurrentActivity();
		
		LinearLayout main_layout_id;
		main_layout_id = (LinearLayout)this.spactivity.findViewById(com.example.forum_app.R.id.main_layout_id);
		
		LinearLayout ll_exp_panel;
		ll_exp_panel = (LinearLayout)main_layout_id.getChildAt(0);
		
		ExpandablePanel ex_post_panel = (ExpandablePanel) ll_exp_panel.getChildAt(0);
		
		LinearLayout ll_exp_panel_header;
		ll_exp_panel_header = (LinearLayout)ex_post_panel.getChildAt(0);
		
		Button btedit;
	    btedit = (Button)ll_exp_panel_header.getChildAt(2);
		
		Assert.assertEquals(true, (btedit.getVisibility() == View.GONE));
	}
    
	

}
