package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AdminController implements Controller{
	MainController main_controller;
	
	public @FXML Button delete_button;
	public @FXML Button add_button;
	public @FXML Button log_out_button;
	public @FXML Button quit_button;
	public @FXML ListView<String> user_listview;
	public @FXML TextField add_textfield;

	public void setup(MainController mc) {
		this.main_controller = mc;
	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
