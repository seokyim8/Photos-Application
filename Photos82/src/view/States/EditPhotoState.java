package view.States;

import javafx.event.ActionEvent;
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
	public PhotosState processEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		return null;
	}
	public static EditPhotoState getInstance() {
		if(EditPhotoState.currentState == null) {
			EditPhotoState.currentState = new EditPhotoState();
		}
		return EditPhotoState.currentState;
	}

}
