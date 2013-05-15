package com.example.forum_app.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import com.example.forum_app.*;

import android.test.ActivityInstrumentationTestCase2;
import android.util.JsonWriter;
import android.util.Log;
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

}