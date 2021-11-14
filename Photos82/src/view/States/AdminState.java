package view.States;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import model.Admin;
import model.Album;
import model.Photo;
import model.User;
import view.MainController;

public class AdminState extends PhotosState{
	private static AdminState currentState;
	private AdminState() {
		
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
		
		this.main_controller.admin_controller.obs = FXCollections.observableArrayList();
		ObservableList<String> obs = this.main_controller.admin_controller.obs;
		for(int i = 0; i < this.admin.users.size(); i++) {
			obs.add(this.admin.users.get(i).username);
		}
		this.main_controller.admin_controller.user_listview.setItems(obs);
	}

	@Override
	public PhotosState processEvent(ActionEvent e) {
		Button button = (Button)e.getSource();
		ObservableList<String> obs = this.main_controller.admin_controller.obs;
		if(button == this.main_controller.admin_controller.quit_button) {
			this.main_controller.primaryStage.close();
			return null;
		}
		if(button == this.main_controller.admin_controller.log_out_button) {
			this.main_controller.primaryStage.setScene(this.main_controller.login_scene);
			return this.main_controller.login_state;
		}
		if(button == this.main_controller.admin_controller.add_button) {
			String typed_username = this.main_controller.admin_controller.add_textfield.getText();
			if(this.admin.addUser(typed_username)) {
				obs.add(typed_username);
				try {
					Admin.writeApp(this.admin);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					this.main_controller.primaryStage.close();
				}
				return this;
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(this.main_controller.primaryStage);
				alert.setResizable(false);
				alert.setHeaderText("Error: duplicate username");
				alert.setContentText("Error: Duplicate username exists. Please type in "
						+ "a username that does not exist yet.");
				alert.showAndWait();
				return this;
			}
		}
		//button was delete user
		if(this.main_controller.admin_controller.user_listview.getSelectionModel().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(this.main_controller.primaryStage);
			alert.setResizable(false);
			alert.setHeaderText("Error: no selection");
			alert.setContentText("Error: No username is selected for deletion. Please select "
					+ "a username in the list.");
			alert.showAndWait();
			return this;
		}
		String tbd_username = this.main_controller.admin_controller.user_listview.getSelectionModel().getSelectedItem();
		this.admin.deleteuser(tbd_username);
		try {
			Admin.writeApp(this.admin);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			this.main_controller.primaryStage.close();
		}
		//removing username from the observable list
		for(int i = 0; i < obs.size(); i++) {
			if(obs.get(i).compareTo(tbd_username) == 0) {
				obs.remove(i);
			}
		}
		return this;		
		//DO NOT alLOW space as username?? or are there other restrictions??
	}
	
	public static AdminState getInstance() {
		if(AdminState.currentState == null) {
			AdminState.currentState = new AdminState();
		}
		return AdminState.currentState;
	}
}
