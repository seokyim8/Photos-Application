package model;

import java.io.IOException;
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
	
	/**
	 * Adds photo to the Album instance.
	 * 
	 * @param filePath
	 * @return	0 if successful, 1 if filePath was invalid, 2 if photo already exists 
	 * @throws IOException 
	 */
	public int addPhoto(String filePath) throws IOException {//need to apply change to date_range
		for(int i = 0; i < this.photos.size(); i++) {
			if(this.photos.get(i).filePath.compareTo(filePath)== 0) {
				return 2;
			}
		}
		Photo photo = Photo.createPhoto(filePath, this);
		if(photo == null) {
			return 1;
		}
		
		this.photos.add(photo);
		this.num_of_photos++;
		
		//apply change to date_range
		
		return 0;
	}
	public boolean deletePhoto(String filePath) {//need to apply change to date_range
		for(int i = 0; i < this.photos.size(); i++) {
			if(this.photos.get(i).filePath.compareTo(filePath)== 0) {
				this.photos.remove(i);
				this.num_of_photos--;
				//apply change to date_range

				
				return true;
			}
		}
		return false;
	}
	public boolean rename(String name) {
		for(int i = 0; i < this.user.albums.size(); i++) {
			if(this.user.albums.get(i).name.compareTo(name) == 0) {
				return false;
			}
		}
		this.name = name;
		return true;
	}
}
