package view.States;

import javafx.event.ActionEvent;
import view.MainController;

public class AdminState extends PhotosState{
	private static AdminState currentState;
	private AdminState() {
		
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
	
	public static AdminState getInstance() {
		if(AdminState.currentState == null) {
			AdminState.currentState = new AdminState();
		}
		return AdminState.currentState;
	}
}
