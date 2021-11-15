package view.States;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.Admin;
import model.Album;
import model.Photo;
import model.User;
import view.MainController;

public class SearchState extends PhotosState{
	private static SearchState currentState;
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
		
		ComboBox<String> cb1 = this.main_controller.search_controller.tag_name1_combobox;
		ComboBox<String> cb2 = this.main_controller.search_controller.tag_name2_combobox;
		cb1.getItems().clear();
		cb2.getItems().clear();
		for(int i = 0; i < this.user.tagnames.size(); i++) {
			cb1.getItems().add(this.user.tagnames.get(i));
		}
		for(int i = 0; i < this.user.tagnames.size(); i++) {
			cb2.getItems().add(this.user.tagnames.get(i));
		}
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
			
		}
		if(button == this.main_controller.search_controller.search_by_tags_button) {
			
		}
		if(button == this.main_controller.search_controller.clear_button) {
			
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

}
