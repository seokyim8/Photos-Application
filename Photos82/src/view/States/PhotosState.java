package view.States;

import javafx.event.ActionEvent;
import model.Admin;
import model.Album;
import model.Photo;
import model.User;
import view.MainController;

public abstract class PhotosState {
	protected MainController main_controller;

	protected Admin admin;
	protected User user;
	protected Album album;
	protected Photo photo;
	
	public abstract void setup(MainController mc);
	public abstract void enter(Admin admin, User user, Album album, Photo photo);
	public abstract PhotosState processEvent(ActionEvent e);
}
