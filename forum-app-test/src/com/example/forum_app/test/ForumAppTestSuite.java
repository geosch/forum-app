package com.example.forum_app.test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@RunWith(Suite.class)

@SuiteClasses({AllTests.class, DBConnectionTest.class, ForumViewButtonTest.class, ForumViewListsTest.class, 
			   LoginTest.class, RegistryCheckInputTest.class, RegistryInsertToDatabaseTest.class, UserClassTest.class})
public class ForumAppTestSuite {
	
	
}
