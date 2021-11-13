package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddPhotoController implements Controller{
	MainController main_controller;
	
	@FXML Button add_button;
	@FXML Button cancel_button;
	@FXML Button quit_button;
	@FXML TextField caption_textfield;
	@FXML TextField filepath_textfield;
	
	
	public void setup(MainController mc) {
		this.main_controller = mc;
	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
