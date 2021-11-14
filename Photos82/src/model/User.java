package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String username;
	public ArrayList<String> tagnames;
	public ArrayList<Album> albums;

	public User(String username) {
		this.username = username;
		this.tagnames = new ArrayList<String>();
		this.albums = new ArrayList<Album>();
		
		this.tagnames.add("location");
		this.tagnames.add("person");
	}
	public boolean createAlbum(String name) {//add to UML?
		for(int i = 0; i < this.albums.size(); i++) {
			if(this.albums.get(i).name.compareTo(name) == 0) {
				return false;//album names unique for each user
			}
		}
		Album album = new Album(name,this);
		this.albums.add(album);
		return true;
		
	}
	public boolean deleteAlbum(String name) {
		for(int i = 0; i < this.albums.size(); i++) {
			if(this.albums.get(i).name.compareTo(name) == 0) {
				this.albums.remove(i);
				return true;
			}
		}
		return false;
	}
	public boolean createTag(String name) {
		for(int i = 0; i < this.tagnames.size(); i++) {
			if(this.tagnames.get(i).compareTo(name) == 0) {
				return false;
			}
		}
		this.tagnames.add(name);
		return true;
	}
	public boolean deleteTag(String name) {
		for(int i = 0; i < this.tagnames.size(); i++) {
			if(this.tagnames.get(i).compareTo(name) == 0) {
				this.tagnames.remove(i);
				return true;
			}
		}
		return false;
	}
}
