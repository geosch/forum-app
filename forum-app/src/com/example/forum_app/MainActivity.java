package com.example.forum_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// afterwards android should allow db requests
        if (android.os.Build.VERSION.SDK_INT >= 9) {
        	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);
        	}
        
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
     * Method creates a jdbc connection to a 
     * postgresql database
     * @return connection if true otherwise null
     */
	public Connection createDatabaseConnection() {
    	Connection conn = null;
		Log.d("DBConnection", "Start create conection");
		// load jdbc postgresql driver
    	try {
			Class.forName("org.postgresql.Driver");
		
	    	// create connection
	    	String url = "jdbc:postgresql://stevie.heliohost.org:5432/forumapp_database";
	    	
			Log.d("DBConnection", "Host url: " + url);
			
			conn = DriverManager.getConnection(url,"forumapp","12345678");
			Log.d("DBConnection", "Connection started ... " + conn.isClosed());
			return conn;
		
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			Log.d("DBConnection", "ERROR: Catch SQL Exception");
			Log.d("DBConnection", e.getMessage() + " " + e.getErrorCode() + " " + e.getClass().getName());
			
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			Log.d("DBConnection", "ERROR: Catch Class Not Found");
			return null;
		}
	}
    
}
