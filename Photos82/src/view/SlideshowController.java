package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class SlideshowController implements Controller{
	MainController main_controller;
	
	public @FXML Button left_button;
	public @FXML Button right_button;
	public @FXML Button album_button;
	public @FXML Button quit_button;
	public @FXML Text caption_text;
	public @FXML Text tags_text;
	public @FXML Text date_text;
	public @FXML ImageView photo_imageview;

	
	public void setup(MainController mc) {
		this.main_controller = mc;
	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
