package view.States;

import model.Album;
import model.Photo;
import model.User;

public abstract class PhotosState {
	protected User user;
	protected Album album;
	protected Photo photo;
	
	public abstract void enter();
	public abstract void processEvent();
}
