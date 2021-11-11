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
	
	public ArrayList<User> users;
	
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
		//check duplicate user names
		for(int i = 0; i < this.users.size(); i++) {
			if(this.users.get(i).username.compareTo(name) == 0) {
				return false;
			}
		}
		User user = new User(name);
		this.users.add(user);
		return true;
	}
	public boolean deleteuser(String name) {
		for(int i = 0; i < this.users.size(); i++) {
			if(this.users.get(i).username == name) {
				this.users.remove(i);
				return true;
			}
		}
		return false;
	}
}
