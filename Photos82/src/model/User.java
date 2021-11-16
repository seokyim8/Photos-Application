package model;

import java.io.Serializable;
import java.time.LocalDate;
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
	public boolean addTagType(String name) {
		for(int i = 0; i < this.tagnames.size(); i++) {
			if(name.compareTo(this.tagnames.get(i)) == 0) {//duplicate tag type found
				return false;
			}
		}
		this.tagnames.add(name);
		return true;
	}
	public ArrayList<Photo> searchByDate(String fromDate, String toDate){//assumes dates are parsable
		ArrayList<Photo> tbr = new ArrayList<Photo>();
		for(int i = 0; i < this.albums.size(); i++) {
			Album album = this.albums.get(i);
			for(int j = 0; j < album.photos.size(); j++) {
				Photo photo = album.photos.get(j);
				if(!tbr.contains(photo)) {
					LocalDate from_date = LocalDate.parse(fromDate);
					LocalDate to_date = LocalDate.parse(toDate);
					if((photo.datetime.toLocalDate().compareTo(from_date) == 0 || photo.datetime.toLocalDate().isAfter(from_date))
							&& (photo.datetime.toLocalDate().compareTo(to_date) == 0 || photo.datetime.toLocalDate().isBefore(to_date))) {
						tbr.add(photo);
					}
				}
			}
		}
		return tbr;
	}
	public ArrayList<Photo> searchByTags(String tag1_name, String tag1_val, String tag2_name, String tag2_val, boolean isAnd){
		Tag t1 = null, t2 = null;
		if(tag1_name.compareTo("") != 0 && tag1_val.compareTo("") != 0) {
			t1 = new Tag(tag1_name,tag1_val,null);
		}
		if(tag2_name.compareTo("") != 0 && tag2_val.compareTo("") != 0) {
			t2 = new Tag(tag2_name,tag2_val,null);
		}
		ArrayList<Tag> tag_arr = new ArrayList<>();
		if(t1 != null) {
			tag_arr.add(t1);
		}
		if(t2 != null) {
			tag_arr.add(t2);
		}
			
		ArrayList<Photo> tbr = new ArrayList<Photo>();
		for(int i = 0; i < this.albums.size(); i++) {
			Album album = this.albums.get(i);
			for(int j = 0; j < album.photos.size(); j++) {
				Photo photo = album.photos.get(j);
				if(!tbr.contains(photo)) {
					if(tag_arr.isEmpty()) {
						tbr.add(photo);
						continue;
					}
					if(isAnd) {
						boolean satisfies = true;
						for(int k = 0; k < tag_arr.size(); k++) {
							if(!photo.tags.contains(tag_arr.get(k))) {
								satisfies = false;
								break;
							}
						}
						if(satisfies) {
							tbr.add(photo);
						}
					}
					else {//isOr
						for(int k = 0; k < tag_arr.size(); k++) {
							if(photo.tags.contains(tag_arr.get(k))) {
								tbr.add(photo);
								break;
							}
						}
					}
				}
			}
		}
		return tbr;
	}
}
