package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class EditPhotoController implements Controller{
	MainController main_controller;
	
	public @FXML Button album_button;
	public @FXML Button apply_button;
	public @FXML Button add_tag_button;
	public @FXML Button copy_photo_button;
	public @FXML Button move_photo_button;
	public @FXML Button quit_button;
	public @FXML Text album_text;
	public @FXML Text caption_text;
	public @FXML Text date_time_text;
	public @FXML Text tags_text;
	public @FXML TextField caption_textfield;
	public @FXML TextField tag_name_textfield;
	public @FXML TextField tag_value_textfield;
	public @FXML TextField to_album_textfield;
	public @FXML TableView<String> tags_tableview;

	public void setup(MainController mc) {
		this.main_controller = mc;
	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
