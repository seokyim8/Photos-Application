package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AlbumController implements Controller{
	MainController main_controller;
	
	public @FXML Button rename_album_button;
	public @FXML Button home_button;
	public @FXML Button slideshow_button;
	public @FXML Button add_photo_button;
	public @FXML Button remove_photo_button;
	public @FXML Button edit_photo_button;
	public @FXML Button log_out_button;
	public @FXML Button quit_button;
	public @FXML TextField rename_album_textfield;
	public @FXML Text album_info_text;
	public @FXML ListView<String> photos_list;

	
	public void setup(MainController mc) {
		this.main_controller = mc;

	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
