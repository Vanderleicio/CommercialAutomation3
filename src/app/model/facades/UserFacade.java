package app.model.facades;

import java.util.ArrayList;

import app.model.daos.UserDAO;
import app.model.exceptions.*;
import app.model.models.*;

public class UserFacade {
	
	private static UserDAO userData = new UserDAO();
	
	private static String idCurrentUser = "0";
	
	
	public static void create(String nickName, String password, String name, String category) throws ExistentNicknameException{
		try {
			userData.searchNick(nickName);
			throw new ExistentNicknameException();
		} catch(NickNonexistent eNick) {
			User newUser = new User(nickName, password, name, category);
			userData.add(newUser);
		}
	}
	
	public static void login(String nickname, String password) throws LoginDoesntMatch, NickNonexistent {
		try {
			User userAttempt = (User) userData.searchNick(nickname);
			if (password.equals(userAttempt.getPassword())) {
				String idUser = userData.searchNick(nickname).getId();
				idCurrentUser = idUser;
			} else {
				throw new LoginDoesntMatch();
			}
		} catch(NickNonexistent eNick) {
			throw new NickNonexistent();
		}
	}
	
	public static void delUser(String id) throws CurrentUserException, IdDoesntExist, EntitiesNotRegistred {
		if (id.equals(idCurrentUser)) {
			throw new CurrentUserException();
		} else {
			userData.delete(id);
		}
	}
	
	public static void editUser(String id, String newNick, String newPass, String newName, String newCategory) throws IdDoesntExist, EntitiesNotRegistred, ExistentNicknameException {
		User userEdit = userData.getOneUser(id);
		
		try {
			if (!userEdit.getNickname().equals(newNick)) {
				userData.searchNick(newNick);
				throw new ExistentNicknameException();
			}
			userEdit.setName(newName);
			userEdit.setPassword(newPass);
			userEdit.setCategory(newCategory);
		} catch(NickNonexistent eNick) {
			userEdit.setNickname(newNick);
			userEdit.setName(newName);
			userEdit.setPassword(newPass);
			userEdit.setCategory(newCategory);
		}
	}
	
	public static ArrayList<User> listUser(){
		return userData.getUsersList();
	}

	/**
	 * @return the idCurrentUser
	 */
	public static String getIdCurrentUser() {
		return idCurrentUser;
	}
	
	public static void chooseAUser(String id){
		userData.setChosenEntityId(id);
	}
	
	public static User chosenUser() {
		try {
			String id = userData.getChosenEntityId();
			return userData.getOneUser(id);
		} catch (IdDoesntExist | EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
