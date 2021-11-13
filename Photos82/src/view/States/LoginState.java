package view.States;

import view.MainController;

public class LoginState extends PhotosState{
	private static LoginState currentState;
	private LoginState() {
		
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
	public static LoginState getInstance() {
		if(LoginState.currentState == null) {
			LoginState.currentState = new LoginState();
		}
		return LoginState.currentState;
	}

}
