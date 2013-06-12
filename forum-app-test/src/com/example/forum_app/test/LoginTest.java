package com.example.forum_app.test;

import com.example.forum_app.*;
import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import junit.framework.Assert;

public class LoginTest extends ActivityInstrumentationTestCase2<MainActivity> {
	
	private Solo solo;
	private EditText email;
	private EditText password;
	private LoginActivity lactivity;
	
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
		User user = User.getInstance();
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getUserid(), 3);
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