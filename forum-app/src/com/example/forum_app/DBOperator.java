package com.example.forum_app;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.util.Log;

public class DBOperator {
 
	private static String url;
	
	private static DBOperator instance = null;
	
	private DBOperator() {
		url = "http://forumapp.heliohost.org/DBConnectionService.php";
	}
	
	public static DBOperator getInstance() {
        if (instance == null) {
            instance = new DBOperator();
        }
        return instance;
    }
    //List<JSONObject> queryResult = null;
    // constructor
    
	private List<JSONObject> jsonHttpRequest(String statement, String type)
	{
		// JSON parser class
		JSONParser jsonParser = new JSONParser();
	    
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("query", statement));
		
		// getting product details by making HTTP request
        List<JSONObject> json = jsonParser.makeHttpRequest(
                url, "POST", params);
        
    	return json;
	}
    
    public List<JSONObject> sendQuery(String statement)
    {
    	return this.jsonHttpRequest(statement, "query");
    }
    
    public List<JSONObject> sendInsert(String statement)
    {
    	return this.jsonHttpRequest(statement, "insert");
    }
    
    public List<JSONObject> sendUpdate(String statement)
    {
    	return this.jsonHttpRequest(statement, "update");
    }
    
    public List<JSONObject> sendDelete(String statement)
    {
    	return this.jsonHttpRequest(statement, "delete");
    }

}
