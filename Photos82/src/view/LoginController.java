package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController implements Controller{
	MainController main_controller;
	
	public @FXML Button login_button;
	public @FXML Button quit_button;
	public @FXML TextField username_textfield;
	
	public void setup(MainController mc) {
		this.main_controller = mc;
	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
