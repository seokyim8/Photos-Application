package view.States;

import javafx.event.ActionEvent;
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
	public PhotosState processEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		return null;
		
	}
	public static LoginState getInstance() {
		if(LoginState.currentState == null) {
			LoginState.currentState = new LoginState();
		}
		return LoginState.currentState;
	}

}
