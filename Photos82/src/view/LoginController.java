package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController implements Controller{
	MainController main_controller;
	
	@FXML Button login_button;
	@FXML Button quit_button;
	@FXML TextField username_textfield;
	
	public void setup(MainController mc) {
		this.main_controller = mc;
		
		login_button.setOnAction(e->processEvent(e));
		quit_button.setOnAction(e->processEvent(e));
	}
	
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
