package com.example.forum_app.test;

import junit.framework.Assert;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;

import com.example.forum_app.LoginActivity;
import com.example.forum_app.MainActivity;
import com.jayway.android.robotium.solo.Solo;
//import android.test.AndroidTestCase;

//public class SomeOtherTest extends AndroidTestCase{

public class SomeOtherTest extends ActivityInstrumentationTestCase2<MainActivity> {

	protected static final int TIME_OUT = 5000;
	private MainActivity mActivity;
	private Activity lActivity;
	private Button login;
	
	private Solo solo;
	
    
	public SomeOtherTest() {
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
}



