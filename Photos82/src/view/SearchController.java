package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Photo;

public class SearchController implements Controller{
	MainController main_controller;
	
	public @FXML Button quit_button;
	public @FXML Button go_back_home_button;
	public @FXML Button create_album_button;
	public @FXML Button search_by_date_button;
	public @FXML Button search_by_tags_button;
	public @FXML Button clear_button;
	public @FXML ListView<Photo> photos_listview;
	public @FXML TextField from_textfield;
	public @FXML TextField to_textfield;
	public @FXML TextField tag_value1_textfield;
	public @FXML TextField tag_value2_textfield;
	public @FXML ComboBox<String> tag_name1_combobox;
	public @FXML ComboBox<String> tag_name2_combobox;
	public @FXML ComboBox<String> and_or_combobox;
	
	public void setup(MainController mc) {
		this.main_controller = mc;
		and_or_combobox.setItems(FXCollections.observableArrayList("and","or"));
	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
