package model;

import java.util.ArrayList;

public class Album {
	String name;
	int num_of_photos;
	String date_range;
	User user;
	ArrayList<Photo> photos;
	
	public Album(String name, User user) {
		this.name = name;
		this.user = user;
		this.photos = new ArrayList<Photo>();
	}
	
	public void addPhoto() {
		
	}
	public void deletePhoto() {
		
	}
}
