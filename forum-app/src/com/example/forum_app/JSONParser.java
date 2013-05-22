package com.example.forum_app;

/**
 * following code from http://www.androidhive.info/2012/05/how-to-connect-android-with-php-mysql/
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.util.Log;
 
public class JSONParser {
 
    static InputStreamReader is = null;
    List<JSONObject> queryResult = null;
    // constructor
    public JSONParser() {
 
    }
 
    // function get json from url
    // by making HTTP POST or GET mehtod
    public List<JSONObject> makeHttpRequest(String url, String method,
            List<NameValuePair> params) {
 
        // Making HTTP request
        try {
 
            // check for request method
            if(method == "POST"){
            	
            	HttpURLConnection connect = null;
            	URL connectionUrl = new URL(url);
            	
            	connect = (HttpURLConnection) connectionUrl.openConnection();
            	connect.setRequestMethod("POST");
            	
            	connect.setRequestProperty("Content-Length", Integer.toString(params.get(0).getName().length() + params.get(0).getValue().length() + 2));
            	connect.setDoOutput(true);
            	DataOutputStream wr = new DataOutputStream(connect.getOutputStream());
            	wr.writeBytes("&" + params.get(0).getName() + "=" + params.get(0).getValue());
            	wr.flush();
            	wr.close();
            	
            	is = new InputStreamReader(connect.getInputStream());
            	
            	if (is != null)
            	{
            		Log.d("DBConnection", "inputstreamreader != null");
            	}
 
            }else if(method == "GET"){
            	
            	HttpURLConnection connect = null;
            	URL connectionUrl = new URL(url);
            	
            	connect = (HttpURLConnection) connectionUrl.openConnection();
            	
            	is = new InputStreamReader(connect.getInputStream());
            }           
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        try {
            BufferedReader reader = new BufferedReader(is);
            StringBuilder sb = new StringBuilder();
            String line = null;
            
            this.queryResult = new ArrayList<JSONObject>();
            while ((line = reader.readLine()) != null) 
            {
            	Log.d("DBConnection", line);
                this.queryResult.add(new JSONObject(line));
            }
            is.close();
        } 
        
        catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

 
        // return JSON String
        return this.queryResult;
 
    }
}
