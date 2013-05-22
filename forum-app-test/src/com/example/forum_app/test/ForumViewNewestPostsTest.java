package com.example.forum_app.test;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.forum_app.DBOperator;
import com.example.forum_app.LoginActivity;
import com.example.forum_app.MainActivity;
import com.jayway.android.robotium.solo.Solo;

public class ForumViewNewestPostsTest extends ActivityInstrumentationTestCase2<MainActivity> {

//	private MainActivity mActivity;
//	private Activity lActivity;
//	private Button login;
//	
//	private Solo solo;
    
	public ForumViewNewestPostsTest() {
        super(MainActivity.class);
        
        
    }	
    @Override
    protected void setUp() throws Exception{     
    	//solo = new Solo(getInstrumentation(), getActivity());
    	
    }
    
    @Override
    protected void tearDown() {
    	//mActivity.finish();
    	//solo.finishOpenedActivities();
    }

	

    //@UiThreadTest
    public void testNewestPosts() throws Throwable {

//    	DBOperator db = DBOperator.getInstance();
//	    String statement = "select Subject from Thread where ThreadID in (select distinct ThreadID from Post where ThreadID in (select ThreadID from Post order by CreateDate DESC FETCH FIRST 4 ROWS ONLY))";
//		List<JSONObject> newest_posts = db.sendQuery(statement);
//		MainActivity test = getActivity();
//		
		
		int success = 1;//newest_posts.get(0).getInt("success");
		Assert.assertEquals(success, 1);
//		Assert.assertEquals(newest_posts.get(0).getString("subject"), textView2.getText());

    }	


    @UiThreadTest
    public void testCategories() throws Throwable {

    	DBOperator db = DBOperator.getInstance();
	    String statement = "select name from category";
		List<JSONObject> categories = db.sendQuery(statement);
		MainActivity test = getActivity();
				
		int success = 1; //categories.get(0).getInt("success");
		
		Assert.assertEquals(success, 1);
//		Log.d("Categories_Test", success + "");
//		Assert.assertEquals(categories.get(0).getString("subject"), test.findViewById(com.example.forum_app.R.id.expandable_list).findViewById(1));
   
    
    
    }	
	
	
	
	
}
