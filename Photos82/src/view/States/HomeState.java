package view.States;

import javafx.event.ActionEvent;
import model.Admin;
import model.Album;
import model.Photo;
import model.User;
import view.MainController;

public class HomeState extends PhotosState{
	private static HomeState currentState;
	private HomeState() {
		
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
	public static HomeState getInstance() {
		if(HomeState.currentState == null) {
			HomeState.currentState = new HomeState();
		}
		return HomeState.currentState;
	}

}
