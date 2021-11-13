package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AlbumController implements Controller{
	MainController main_controller;
	
	@FXML Button rename_album_button;
	@FXML Button home_button;
	@FXML Button slideshow_button;
	@FXML Button add_photo_button;
	@FXML Button remove_photo_button;
	@FXML Button edit_photo_button;
	@FXML Button log_out_button;
	@FXML Button quit_button;
	@FXML TextField rename_album_textfield;
	@FXML Text album_info_text;
	@FXML ListView<String> photos_list;

	
	public void setup(MainController mc) {
		this.main_controller = mc;

	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
