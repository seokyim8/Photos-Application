package view.States;

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
	public void processEvent() {
		// TODO Auto-generated method stub
		
	}
	public static HomeState getInstance() {
		if(HomeState.currentState == null) {
			HomeState.currentState = new HomeState();
		}
		return HomeState.currentState;
	}

}
