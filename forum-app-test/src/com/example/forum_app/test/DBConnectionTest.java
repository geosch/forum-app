package com.example.forum_app.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import com.example.forum_app.*;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import junit.framework.Assert;

public class DBConnectionTest extends ActivityInstrumentationTestCase2<MainActivity> {

	@SuppressWarnings("deprecation")
	public DBConnectionTest() {
		super("com.example.forum-app.",MainActivity.class);
		// TODO Auto-generated constructor stub
	}

    @Override
    protected void setUp() throws Exception{
    	super.setUp();
    	this.getActivity();
    }
    
    /**
     * testDbConnection
     * Method tests a connection to the php service
     * which returns an List which represents the rows
     * of the answer.
     * @throws Throwable
     */
	public void testDbConnection() throws Throwable {
	    
		// JSON parser class
	    JSONParser jsonParser = new JSONParser();
	    
	    String url = "http://forumapp.heliohost.org/DBConnectionService.php";
		
		String query = "select userid from forumuser where userid=1";
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("query", query));
		
		// getting product details by making HTTP request
        List<JSONObject> json = jsonParser.makeHttpRequest(
                url, "POST", params);
        
        Log.d("DBConnection", "Json output:");
        Log.d("DBConnection", json.toString());
		
		Assert.assertEquals(json.get(0).getInt("userid"), 1); 
	}
	
	/**
     * testSendQuery
     * Method tests if the method which send query 
     * to the service works.
     * @throws Throwable
     */
	public void testSendQuery() throws Throwable {
	    
		String query = "Select * from forumuser";
		//List<JSONObject> json = sendQuery(query);
		Activity activity = getActivity();
		List<JSONObject> json = ((MainActivity) activity).sendQuery(query);
		Assert.assertEquals(json.get(0).getInt("userid"), 1);
	}

}