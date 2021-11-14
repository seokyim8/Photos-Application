package model;

import java.io.Serializable;

public class Tag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String value;
	Photo photo;
	
	public Tag(String name, String value, Photo photo) {
		this.name = name;
		this.value = value;
		this.photo = photo;
	}
}
