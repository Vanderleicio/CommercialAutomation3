package app.model.facades;

import java.util.ArrayList;

import app.model.exceptions.ExistentNicknameException;
import app.model.models.*;

public class UserFacade {
	
	public void create(String nickName, String password, String name, String category) throws ExistentNicknameException {
		User userRegister = User.searchNick(nickName);
		User newUser = new User(nickName, password, name, category);
		
		if (userRegister == null) {
			User.addUser(newUser);
		} else if (userRegister != null) {
			throw new ExistentNicknameException();
		}
	}
	
	public ArrayList<User> list(){
		return User.getUsers();
	}
	
}
