package view.States;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

public class AlbumState extends PhotosState{
	private static AlbumState currentState;
	private AlbumState() {
		
	}
	
	@Override
	public void setup(MainController mc) {
		this.main_controller = mc;
	}
	@Override
	public void enter(Admin admin, User user, Album album, Photo photo) {
		this.admin = admin;
		this.user = user;
		this.album = album;
		this.photo = photo;
		
		this.main_controller.album_controller.obs = FXCollections.observableArrayList();
		ObservableList<String> obs = this.main_controller.album_controller.obs;
		for(int i = 0; i < this.album.photos.size(); i++) {
			obs.add(i+"");
		}
		this.main_controller.album_controller.photos_list.setItems(obs);
		ListView<String> listview = this.main_controller.album_controller.photos_list;
		listview.setCellFactory(p -> new ListCell<String>() {
			private ImageView imageview = new ImageView();
			
			public void updateItem(String album_index, boolean empty) {
				super.updateItem(album_index, empty);
				if(empty) {
					setText(null);
					setGraphic(null);
				}
				else {
					int index = Integer.parseInt(album_index);
					
					System.out.println(index+"");
					
					Photo temp_photo = album.photos.get(index);
					String filePath = temp_photo.filePath;
					try {
						imageview.setImage(new Image(new FileInputStream(filePath)));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						main_controller.primaryStage.close();
					}
					String tags_list_string = "";
					for(int i = 0; i < temp_photo.tags.size(); i++) {
						tags_list_string += temp_photo.tags.get(i)+ " ";
					}
					setText("Caption: " + temp_photo.caption + "\nDate-time: " + 
					temp_photo.datetime.toString() + "\nTags " + tags_list_string);
					setGraphic(imageview);
				}
			}
		});
		
		updateAlbumInfo();
	}

	@Override
	public PhotosState processEvent(ActionEvent e) {
		Button button = (Button)e.getSource();
		if(button == this.main_controller.album_controller.quit_button) {
			this.main_controller.primaryStage.close();
			return null;
		}
		if(button == this.main_controller.album_controller.log_out_button) {
			this.main_controller.primaryStage.setScene(this.main_controller.login_scene);
			return this.main_controller.login_state;
		}
		if(button == this.main_controller.album_controller.home_button) {
			this.main_controller.primaryStage.setScene(this.main_controller.home_scene);
			HomeState tempState = this.main_controller.home_state;
			tempState.enter(this.admin, this.user, this.album, this.photo);
			return tempState;
		}
		if(button == this.main_controller.album_controller.slideshow_button) {
			this.main_controller.primaryStage.setScene(this.main_controller.slideshow_scene);
			SlideshowState tempState = this.main_controller.slideshow_state;
			tempState.enter(this.admin, this.user, this.album, this.photo);
			return tempState;
		}
		if(button == this.main_controller.album_controller.add_photo_button) {
			this.main_controller.primaryStage.setScene(this.main_controller.addphoto_scene);
			AddPhotoState tempState = this.main_controller.addphoto_state;
			tempState.enter(this.admin, this.user, this.album, this.photo);
			return tempState;
		}
		if(button == this.main_controller.album_controller.edit_photo_button) {
			this.main_controller.primaryStage.setScene(this.main_controller.editphoto_scene);
			EditPhotoState tempState = this.main_controller.editphoto_state;
			tempState.enter(this.admin, this.user, this.album, this.photo);
			return tempState;
		}
		if(button == this.main_controller.album_controller.rename_album_button) {
			String name = this.main_controller.album_controller.rename_album_textfield.getText();
			if(!this.album.rename(name)) {//renaming failed
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(this.main_controller.primaryStage);
				alert.setResizable(false);
				alert.setHeaderText("Error: duplicate album name found");
				alert.setContentText("Error: Duplicate album name found. Please provide "
						+ "an album name that does not already exist in the album list.");
				alert.showAndWait();
				return this;
			}
			else {//renamed album
				try { 
					Admin.writeApp(this.admin);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					this.main_controller.primaryStage.close();
				}
				updateAlbumInfo();
				return this;
			}
		}
		//remove photo button clicked --> need to come back to this one to test!!
		if(this.main_controller.album_controller.photos_list.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(this.main_controller.primaryStage);
			alert.setResizable(false);
			alert.setHeaderText("Error: no photo selected");
			alert.setContentText("Error: No photo is selected. Please select "
					+ "a photo you wish to delete from the list of photos.");
			alert.showAndWait();
			return this;
		}
		int index = this.main_controller.album_controller.photos_list.getSelectionModel().getSelectedIndex();
		this.main_controller.album_controller.obs.remove(index);
		this.album.photos.remove(index);
		try {
			Admin.writeApp(this.admin);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return this;
	}
	public static AlbumState getInstance() {
		if(AlbumState.currentState == null) {
			AlbumState.currentState = new AlbumState();
		}
		return AlbumState.currentState;
	}
	private void updateAlbumInfo() {
		String fromDate, toDate;
		if(this.album.date_range[0] == null) {
			fromDate = "none";
		}
		else {
			fromDate = this.album.date_range[0].toString();
		}
		if(this.album.date_range[1] == null) {
			toDate= "none";
		}
		else {
			toDate = this.album.date_range[1].toString();
		}
		String album_info = "Name: " + this.album.name + "\nNumber of Photos: " +
		this.album.num_of_photos + "\nRange of Dates: " + fromDate + " to " + toDate;
		this.main_controller.album_controller.album_info_text.setText(album_info);
	}

}
