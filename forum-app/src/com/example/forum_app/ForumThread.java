package com.example.forum_app;

import java.io.Serializable;

public class ForumThread implements Serializable {
	public ForumThread(int id, String subject) {
		super();
		this.subject = subject;
		this.id = id;
	}

	private String subject;
	private int id;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	


	public String getSubject() {
		return subject;
	}






	public void setSubject(String subject) {
		this.subject = subject;
	}






	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
	}






	@Override
	public String toString() {
		return this.subject;
	}

}