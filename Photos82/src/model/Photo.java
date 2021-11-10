package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Photo {
	String filePath;
	String caption;
	LocalDate date;
	ArrayList<Album> albums;
	ArrayList<Tag> tags;
}
