package view.States;

import javafx.event.ActionEvent;
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
	public void enter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhotosState processEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		return null;
	}
	public static SearchState getInstance() {
		if(SearchState.currentState == null) {
			SearchState.currentState = new SearchState();
		}
		return SearchState.currentState;
	}

}
