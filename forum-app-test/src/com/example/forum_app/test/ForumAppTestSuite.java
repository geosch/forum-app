package com.example.forum_app.test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@RunWith(Suite.class)
@SuiteClasses({SomeTest.class, SomeOtherTest.class, RegistryInsertToDatabaseTest.class, RegistryCheckInputTest.class, DBConnectionTest.class})
public class ForumAppTestSuite {
	
	
}
