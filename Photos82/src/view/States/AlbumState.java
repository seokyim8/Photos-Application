package view.States;

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
	public void enter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processEvent() {
		// TODO Auto-generated method stub
		
	}
	private static AlbumState getInstance() {
		if(AlbumState.currentState == null) {
			AlbumState.currentState = new AlbumState();
		}
		return AlbumState.currentState;
	}

}
