package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HomeController implements Controller{
	MainController main_controller;
	
	public @FXML Button create_album_button;
	public @FXML Button search_photos_button;
	public @FXML Button open_album_button;
	public @FXML Button delete_album_button;
	public @FXML Button log_out_button;
	public @FXML Button quit_button;
	public @FXML Button add_tags_button;
	public @FXML TextField create_album_textfield;
	public @FXML ListView<String> albums_listview;
	public ObservableList<String> obs;
	
	public void setup(MainController mc) {
		this.main_controller = mc;
	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
