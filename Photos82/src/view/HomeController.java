package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HomeController implements Controller{
	MainController main_controller;
	
	@FXML Button create_album_button;
	@FXML Button search_photos_button;
	@FXML Button open_album_button;
	@FXML Button delete_album_button;
	@FXML Button log_out_button;
	@FXML Button quit_button;
	@FXML TextField create_album_textfield;
	@FXML ListView<String> albums_listview;

	
	public void setup(MainController mc) {
		this.main_controller = mc;
	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
