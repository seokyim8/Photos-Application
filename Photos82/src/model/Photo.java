package model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class Photo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String filePath;
	public String caption;
	public LocalDateTime datetime;
	public ArrayList<Album> albums;
	public ArrayList<Tag> tags;
	
	private Photo(String filePath, Album album) throws IOException {
		this.filePath = filePath;
		FileTime filetime = Files.getLastModifiedTime(new File(filePath).toPath());
		this.datetime = LocalDateTime.ofInstant(filetime.toInstant(), ZoneId.of("America/New_York"));
		this.albums = new ArrayList<Album>();
		this.tags = new ArrayList<Tag>();
		this.caption = "";
		this.albums.add(album);
	}
	public static Photo createPhoto(String filePath, Album album) throws IOException {
		File file = new File(filePath);
		if(file.exists()) {
			return new Photo(filePath,album);
		}
		return null;
	}
	public boolean addTag(String name, String value) {
		for(int i = 0; i < this.tags.size(); i++) {
			if(this.tags.get(i).name.compareTo(name) == 0 
					&& this.tags.get(i).value.compareTo(value) == 0) {
				return false;//tag name + value combination has to be unique for a photo
			}
		}
		Tag tag = new Tag(name,value,this);
		this.tags.add(tag);
		return true;
	}
	public boolean deleteTag(String name, String value) {
		for(int i = 0; i < this.tags.size(); i++) {
			if(this.tags.get(i).name.compareTo(name) == 0 
					&& this.tags.get(i).value.compareTo(value) == 0) {
				this.tags.remove(i);
				return true;
			}
		}
		return false;
	}
	public void modifyCaption(String caption) {
		this.caption = caption;
	}
	public int copyToAlbum(String album_name, ArrayList<Album> given_albums) {
		for(int i = 0; i < given_albums.size(); i++) {
			if(given_albums.get(i).name.compareTo(album_name) == 0) {
				Album temp = given_albums.get(i);
				for(int j = 0; j < temp.photos.size(); j++) {
					if(temp.photos.get(j).equals(this)) {
						return 1;
					}
				}
				temp.addPhotoThroughLink(this);
				this.albums.add(temp);
				return 0;
			}
		}
		return 2;
	}
	public int moveToAlbum(String album_name, Album tbr_album, ArrayList<Album> given_albums) {
		for(int i = 0; i < given_albums.size(); i++) {
			if(given_albums.get(i).name.compareTo(album_name) == 0) {
				Album temp = given_albums.get(i);
				for(int j = 0; j < temp.photos.size(); j++) {
					if(temp.photos.get(j).equals(this)) {
						return 1;
					}
				}
				
				temp.addPhotoThroughLink(this);
				this.albums.add(temp);
				
				tbr_album.photos.remove(this);
				tbr_album.num_of_photos--;
				tbr_album.updateDateRange();
				this.albums.remove(tbr_album);
				return 0;
			}
		}
		return 2;
	}
}
