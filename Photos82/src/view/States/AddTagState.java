package view.States;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import model.Admin;
import model.Album;
import model.Photo;
import model.User;
import view.MainController;

public class AddTagState extends PhotosState{
	private static AddTagState currentState;
	
	private AddTagState() {
		
	}

	@Override
	public void setup(MainController mc) {
		this.main_controller = mc;
	}

	@Override
	public void enter(Admin admin, User user, Album album, Photo photo) {
		this.main_controller.primaryStage.setTitle("Add Tags");
		this.admin = admin;
		this.user = user;
		this.album = album;
		this.photo = photo;
	}

	@Override
	public PhotosState processEvent(ActionEvent e) {
		Button button = (Button)e.getSource();
		if(button == this.main_controller.addtag_controller.quit_button) {
			this.main_controller.primaryStage.close();
			return null;
		}
		if(button == this.main_controller.addtag_controller.cancel_button) {
			this.main_controller.primaryStage.setScene(this.main_controller.home_scene);
			HomeState tempState = this.main_controller.home_state;
			tempState.enter(this.admin, this.user, this.album, this.photo);
			return tempState;
		}
		//add button clicked
		if(this.main_controller.addtag_controller.name_textfield.getText().trim().length() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(this.main_controller.primaryStage);
			alert.setResizable(false);
			alert.setHeaderText("Error: invalid tagname");
			alert.setContentText("Error: Invalid tag name. Please type in "
					+ "a tag name that is not a blank or a sequence of spaces.");
			alert.showAndWait();
			return this;
		}
		if(!this.user.addTagType(this.main_controller.addtag_controller.name_textfield.getText().trim())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(this.main_controller.primaryStage);
			alert.setResizable(false);
			alert.setHeaderText("Error: duplicate tagname");
			alert.setContentText("Error: Duplicate tag name. Please type in "
					+ "a tag name that does not already exist.");
			alert.showAndWait();
			return this;
		}
		//tag type successfully added
		try {
			Admin.writeApp(this.admin);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			this.main_controller.primaryStage.close();
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(this.main_controller.primaryStage);
		alert.setResizable(false);
		alert.setHeaderText("Success: tagname added");
		alert.setContentText("Success: tagname added.");
		alert.showAndWait();
		
		this.main_controller.primaryStage.setScene(this.main_controller.home_scene);
		HomeState tempState = this.main_controller.home_state;
		tempState.enter(this.admin, this.user, this.album, this.photo);
		return tempState;
	}

	public static AddTagState getInstance() {
		if(AddTagState.currentState == null) {
			AddTagState.currentState = new AddTagState();
		}
		return AddTagState.currentState;
	}
}
