package view.States;

import javafx.event.ActionEvent;
import model.Admin;
import model.Album;
import model.Photo;
import model.User;
import view.MainController;

public class AlbumState extends PhotosState{
	private static AlbumState currentState;
	private AlbumState() {
		
	}
	
	@Override
	public void setup(MainController mc) {
		this.main_controller = mc;
	}
	@Override
	public void enter(Admin admin, User user, Album album, Photo photo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhotosState processEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		return null;
		
	}
	public static AlbumState getInstance() {
		if(AlbumState.currentState == null) {
			AlbumState.currentState = new AlbumState();
		}
		return AlbumState.currentState;
	}

}
