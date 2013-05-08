package com.example.forum_app.test;


import junit.framework.Assert;
import android.test.AndroidTestCase;


public class SomeTest extends AndroidTestCase {

    public void testSomething() throws Throwable {
       Assert.assertTrue(( 1 + 1 == 2));
    }
	
    public void testSomethingElse() throws Throwable {
       Assert.assertTrue(1 + 1 == 2);
    }
        
}

