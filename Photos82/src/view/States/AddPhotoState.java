package view.States;

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
	public void enter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processEvent() {
		// TODO Auto-generated method stub
		
	}
	public static AddPhotoState getInstance() {
		if(AddPhotoState.currentState == null) {
			AddPhotoState.currentState = new AddPhotoState();
		}
		return AddPhotoState.currentState;
	}
}
