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
	String filePath;
	String caption;
	LocalDateTime datetime;
	ArrayList<Album> albums;
	ArrayList<Tag> tags;
	
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
}
