package view.States;

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
	public void processEvent() {
		// TODO Auto-generated method stub
		
	}
	
	public static AdminState getInstance() {
		if(AdminState.currentState == null) {
			AdminState.currentState = new AdminState();
		}
		return AdminState.currentState;
	}
}
