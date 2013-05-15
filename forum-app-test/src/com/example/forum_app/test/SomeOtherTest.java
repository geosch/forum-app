package com.example.forum_app.test;

import com.example.forum_app.*;

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;
//import android.test.AndroidTestCase;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//public class SomeOtherTest extends AndroidTestCase{

public class SomeOtherTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private MainActivity mActivity;
	private Button button1;
	private EditText textField;
	
    @SuppressWarnings("deprecation")
	public SomeOtherTest() {
        super("com.example.forum_app", MainActivity.class);
        
        
    }	
    @Override
    protected void setUp() throws Exception{     
    
    	mActivity = this.getActivity();
    	button1 = (Button) mActivity.findViewById(com.example.forum_app.R.id.button1);
    }
	
	
    public void testSomeOtherThing() throws Throwable {
    	mActivity.runOnUiThread(new Runnable() {
        	
    		@Override
    		public void run(){
        		button1.requestFocus();
        		button1.performClick();
        		textField = (EditText) mActivity.findViewById(com.example.forum_app.R.id.editText1); 
        	}    		
    	});
    	
    

    //String text = (String)textField.getText();
       	
    	
    	
    //Assert.assertEquals("FirstButton", text);
    Assert.assertTrue(false);
    
    
    
    }
}



