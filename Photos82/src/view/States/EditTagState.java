package view.States;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import model.Admin;
import model.Album;
import model.Photo;
import model.User;
import view.MainController;

public class EditTagState extends PhotosState{
	private static EditTagState currentState;
	
	private EditTagState() {
		
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
		setUpComboBox();
	}

	@Override
	public PhotosState processEvent(ActionEvent e) {
		Button button = (Button)e.getSource();
		if(button == this.main_controller.edittag_controller.quit_button) {
			this.main_controller.primaryStage.close();
			return null;
		}
		if(button == this.main_controller.edittag_controller.cancel_button) {
			this.main_controller.primaryStage.setScene(this.main_controller.home_scene);
			HomeState tempState = this.main_controller.home_state;
			tempState.enter(this.admin, this.user, this.album, this.photo);
			return tempState;
		}
		if(button == this.main_controller.edittag_controller.delete_button) {
			if(this.main_controller.edittag_controller.tag_combobox.getSelectionModel().isSelected(0)) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(this.main_controller.primaryStage);
				alert.setResizable(false);
				alert.setHeaderText("Error: tag not selected");
				alert.setContentText("Error: Tag not selected. Please select "
						+ "a tag name from the list of tag names.");
				alert.showAndWait();
				return this;
			}
			Alert confirmation = new Alert(AlertType.CONFIRMATION);
			confirmation.initOwner(this.main_controller.primaryStage);
			confirmation.setResizable(false);
			confirmation.setHeaderText("Are you sure you want to delete this tag type?");
			Optional<ButtonType> result = confirmation.showAndWait();
			if(result.isPresent() && result.get() == ButtonType.CANCEL) {
				return this;
			}
			//tag name/type is selected
			this.user.deleteTag(this.main_controller.edittag_controller.tag_combobox.getValue());
			try {
				Admin.writeApp(this.admin);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				this.main_controller.primaryStage.close();
			}
			setUpComboBox();
			return this;
		}
		//add button clicked
		if(this.main_controller.edittag_controller.name_textfield.getText().trim().length() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(this.main_controller.primaryStage);
			alert.setResizable(false);
			alert.setHeaderText("Error: invalid tagname");
			alert.setContentText("Error: Invalid tag name. Please type in "
					+ "a tag name that is not a blank or a sequence of spaces.");
			alert.showAndWait();
			return this;
		}
		if(!this.user.addTagType(this.main_controller.edittag_controller.name_textfield.getText().trim())) {
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

	public static EditTagState getInstance() {
		if(EditTagState.currentState == null) {
			EditTagState.currentState = new EditTagState();
		}
		return EditTagState.currentState;
	}
	private void setUpComboBox() {
		ComboBox<String> cb = this.main_controller.edittag_controller.tag_combobox;
		cb.getItems().clear();
		cb.getItems().add("Select");
		for(int i = 0; i< this.user.tagnames.size(); i++) {
			cb.getItems().add(this.user.tagnames.get(i));
		}
		cb.getSelectionModel().clearAndSelect(0);
	}
}
