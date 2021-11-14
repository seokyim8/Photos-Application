package view.States;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import model.Admin;
import model.Album;
import model.Photo;
import model.User;
import view.MainController;

public class HomeState extends PhotosState{
	private static HomeState currentState;
	private HomeState() {
		
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
		
		this.main_controller.home_controller.obs = FXCollections.observableArrayList();
		ObservableList<String> obs = this.main_controller.home_controller.obs;
		for(int i = 0; i < this.user.albums.size(); i++) {
			Album temp = this.user.albums.get(i);
			String fromDate, toDate;
			if(temp.date_range[0] == null) {
				fromDate = "none";
			}
			else {
				fromDate = temp.date_range[0].toString();
			}
			if(temp.date_range[1] == null) {
				toDate = "none";
			}
			else {
				toDate = temp.date_range[1].toString();
			}
			obs.add("Album Name: " + temp.name + "\nNumber of Photos: " + temp.num_of_photos
					+ "\nDate Range: " + fromDate + " to " + toDate);
		}
		this.main_controller.home_controller.albums_listview.setItems(obs);
	}

	@Override
	public PhotosState processEvent(ActionEvent e) {
		Button button = (Button)e.getSource();
		if(button == this.main_controller.home_controller.quit_button) {
			this.main_controller.primaryStage.close();
			return null;
		}
		if(button == this.main_controller.home_controller.log_out_button) {
			this.main_controller.primaryStage.setScene(this.main_controller.login_scene);
			return this.main_controller.login_state;
		}
		if(button == this.main_controller.home_controller.create_album_button) {
			String tbc_album = this.main_controller.home_controller.create_album_textfield.getText();
			if(!this.user.createAlbum(tbc_album)) {//cannot create album
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(this.main_controller.primaryStage);
				alert.setResizable(false);
				alert.setHeaderText("Error: duplicate albumname");
				alert.setContentText("Error: Duplicate album name exists. Please type in "
						+ "a album name that does not exist yet.");
				alert.showAndWait();
				return this;
			}
			else {//created album
				System.out.println("presssssed");
				Album temp = this.user.albums.get(this.user.albums.size()-1);
				ObservableList<String> obs = this.main_controller.home_controller.obs;
				//taking care of case where album has no photos and thus no date range 
				String fromDate, toDate;
				if(temp.date_range[0] == null) {
					fromDate = "none";
				}
				else {
					fromDate = temp.date_range[0].toString();
				}
				if(temp.date_range[1] == null) {
					toDate = "none";
				}
				else {
					toDate = temp.date_range[1].toString();
				}
				
				obs.add("Album Name: " + temp.name + "\nNumber of Photos: " + temp.num_of_photos
						+ "\nDate Range: " + fromDate + " to " + toDate);
				try {
					Admin.writeApp(this.admin);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					this.main_controller.primaryStage.close();
				}
				return this;
			}
		}
		if(button == this.main_controller.home_controller.search_photos_button) {
			SearchState tempState = this.main_controller.search_state;
			tempState.enter(this.admin, this.user, this.album, this.photo);
			this.main_controller.primaryStage.setScene(this.main_controller.search_scene);
			return tempState;
		}
		if(button == this.main_controller.home_controller.open_album_button) {
			AlbumState tempState = this.main_controller.album_state;
			tempState.enter(this.admin, this.user, this.album, this.photo);
			this.main_controller.primaryStage.setScene(this.main_controller.album_scene);
			return tempState;
		}
		//delete album button clicked
		if(this.main_controller.home_controller.albums_listview.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(this.main_controller.primaryStage);
			alert.setResizable(false);
			alert.setHeaderText("Error: no album selected");
			alert.setContentText("Error: No album is selected. Please select "
					+ "an album from the list that you want to delete.");
			alert.showAndWait();
			return this;
		}
		ObservableList<String> obs = this.main_controller.home_controller.obs;
		int index = this.main_controller.home_controller.albums_listview.getSelectionModel().getSelectedIndex();
		this.user.albums.remove(index);
		obs.remove(index);
		try {
			Admin.writeApp(this.admin);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			this.main_controller.primaryStage.close();
		}
		return this;
	}
	public static HomeState getInstance() {
		if(HomeState.currentState == null) {
			HomeState.currentState = new HomeState();
		}
		return HomeState.currentState;
	}

}
