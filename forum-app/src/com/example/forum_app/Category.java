package com.example.forum_app;

import java.io.Serializable;

public class Category implements Serializable {
	public Category(String name, int id, String description) {
		super();
		this.name = name;
		this.id = id;
		this.description = description;
	}

	private String name;
	private int id;
	private String description;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getDescription() {
		return description;
	}





	public void setDescription(String description) {
		this.description = description;
	}





	@Override
	public String toString() {
		return name;
	}

}
