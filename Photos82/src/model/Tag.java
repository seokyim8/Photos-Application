package model;

import java.io.Serializable;

public class Tag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public String value;
	public Photo photo;
	
	public Tag(String name, String value, Photo photo) {
		this.name = name;
		this.value = value;
		this.photo = photo;
	}
	
	public String getName() {
		return this.name;
	}
	public String getValue() {
		return this.value;
	}
	
	public String toString() {
		return this.name + ": " + this.value;
	}
	public boolean equals(Object o) {
		if(o != null && o instanceof Tag) {
			Tag tag = (Tag)o;
			if(this.name == null && tag.name != null) {
				return false;
			}
			if(this.name != null && tag.name == null) {
				return false;
			}
			if(this.value == null && tag.value != null) {
				return false;
			}
			if(this.value != null && tag.value == null) {
				return false;
			}
			return this.name.compareTo(tag.name) == 0 && this.value.compareTo(tag.value) == 0;
		}
		return false;
	}
}
