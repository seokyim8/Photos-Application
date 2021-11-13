package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class EditPhotoController implements Controller{
	MainController main_controller;
	
	@FXML Button album_button;
	@FXML Button apply_button;
	@FXML Button add_tag_button;
	@FXML Button copy_photo_button;
	@FXML Button move_photo_button;
	@FXML Button quit_button;
	@FXML Text album_text;
	@FXML Text caption_text;
	@FXML Text date_time_text;
	@FXML Text tags_text;
	@FXML TextField caption_textfield;
	@FXML TextField tag_name_textfield;
	@FXML TextField tag_value_textfield;
	@FXML TextField to_album_textfield;
	@FXML TableView<String> tags_tableview;

	public void setup(MainController mc) {
		this.main_controller = mc;
	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
