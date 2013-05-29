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

public class ForumViewListsTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private MainActivity mActivity;
	private List<JSONObject> newest_posts;
	private List<JSONObject> categories;
	
    
	public ForumViewListsTest() {
        super(MainActivity.class);
        
        
    }	
    @Override
    protected void setUp() throws Exception{     
    	mActivity = getActivity();    	
		newest_posts = mActivity.getJsonNewestPosts();
		categories = mActivity.getJsonCategories();
		
    }
    
    @Override
    protected void tearDown() {
    	mActivity.finish();
    }

	

    public void testJSONParser() throws Throwable {

		Assert.assertNotNull(newest_posts);
		Assert.assertNotNull(categories);
		
    }
    
    public void testCorrectNumberinNewestPosts() throws Throwable {
    	int max = mActivity.getMaxNumberNewesPosts();
    	int size = newest_posts.size();
    	Assert.assertTrue(size <= max);
    }
	
}
