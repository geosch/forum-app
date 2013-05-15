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
import com.example.forum_app.R;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends Activity implements OnClickListener{

	private Button button1;
	private Button button2;
	private Button button3;
	
	private ProgressBar progressBar1;

	private EditText editText1;
	
	private String button_pressed;
	
    private String show_text_to_user;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		this.button1 = (Button) this.findViewById(R.id.button1);
		this.button2 = (Button) this.findViewById(R.id.button2);
		this.button3 = (Button) this.findViewById(R.id.button3);
		
		this.editText1 = (EditText) this.findViewById(R.id.editText1);
		
		this.progressBar1 = (ProgressBar) this.findViewById(R.id.progressBar1);
		
		this.button1.setOnClickListener(this);
		this.button2.setOnClickListener(this);
		this.button3.setOnClickListener(this);
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
    
    @Override
	public void onClick(View v) {

		Button clicked = (Button) v;

		String nextCipherFromUser = (String) clicked.getText();

		Log.d("TAG", "Button: " + clicked.getText() + ", clicked!");

		if (nextCipherFromUser.equals("Button1")) {
			Log.d("TAG", "Button1");
			
			this.button_pressed = "First Button";
			this.editText1.setText(this.button_pressed);
		}
		else if (nextCipherFromUser.equals("Button2")) {
			Log.d("TAG", "Button2");
			
			this.button_pressed = "Second Button";
			this.editText1.setText(this.button_pressed);
		}
		else if (nextCipherFromUser.equals("Button3")) {
			Log.d("TAG", "Button3");
			
			this.button_pressed = "Third Button";
			this.editText1.setText(this.button_pressed);
		}
    }
}
