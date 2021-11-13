package view.States;

import javafx.event.ActionEvent;
import model.Admin;
import model.Album;
import model.Photo;
import model.User;
import view.MainController;

public class AddPhotoState extends PhotosState{
	private static AddPhotoState currentState;
	private AddPhotoState() {
		
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
	public static AddPhotoState getInstance() {
		if(AddPhotoState.currentState == null) {
			AddPhotoState.currentState = new AddPhotoState();
		}
		return AddPhotoState.currentState;
	}
}
