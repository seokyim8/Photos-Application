package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Admin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String storeDir = "";
	public static final String storeFile = "";
	
	ArrayList<User> users;
	
	public static void writeApp(Admin admin) throws IOException {//add to UML??
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir 
				+ File.separator + storeFile));
		oos.writeObject(admin);
		oos.close();//necessary??
	}
	public static Admin readApp() throws IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir
				+ File.separator + storeFile));
		Admin admin = (Admin)ois.readObject();
		ois.close();
		return admin;
	}
	
	public boolean addUser(String name) {
		//TODO: fill in 
		return false;
	}
	public boolean deleteuser(String name) {
		//TODO: fill in
		return false;
	}
}
