package com.example.forum_app;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * sendQuery
     * @param query holds string with full SQL query
     * @return is a list where every item equals one row from query answer
     */
    public List<JSONObject> sendQuery(String query){
    	
    	// JSON parser class
	    JSONParser jsonParser = new JSONParser();
	    
	    String url = "http://forumapp.heliohost.org/DBConnectionService.php";
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("query", query));
		
		// getting product details by making HTTP request
        List<JSONObject> json = jsonParser.makeHttpRequest(
                url, "POST", params);
        
    	return json;
    }
    
}
