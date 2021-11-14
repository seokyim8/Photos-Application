package model;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Album implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public int num_of_photos;
	public LocalDateTime[] date_range;
	public User user;
	public ArrayList<Photo> photos;
	
	public Album(String name, User user) {
		this.name = name;
		this.user = user;
		this.photos = new ArrayList<Photo>();
		this.num_of_photos = 0;
		this.date_range = new LocalDateTime[2];
	}
	
	/**
	 * Adds photo to the Album instance.
	 * 
	 * @param filePath
	 * @return	0 if successful, 1 if filePath was invalid, 2 if photo already exists 
	 * @throws IOException 
	 */
	public int addPhoto(String filePath) throws IOException {
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
		
		if(this.date_range == null) {
			this.date_range = new LocalDateTime[2];
			this.date_range[0] = photo.datetime;
			this.date_range[1] = photo.datetime;
		}
		else {
			if(this.date_range[0].isAfter(photo.datetime)) {
				this.date_range[0] = photo.datetime;
			}
			else if(this.date_range[1].isBefore(photo.datetime)) {
				this.date_range[1] = photo.datetime;
			}
		}
		
		return 0;
	}
	public boolean deletePhoto(String filePath) {
		for(int i = 0; i < this.photos.size(); i++) {
			if(this.photos.get(i).filePath.compareTo(filePath)== 0) {
				this.photos.remove(i);
				this.num_of_photos--;
				//apply change to date_range --> should Album just maintain a sorted list of photos?
				if(this.photos.size() == 0) {
					this.date_range = null;
				}
				else {
					LocalDateTime max = this.photos.get(0).datetime;
					LocalDateTime min = this.photos.get(0).datetime;
					for(int j = 1; j < this.photos.size(); j++) {
						LocalDateTime temp = this.photos.get(j).datetime;
						if(max.isBefore(temp)) {
							max = temp;
						}
						if(min.isAfter(temp)) {
							min = temp;
						}
					}
					this.date_range[0] = min;
					this.date_range[1] = max;
				}
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
