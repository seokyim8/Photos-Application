package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginController implements Controller{
	MainController main_controller;
	
	@FXML Button login_button;
	public void setup(MainController mc) {
		this.main_controller = mc;
		
		login_button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processEvent(e);
			}
			
		});
	}
	
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
