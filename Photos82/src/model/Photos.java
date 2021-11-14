package model;


import javafx.application.Application;
import javafx.stage.Stage;
import view.MainController;

public class Photos extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainController mc = new MainController();
		mc.start(primaryStage);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
