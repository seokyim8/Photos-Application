package view.States;

import javafx.event.ActionEvent;
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
	public void enter() {
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
