package com.example.forum_app;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	private HttpURLConnection  conn;

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
     * Method creates a jdbc connection to a 
     * postgresql database
     * @return connection if true otherwise null
     */
	public HttpURLConnection createDatabaseConnection() {
		URL url;
		try {
			url = new URL("http://thomaskohl.bplaced.net/tttct/getAllUser.php");
			this.conn = (HttpURLConnection) url.openConnection();
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("DBConnection", "ERROR MalformedURLException " + e.getMessage());
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("DBConnection", "ERROR IOException " + e.getMessage());
		}

    	return conn;
	}
    
}
