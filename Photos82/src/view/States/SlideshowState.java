package view.States;

import javafx.event.ActionEvent;
import model.Admin;
import model.Album;
import model.Photo;
import model.User;
import view.MainController;

public class SlideshowState extends PhotosState{
	private static SlideshowState currentState;
	private SlideshowState() {
		
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

	public static SlideshowState getInstance() {
		if(SlideshowState.currentState == null) {
			SlideshowState.currentState = new SlideshowState();
		}
		return SlideshowState.currentState;
	}
}
