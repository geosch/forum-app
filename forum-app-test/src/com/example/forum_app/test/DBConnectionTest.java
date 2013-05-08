package com.example.forum_app.test;


import java.sql.Connection;
import com.example.forum_app.*;
import android.test.ActivityInstrumentationTestCase2;
import junit.framework.Assert;

public class DBConnectionTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private MainActivity mActivity;
	
    @SuppressWarnings("deprecation")
	public DBConnectionTest() {
		super("com.example.forum-app.",MainActivity.class);
		// TODO Auto-generated constructor stub
	}

    @Override
    protected void setUp() throws Exception{
    	super.setUp();
    	mActivity = this.getActivity();
    }
    
	public void testDbConnection() throws Throwable {
		
		Connection conn = this.mActivity.createDatabaseConnection();
    	boolean test = false;
    	if(conn.isValid(2))
    		test = true;
    	conn.close();
    	Assert.assertTrue(test);
    }

}