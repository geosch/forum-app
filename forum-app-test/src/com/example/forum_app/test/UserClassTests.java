package com.example.forum_app.test;

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;

import com.example.forum_app.MainActivity;
import com.example.forum_app.RegisterActivity;
import com.example.forum_app.User;

public class UserClassTests extends ActivityInstrumentationTestCase2<MainActivity>{

	public UserClassTests(Class<MainActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

	protected void setUp() throws Exception{
		super.setUp();
		//maybe need Robotium solo?
	}
	
	public void testCorrectUserObject() throws Throwable {
		int userid = 3;
		User instance = User.getInstance(userid);
		Assert.assertNotNull(instance);
		if(instance != null)
		{
			Assert.assertEquals(3, instance.getUserid());
			Assert.assertEquals("tester", instance.getNickname());
			Assert.assertEquals("Austria", instance.getCountry());
			Assert.assertEquals("tester@gmx.at", instance.getEmail());
		}
	}
}
