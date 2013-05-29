package com.example.forum_app.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.ActivityInstrumentationTestCase2;

import com.example.forum_app.MainActivity;
import com.example.forum_app.User;

public class UserClassTest extends ActivityInstrumentationTestCase2<MainActivity>{

	public UserClassTest(Class<MainActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
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
