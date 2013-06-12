package com.example.forum_app.test;

import java.util.List;

import junit.framework.Assert;

import org.json.JSONObject;
import android.test.ActivityInstrumentationTestCase2;
import com.example.forum_app.MainActivity;
import com.example.forum_app.ThreadsActivity;
import com.jayway.android.robotium.solo.Solo;

public class ForumViewListsTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;

	private MainActivity mActivity;
	private List<JSONObject> newest_posts;
	private List<JSONObject> categories;
	
	
	public ForumViewListsTest() {
        super(MainActivity.class);
       
    }	
    @Override
    protected void setUp() throws Exception{     
    	mActivity = getActivity();
    	solo = new Solo(getInstrumentation(), mActivity);
		newest_posts = mActivity.getJsonNewestPosts();
		categories = mActivity.getJsonCategories();
		
		String name = mActivity.getCategoriesObj().toString();
		System.out.println(name);
		
    }
    
    @Override
    protected void tearDown() {
    	solo.finishOpenedActivities();
    	// mActivity.finish();
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
	
    public void testCategoriesButtons() throws Throwable {
    	
    	String name = mActivity.getCategoriesObj().get(0).getName();
        solo.waitForActivity("MainActivity");
    	solo.clickOnText(name);
		solo.waitForActivity("ThreadsActivity");
		Assert.assertNotNull(solo.getCurrentActivity());
		Assert.assertEquals(ThreadsActivity.class, solo.getCurrentActivity().getClass());   
    }
}
