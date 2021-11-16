package view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Tag;

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
	public @FXML ComboBox<String> tag_name_combobox;
	public @FXML TextField tag_value_textfield;
	public @FXML TextField to_album_textfield;
	public @FXML TableView<Tag> tags_tableview;
	public @FXML TableColumn<Tag,String> type_column;
	public @FXML TableColumn<Tag,String> value_column;
	public @FXML TableColumn<Tag,Tag> button_column;
	public @FXML ImageView photo_imageview;
	public @FXML ObservableList<Tag> obs;
	
	public void setup(MainController mc) {
		this.main_controller = mc;
		type_column.setCellValueFactory(new PropertyValueFactory<>("name"));
		value_column.setCellValueFactory(new PropertyValueFactory<>("value"));
		button_column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

	}
	@FXML
	private void processEvent(ActionEvent e) {
		this.main_controller.processEvent(e);
	}
}
