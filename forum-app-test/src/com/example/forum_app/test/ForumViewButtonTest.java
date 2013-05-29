package com.example.forum_app.test;

import junit.framework.Assert;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;

import com.example.forum_app.LoginActivity;
import com.example.forum_app.MainActivity;
import com.example.forum_app.RegisterActivity;
import com.jayway.android.robotium.solo.Solo;
//import android.test.AndroidTestCase;

//public class SomeOtherTest extends AndroidTestCase{

public class ForumViewButtonTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	
    
	public ForumViewButtonTest() {
        super(MainActivity.class);
        
        
    }	
    @Override
    protected void setUp() throws Exception{     
    	solo = new Solo(getInstrumentation(), getActivity());
    	
    }
    
    @Override
    protected void tearDown() {
    	//mActivity.finish();
    	solo.finishOpenedActivities();
    }
	


    public void testLoginButton() throws Throwable {
		solo.waitForActivity("MainActivity");
		solo.clickOnButton("Login");
		solo.waitForActivity("LoginActivity");
		Assert.assertNotNull(solo.getCurrentActivity());
		Assert.assertEquals(LoginActivity.class, solo.getCurrentActivity().getClass());    
    }
    
    
    public void testRegisterButton() throws Throwable {
		solo.waitForActivity("MainActivity");
		solo.clickOnButton("Register");
		solo.waitForActivity("RegisterActivity");
		Assert.assertNotNull(solo.getCurrentActivity());
		Assert.assertEquals(RegisterActivity.class, solo.getCurrentActivity().getClass());    
    }
    
}



