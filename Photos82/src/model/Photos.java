package model;



import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MainController;

public class Photos extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainController mc = new MainController();
		mc.start(primaryStage);
	}
	public static void main(String[] args) throws IOException {
		//Admin.writeApp(new Admin());
		launch(args);
	}
}
