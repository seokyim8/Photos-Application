package view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.States.*;

public class MainController {//should we add Scene to UML CLASS DIAGRAM??
	public Stage primaryStage;
	
	//controllers
	public LoginController login_controller;
	public Scene login_scene;
	public AdminController admin_controller;
	public Scene admin_scene;
	public HomeController home_controller;
	public Scene home_scene;
	public SearchController search_controller;
	public Scene search_scene;
	public AlbumController album_controller;
	public Scene album_scene;
	public SlideshowController slideshow_controller;
	public Scene slideshow_scene;
	public AddPhotoController addphoto_controller;
	public Scene addphoto_scene;
	public EditPhotoController editphoto_controller;
	public Scene editphoto_scene;
	
	//states
	public PhotosState current_state;
	public LoginState login_state;
	public AdminState admin_state;
	public HomeState home_state;
	public SearchState search_state;
	public AlbumState album_state;
	public SlideshowState slideshow_state;
	public AddPhotoState addphoto_state;
	public EditPhotoState editphoto_state;
	
	
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setOnCloseRequest(e->Platform.exit());
		this.setup();
		this.current_state.enter(null,null,null,null);
		primaryStage.setScene(this.login_scene);
		primaryStage.setTitle("Log in");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void processEvent(ActionEvent e) {//temporary; for testing purposes
		this.current_state = this.current_state.processEvent(e);
	}
	
	private void setup() throws IOException {
		//setting up controllers
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		login_scene = scene;
		login_controller  = loader.getController();
		
		loader = new FXMLLoader(getClass().getResource("/view/admin.fxml"));
		root = loader.load();
		scene = new Scene(root);
		admin_scene = scene;
		admin_controller  = loader.getController();

		
		loader = new FXMLLoader(getClass().getResource("/view/home.fxml"));
		root = loader.load();
		scene = new Scene(root);
		home_scene = scene;
		home_controller  = loader.getController();

		
		loader = new FXMLLoader(getClass().getResource("/view/search.fxml"));
		root = loader.load();
		scene = new Scene(root);
		search_scene = scene;
		search_controller  = loader.getController();

		
		loader = new FXMLLoader(getClass().getResource("/view/album.fxml"));
		root = loader.load();
		scene = new Scene(root);
		album_scene = scene;
		album_controller  = loader.getController();

		
		loader = new FXMLLoader(getClass().getResource("/view/slideshow.fxml"));
		root = loader.load();
		scene = new Scene(root);
		slideshow_scene = scene;
		slideshow_controller  = loader.getController();

		
		loader = new FXMLLoader(getClass().getResource("/view/addphoto.fxml"));
		root = loader.load();
		scene = new Scene(root);
		addphoto_scene = scene;
		addphoto_controller  = loader.getController();

		
		loader = new FXMLLoader(getClass().getResource("/view/editphoto.fxml"));
		root = loader.load();
		scene = new Scene(root);
		editphoto_scene = scene;
		editphoto_controller  = loader.getController();

		login_controller.setup(this);
		admin_controller.setup(this);
		home_controller.setup(this);
		search_controller.setup(this);
		album_controller.setup(this);
		slideshow_controller.setup(this);//necessary? this is only a one time use window every time
		addphoto_controller.setup(this);
		editphoto_controller.setup(this);
		
		//setting up states
		this.login_state = LoginState.getInstance();this.login_state.setup(this);
		this.admin_state = AdminState.getInstance();this.admin_state.setup(this);
		this.home_state = HomeState.getInstance();this.home_state.setup(this);
		this.search_state = SearchState.getInstance();this.search_state.setup(this);
		this.album_state = AlbumState.getInstance();this.album_state.setup(this);
		this.slideshow_state = SlideshowState.getInstance();this.slideshow_state.setup(this);
		this.addphoto_state = AddPhotoState.getInstance();this.addphoto_state.setup(this);
		this.editphoto_state = EditPhotoState.getInstance();this.editphoto_state.setup(this);
		this.current_state = this.login_state;
	}
}
