package view.States;

import java.io.FileInputStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
		
//		this.main_controller.home_controller.obs = FXCollections.observableArrayList();
//		ObservableList<String> obs = this.main_controller.home_controller.obs;
//		for(int i = 0; i < this.user.albums.size(); i++) {
//			obs.add(this.user.albums.get(i).name);
//		}
//		this.main_controller.admin_controller.user_listview.setItems(obs);
//		ListView<String> listview = this.main_controller.home_controller.albums_listview;
//		listview.setCellFactory(p -> new ListCell<String>() {
//			private ImageView imageview = new ImageView();
//			
//			public void updateItem(String album_name, boolean empty) {
//				super.updateItem(album_name, empty);
//				if(empty) {
//					setText(null);
//					setGraphic(null);
//				}
//				else {
//					imageview.setImage(new Image(new FileInputStream()));
//				}
//			}
//		});
	}

	@Override
	public PhotosState processEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		return null;
		
	}
	public static AlbumState getInstance() {
		if(AlbumState.currentState == null) {
			AlbumState.currentState = new AlbumState();
		}
		return AlbumState.currentState;
	}

}
