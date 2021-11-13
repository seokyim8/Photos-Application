package view.States;

import view.MainController;

public class EditPhotoState extends PhotosState{
	private static EditPhotoState currentState;
	private EditPhotoState() {
		
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
	public static EditPhotoState getInstance() {
		if(EditPhotoState.currentState == null) {
			EditPhotoState.currentState = new EditPhotoState();
		}
		return EditPhotoState.currentState;
	}

}
