package model;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
	 * @return	0 if successful, 1 if filePath is invalid, 2 if photo already exists, 3 if file path does not specify an image file
	 * @throws IOException 
	 */
	public int addPhoto(String filePath) throws IOException {
		if(!isImageExtention(filePath)) {
			return 3;
		}
		for(int i = 0; i < this.photos.size(); i++) {
			if(this.photos.get(i).filePath.compareTo(filePath)== 0) {
				return 2;
			}
		}
		Photo photo = Photo.createPhoto(filePath, this);
		if(photo == null) {
			return 1;
		}
		
		//check whether there is the same photo in another album. If so, use addPhotoThroughLink
		for(int i = 0; i < this.user.albums.size(); i++) {
			Album temp = this.user.albums.get(i);
			if(temp.equals(this)) {
				continue;
			}
			for(int j = 0; j < temp.photos.size(); j++) {
				if(temp.photos.get(j).filePath.compareTo(filePath) == 0) {
					this.addPhotoThroughLink(temp.photos.get(j));
					return 0;
				}
			}
		}
		
		this.photos.add(photo);
		this.num_of_photos++;
		
		if(this.date_range[0] == null && this.date_range[1] == null) {
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
	public void addPhotoThroughLink(Photo given) {
		this.photos.add(given);
		this.num_of_photos++;
		if(this.date_range[0] == null && this.date_range[1] == null) {
			this.date_range[0] = given.datetime;
			this.date_range[1] = given.datetime;
		}
		else {
			if(this.date_range[0].isAfter(given.datetime)) {
				this.date_range[0] = given.datetime;
			}
			else if(this.date_range[1].isBefore(given.datetime)) {
				this.date_range[1] = given.datetime;
			}
		}
	}
	public boolean deletePhoto(int index) {
		if(index < 0 || index >= this.photos.size()) {
			return false;
		}
		this.photos.get(index).albums.remove(this);
		this.photos.remove(index);
		this.num_of_photos--;
		//apply change to date_range --> should Album just maintain a sorted list of photos?
		updateDateRange();
		return true;
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
	private boolean isImageExtention(String filePath) {
		String[] arr = filePath.split(Pattern.quote("."));
		String extension = arr[arr.length-1];
		extension.toLowerCase();
		
		String[] image_extensions = {"bmp","gif","jpg","jpeg","png"};
		for(int i = 0; i < image_extensions.length; i++) {
			if(extension.compareTo(image_extensions[i]) == 0) {
				return true;
			}
		}
		return false;
	}
	public void updateDateRange() {
		if(this.photos.size() == 0) {
			this.date_range = new LocalDateTime[2];
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
	}
	public ArrayList<Photo> searchByDate(String fromDate, String toDate){
		//TODO:
		return null;
	}
	public ArrayList<Photo> searchByTags(String tag1_name, String tag1_val, String tag2_name, String tag2_val, boolean isAnd){
		//TODO:
		return null;
	}
}
