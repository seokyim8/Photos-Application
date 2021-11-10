package model;

import java.util.ArrayList;

public class User{
	String username;
	ArrayList<String> tagnames;
	ArrayList<Album> albums;

	public User(String username) {
		this.username = username;
		this.tagnames = new ArrayList<String>();
		this.albums = new ArrayList<Album>();
		
		this.tagnames.add("location");
		this.tagnames.add("person");
	}
	public void createAlbum() {//add to UML?
		
	}
	public void deleteAlbum() {
		
	}
	public void createTag() {
		
	}
	public void deleteTag() {
		
	}
}
