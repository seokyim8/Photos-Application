package view;


public class AdminController implements Controller{
	MainController main_controller;
	
	public void setup(MainController mc) {
		this.main_controller = mc;
	}
}
