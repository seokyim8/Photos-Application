package view.States;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Admin;
import model.Album;
import model.Photo;
import model.User;
import view.MainController;

public class SlideshowState extends PhotosState{
	private static SlideshowState currentState;
	private int photo_index;
	private SlideshowState() {
		
	}

	@Override
	public void setup(MainController mc) {
		this.main_controller = mc;
	}
	@Override
	public void enter(Admin admin, User user, Album album, Photo photo) {
		this.main_controller.primaryStage.setTitle("Slideshow");
		this.admin = admin;
		this.user = user;
		this.album = album;
		this.photo = photo;
		
		if(this.album.photos.size() != 0) {
			this.main_controller.slideshow_controller.photo_imageview = new ImageView();
			ImageView tempImageView = this.main_controller.slideshow_controller.photo_imageview;
			try {
				tempImageView.setImage(new Image(new FileInputStream(this.album.photos.get(0).filePath)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.main_controller.primaryStage.close();
			}
			this.photo_index = 0;
			updatePhotoInfo();
		}
		else {//no photos stored
			resetPhotoInfo();
		}
	}

	@Override
	public PhotosState processEvent(ActionEvent e) {
		
		
		
		return null;
		
	}

	public static SlideshowState getInstance() {
		if(SlideshowState.currentState == null) {
			SlideshowState.currentState = new SlideshowState();
		}
		return SlideshowState.currentState;
	}
	private void updatePhotoInfo() {
		String caption = this.album.photos.get(photo_index).caption;
		String tags = "";
		String date = refineLocalDateTime(this.album.photos.get(photo_index).datetime);
		for(int i = 0; i < this.album.photos.get(photo_index).tags.size(); i++) {
			if(i == this.album.photos.get(photo_index).tags.size()-1) {
				tags += this.album.photos.get(photo_index).tags.get(i);
			}
			else {
				tags += this.album.photos.get(photo_index).tags.get(i) + ", ";
			}
		}
		this.main_controller.slideshow_controller.caption_text.setText(caption);
		this.main_controller.slideshow_controller.date_text.setText(date);
		this.main_controller.slideshow_controller.tags_text.setText(tags);
	}
	private void resetPhotoInfo() {
		this.main_controller.slideshow_controller.caption_text.setText("");
		this.main_controller.slideshow_controller.date_text.setText("");
		this.main_controller.slideshow_controller.tags_text.setText("");
	}
	private String refineLocalDateTime(LocalDateTime datetime) {//try testing in the morning (or change local time)
		String[] split_arr1 = datetime.toString().split("T");
		String[] split_arr2 = split_arr1[1].split(":");
		int hour = Integer.parseInt(split_arr2[0]);
		String minute = split_arr2[1];
		String am_pm = "am";
		
		if(hour > 12) {
			am_pm = "pm";
			hour -= 12;
		}
		return split_arr1[0] + " " + hour + ":" + minute + am_pm;
	}
}
