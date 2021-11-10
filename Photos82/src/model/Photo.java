package model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class Photo {
	String filePath;
	String caption;
	LocalDate date;
	ArrayList<Album> albums;
	ArrayList<Tag> tags;
	
	public Photo(String filePath) {
		this.filePath = filePath;
		ZoneId zid = ZoneId.of("America/New_York");
		this.date = LocalDate.now(zid);
		this.albums = new ArrayList<Album>();
		this.tags = new ArrayList<Tag>();
		this.caption = "";
	}
	public boolean addTag(String name, String value) {
		for(int i = 0; i < this.tags.size(); i++) {
			if(this.tags.get(i).name.compareTo(name) == 0 
					&& this.tags.get(i).value.compareTo(value) == 0) {
				return false;//tag name + value combination has to be unique for a photo
			}
		}
		Tag tag = new Tag(name,value);
		this.tags.add(tag);
		return true;
	}
}
