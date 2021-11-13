package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SearchController implements Controller{
	MainController main_controller;
	
	@FXML Button quit_button;
	@FXML Button go_back_home_button;
	@FXML Button create_album_button;
	@FXML Button search_by_date_button;
	@FXML Button search_by_tags_button;
	@FXML Button clear_button;
	@FXML ListView<String> photos_listview;
	@FXML TextField from_textfield;
	@FXML TextField to_textfield;
	@FXML TextField tag_value1_textfield;
	@FXML TextField tag_value2_textfield;
	@FXML ComboBox<String> tag_name1_combobox;
	@FXML ComboBox<String> tag_name2_combobox;
	@FXML ComboBox<String> and_or_combobox;
	
	public void setup(MainController mc) {
		this.main_controller = mc;
		and_or_combobox.setItems(FXCollections.observableArrayList("and","or"));
	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
