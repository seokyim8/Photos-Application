package view.States;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Admin;
import model.Album;
import model.Photo;
import model.User;
import view.MainController;

public class SearchState extends PhotosState{
	private static SearchState currentState;
	private ArrayList<Photo> searched_photos;
	private SearchState() {
		
	}

	@Override
	public void setup(MainController mc) {
		this.main_controller = mc;
	}
	@Override
	public void enter(Admin admin, User user, Album album, Photo photo) {
		this.main_controller.primaryStage.setTitle("Search Photos");
		this.admin = admin;
		this.user = user;
		this.album = album;
		this.photo = photo;
		clearItems();
	}

	@Override
	public PhotosState processEvent(ActionEvent e) {
		Button button = (Button)e.getSource();
		if(button == this.main_controller.search_controller.quit_button) {
			this.main_controller.primaryStage.close();
			return null;
		}
		if(button == this.main_controller.search_controller.go_back_home_button) {
			this.main_controller.primaryStage.setScene(this.main_controller.home_scene);
			HomeState tempState = this.main_controller.home_state;
			tempState.enter(this.admin, this.user, this.album, this.photo);
			return tempState;
		}
		if(button == this.main_controller.search_controller.search_by_date_button) {
			String fromDate = this.main_controller.search_controller.from_textfield.getText();
			String toDate = this.main_controller.search_controller.to_textfield.getText();
			if(!areValidDates(fromDate, toDate)) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(this.main_controller.primaryStage);
				alert.setTitle("Error: invalid date input ");
				alert.setResizable(false);
				alert.setHeaderText("Error: Invalid date input. Please provide " +
				"a pair of dates that are in the form of YYYY-MM-DD, with valid year, month and date.");
				alert.showAndWait();
				return this;
			}
			//are valid dates
			LocalDate fd = LocalDate.parse(fromDate);
			LocalDate td = LocalDate.parse(toDate);
			if(fd.isAfter(td)) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(this.main_controller.primaryStage);
				alert.setTitle("Error: invalid date input ");
				alert.setResizable(false);
				alert.setHeaderText("Error: Invalid date input. Please provide " +
				"a pair of dates that are in order (From-Date has to be before To-Date).");
				alert.showAndWait();
				return this;
			}
			this.searched_photos = this.user.searchByDate(fromDate, toDate);
			showPhotos(this.searched_photos);
			return this;
		}
		if(button == this.main_controller.search_controller.search_by_tags_button) {//not working properly!!
			String tag1_name = "";
			if(!this.main_controller.search_controller.tag_name1_combobox.getSelectionModel().isSelected(0)) {
				tag1_name = this.main_controller.search_controller.tag_name1_combobox.getSelectionModel().getSelectedItem();
			}
			String tag1_val = this.main_controller.search_controller.tag_value1_textfield.getText();
			String tag2_name = "";
			if(!this.main_controller.search_controller.tag_name2_combobox.getSelectionModel().isSelected(0)) {
				tag2_name = this.main_controller.search_controller.tag_name2_combobox.getSelectionModel().getSelectedItem();	
			}
			String tag2_val = this.main_controller.search_controller.tag_value2_textfield.getText();
			boolean isAnd = true;
			if(this.main_controller.search_controller.and_or_combobox.getSelectionModel().getSelectedItem().compareTo("or")==0) {
				isAnd = false;
			}
			this.searched_photos = this.user.searchByTags(tag1_name, tag1_val, tag2_name, tag2_val, isAnd);
			showPhotos(this.searched_photos);			
			return this;
		}
		if(button == this.main_controller.search_controller.clear_button) {
			clearItems();
			return this;
		}
		//clicked create album based on search button
		return null;
	}
	public static SearchState getInstance() {
		if(SearchState.currentState == null) {
			SearchState.currentState = new SearchState();
		}
		return SearchState.currentState;
	}
	private boolean areValidDates(String fromDate, String toDate) {
		try {
			LocalDate.parse(fromDate);
			LocalDate.parse(toDate);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
	private void showPhotos(ArrayList<Photo> photo_arr) {
		if(this.main_controller.search_controller.obs == null) {
			this.main_controller.search_controller.obs = FXCollections.observableArrayList();
		}
		else {
			this.main_controller.search_controller.obs.clear();
		}
		ObservableList<Photo> obs = this.main_controller.search_controller.obs;
		for(int i = 0; i < photo_arr.size(); i++) {
			obs.add(photo_arr.get(i));
		}
		
		ListView<Photo> listview = this.main_controller.search_controller.photos_listview;
		listview.setCellFactory(p -> new ListCell<Photo>() {
			private ImageView imageview = new ImageView();
			
			public void updateItem(Photo photo, boolean empty) {
				super.updateItem(photo, empty);
				if(empty || photo == null) {
					setText(null);
					setGraphic(null);
				}
				else {
					String filePath = photo.filePath;
					try {
						imageview.setImage(new Image(new FileInputStream(filePath)));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						main_controller.primaryStage.close();
					}
					String tags_list_string = "";
					for(int i = 0; i < photo.tags.size(); i++) {
						if(i == photo.tags.size()-1) {
							tags_list_string += photo.tags.get(i);
						}
						else {
							tags_list_string += photo.tags.get(i)+ ", ";
						}
					}
					String which_albums = "";
					for(int i = 0; i < photo.albums.size(); i++) {
						if(i == photo.albums.size()-1) {
							which_albums += photo.albums.get(i).name;
						}
						else {
							which_albums += photo.albums.get(i).name + ", ";
						}
					}
					String refined_datetime = refineLocalDateTime(photo.datetime);
					setText("In Albums: " + which_albums + "\nCaption: " + photo.caption + "\nDate-time: " 
					+ refined_datetime + "\nTags: " + tags_list_string);
					imageview.setPreserveRatio(true);
					imageview.setFitHeight(140);
					imageview.setFitWidth(190);
					setGraphic(imageview);
				}
			}
		});
		listview.setItems(obs);
	}
	private String refineLocalDateTime(LocalDateTime datetime) {//try testing in the morning (or change local time)
		String[] split_arr1 = datetime.toString().split("T");
		String[] split_arr2 = split_arr1[1].split(":");
		int hour = Integer.parseInt(split_arr2[0]);
		String minute = split_arr2[1];
		String am_pm = "am";
		
		if(hour > 12) {
			am_pm = "pm";
			hour -= 12;
		}
		return split_arr1[0] + " " + hour + ":" + minute + am_pm;
	}
	private void clearItems() {
		this.main_controller.search_controller.from_textfield.setText("");
		this.main_controller.search_controller.to_textfield.setText("");
		this.main_controller.search_controller.tag_value1_textfield.setText("");
		this.main_controller.search_controller.tag_value2_textfield.setText("");
		if(this.main_controller.search_controller.obs != null) {
			this.main_controller.search_controller.obs.clear();
		}
		populateComboBoxes();
	}
	private void populateComboBoxes() {
		ComboBox<String> cb1 = this.main_controller.search_controller.tag_name1_combobox;
		ComboBox<String> cb2 = this.main_controller.search_controller.tag_name2_combobox;
		ComboBox<String> cb3 = this.main_controller.search_controller.and_or_combobox;
		cb1.getItems().clear();
		cb2.getItems().clear();
		cb3.getItems().clear();
		
		cb1.getItems().add("Select Tag");
		for(int i = 0; i < this.user.tagnames.size(); i++) {
			cb1.getItems().add(this.user.tagnames.get(i));
		}
		cb2.getItems().add("Select Tag");
		for(int i = 0; i < this.user.tagnames.size(); i++) {
			cb2.getItems().add(this.user.tagnames.get(i));
		}
		cb3.getItems().add("and");
		cb3.getItems().add("or");
		cb1.getSelectionModel().clearAndSelect(0);
		cb2.getSelectionModel().clearAndSelect(0);
		cb3.getSelectionModel().clearAndSelect(0);
	}
}
