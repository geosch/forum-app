package com.example.forum_app;

import java.util.List;

import org.json.JSONObject;

import android.util.Log;

public class User {

	private static User instance = null;
	private static int userid;
	private static String nickname;
	private static String country;
	private static String email;
	private static List<JSONObject> json;
	private DBOperator db = DBOperator.getInstance();
	
	private User(){};
	
	private User(int userid){
		String query = "SELECT * FROM forumuser WHERE userid = '" + userid + "'";
		
		try
		{
			User.userid = userid;
			User.json = db.sendQuery(query);
            User.nickname = json.get(0).getString("nickname");
            User.country = json.get(0).getString("country");
            User.email = json.get(0).getString("email");
		}
		catch(Exception ex)
		{
            Log.d("User: ", ex.getMessage());
		}
	};
	
	public static User getInstance() {
			return instance;
    }
	
	public static User createInstance(int userid) {
	    if (instance == null) {
            instance = new User(userid);
        }
        return instance;
	}
	
	public String getNickname() {
		return User.nickname;
	}
	
	public String getEmail() {
		return User.email;
	}
	
	public String getCountry() {
		return User.country;
	}
	
	public int getUserid() {
		return User.userid;
	}
	
	

}
