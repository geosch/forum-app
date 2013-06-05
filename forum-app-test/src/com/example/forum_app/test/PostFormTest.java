package com.example.forum_app.test;

import java.util.Random;

import com.example.forum_app.*;
import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class PostFormTest extends ActivityInstrumentationTestCase2<MainActivity> {
	
	private Solo solo;
	private EditText email;
	private EditText password;
	private EditText edit_title;
	private EditText edit_content;
	private LoginActivity login_activity;
	private MainActivity main_activity;
	private PostFormActivity post_form_activity;
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();

	String randomString( int len ) 
	{
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}


	
	public PostFormTest() {
    	super(MainActivity.class);
	}

    @Override
    protected void setUp() throws Exception{
    	super.setUp();
    	solo = new Solo(getInstrumentation(), getActivity());
    }
    
    @Override
    protected void tearDown() {
    	//mActivity.finish();
    	solo.finishOpenedActivities();
    }
    
    /**
     * testPost
     * Logs in and attempts to make a thread
     * @throws Throwable
     */
	public void testPostForm() throws Throwable {
		
		solo.waitForActivity("MainActivity");
	    solo.clickOnButton("Login");
	    solo.waitForActivity("LoginActivity");
	    
	    this.login_activity = (LoginActivity) solo.getCurrentActivity();
	    
	    this.email = (EditText) this.login_activity.findViewById(com.example.forum_app.R.id.email);
	    this.password = (EditText) this.login_activity.findViewById(com.example.forum_app.R.id.password);
	    
	    solo.enterText(email, "tester@gmx.at");
	    solo.enterText(password, "12345678");
		
		solo.clickOnButton("Login");
		
		solo.waitForActivity("MainActivity");
		
		this.main_activity = (MainActivity) solo.getCurrentActivity();
		
		String name = main_activity.getCategoriesObj().get(0).getName();
		
		solo.clickOnText(name);
		
		solo.waitForActivity("ThreadsActivity");
		
		solo.clickOnButton("New Thread");
		
		solo.waitForActivity("PostFormActivity");
		
		this.post_form_activity = (PostFormActivity) solo.getCurrentActivity();
		
		this.edit_title = (EditText) this.post_form_activity.findViewById(com.example.forum_app.R.id.post_title);
	    this.edit_content = (EditText) this.post_form_activity.findViewById(com.example.forum_app.R.id.post_content);
	
	    String subject = "TESTTEST";
	    
	    solo.enterText(edit_title, subject);
	    String content = "DIES IST EIN TEST!";
	    solo.enterText(edit_content, content);
	    
	    solo.clickOnButton("Post");
	    
	    solo.waitForActivity("ThreadsActivity");
	    
	    
	    
	    TextView test = solo.getText(subject);
	    
	    assertEquals(subject, test.getText().toString());
	    
	    Log.d("PostTest", "PostTest finished " + subject + " " +test.getText().toString());

	    
	    
	    
	    String statement = "DELETE FROM post WHERE content='";
	    statement += content + "'";
	    DBOperator.getInstance().sendDelete(statement);

	    
	    statement = "DELETE FROM thread WHERE subject='";
	    statement += subject + "'";
	    DBOperator.getInstance().sendDelete(statement);
	}
}