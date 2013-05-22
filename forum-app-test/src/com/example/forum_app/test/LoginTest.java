package com.example.forum_app.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import com.example.forum_app.*;
import com.example.forum_app.R;
import com.jayway.android.robotium.solo.Solo;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import junit.framework.Assert;

public class LoginTest extends ActivityInstrumentationTestCase2<MainActivity> {
	
	private Solo solo;
	private EditText email;
	private EditText password;
	private LoginActivity lactivity;
	
    @SuppressWarnings("deprecation")
	public LoginTest() {
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
     * testLogin
     * Method trys to login with tester@gmx.at user
     * @throws Throwable
     */
	public void testCorrectLogin() throws Throwable {
		
		solo.waitForActivity("MainActivity");
	    solo.clickOnButton("Login");
	    solo.waitForActivity("LoginActivity");
	    
	    this.lactivity = (LoginActivity) solo.getCurrentActivity();
	    
	    this.email = (EditText) this.lactivity.findViewById(com.example.forum_app.R.id.email);
	    this.password = (EditText) this.lactivity.findViewById(com.example.forum_app.R.id.password);
	    
	    solo.enterText(email, "tester@gmx.at");
	    solo.enterText(password, "12345678");
		
		solo.clickOnButton("Login");
		
		solo.waitForActivity("MainActivity");
		
		Assert.assertEquals(solo.getCurrentActivity().getIntent().getSerializableExtra("userid"), 3);
	}
	
    public void testWrongLogin() throws Throwable {
		
		solo.waitForActivity("MainActivity");
	    solo.clickOnButton("Login");
	    solo.waitForActivity("LoginActivity");
	    
	    this.lactivity = (LoginActivity) solo.getCurrentActivity();
	    
	    this.email = (EditText) this.lactivity.findViewById(com.example.forum_app.R.id.email);
	    this.password = (EditText) this.lactivity.findViewById(com.example.forum_app.R.id.password);
	    
	    solo.enterText(email, "tester@gmx.at");
	    solo.enterText(password, "87654321");
		
		solo.clickOnButton("Login");	
		
		solo.waitForText("This password is incorrect", 1, 5000);
		
		Assert.assertEquals(password.getError(), "This password is incorrect");
	}
}