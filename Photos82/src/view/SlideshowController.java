package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class SlideshowController implements Controller{
	MainController main_controller;
	
	@FXML Button left_button;
	@FXML Button right_button;
	@FXML Button album_button;
	@FXML Button quit_button;
	@FXML Text caption_text;
	@FXML Text tags_text;
	@FXML Text date_text;
	@FXML ImageView photo_imageview;

	
	public void setup(MainController mc) {
		this.main_controller = mc;
	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
