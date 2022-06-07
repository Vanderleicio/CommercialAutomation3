package app.daos;

import java.util.ArrayList;

import app.exceptions.EntitiesNotRegistred;
import app.exceptions.IdDoesntExist;
import app.models.User;

public class UserDAO{
	private static ArrayList<User> arrayUsers = new ArrayList<User>();
	
	public static void addUser(User user) {
		arrayUsers.add(user);
	}
	
	public static ArrayList<User> listUser() {
		return UserDAO.arrayUsers;
	}
	
	public static void delUser(User user) {
		arrayUsers.remove(user);
	}
	
	public User searchByNick(String nick) {
		for (int i = 0; i < arrayUsers.size(); i++) {
			String currentNick = (arrayUsers.get(i)).getNickname();
			if (nick.equals(currentNick)) {
				return arrayUsers.get(i);
			}
		}
		return null;
	}
	
	public User searchById(String id) throws IdDoesntExist, EntitiesNotRegistred {
		
		if (arrayUsers.size() == 0) {
			throw new EntitiesNotRegistred();
		}
		
		for (int i = 0; i < arrayUsers.size(); i++) {
			String currentUser = (arrayUsers.get(i)).getId();
			if (id.equals(currentUser)) {
				return arrayUsers.get(i);
			}
		}
		throw new IdDoesntExist();
	}
}
