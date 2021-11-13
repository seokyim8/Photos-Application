package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AdminController implements Controller{
	MainController main_controller;
	
	@FXML Button delete_button;
	@FXML Button add_button;
	@FXML Button log_out_button;
	@FXML Button quit_button;
	@FXML ListView<String> user_listview;
	@FXML TextField add_textfield;

	public void setup(MainController mc) {
		this.main_controller = mc;
	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
