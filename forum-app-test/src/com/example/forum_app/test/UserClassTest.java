package com.example.forum_app.test;

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;
import com.example.forum_app.MainActivity;
import com.example.forum_app.User;

public class UserClassTest extends ActivityInstrumentationTestCase2<MainActivity>{

	public UserClassTest() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	@Override
	public void tearDown() throws Exception {
	}

	
	public void testCorrectUserObject() throws Throwable {
		int userid = 3;
		User instance = User.createInstance(userid);
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
