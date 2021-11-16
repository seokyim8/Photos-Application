package model;


import javafx.application.Application;
import javafx.stage.Stage;
import view.MainController;
/**
 * This is the class that contains the main method for starting up the application.
 * 
 * @author Seok Yim, Mae Khaled
 *
 */
public class Photos extends Application{
	/**
	 * Start the application.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		MainController mc = new MainController();
		mc.start(primaryStage);
	}
	/**
	 * The main method.
	 * 
	 * @param args	the arguments given to the main method
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
