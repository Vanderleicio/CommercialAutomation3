package app.model.daos;

import java.util.ArrayList;

import app.model.exceptions.*;
import app.model.models.User;

public class UserDAO extends AbstractDAO{
	
	/**
	 * Procura e retorna um usuário a partir do seu nickname
	 * @param nick: nickname 
	 * @return item da lista
	 * @throws NickNonexistent 
	 */
	public User searchNick(String nick) throws NickNonexistent {
		for (int i = 0; i < this.getList().size(); i++) {
			String currentNick = ((User) this.getList().get(i)).getNickname();
			if (nick.equals(currentNick)) {
				return (User) this.getList().get(i);
			}
		}
		throw new NickNonexistent();
	}
	
	public User getOneUser(String id) throws IdDoesntExist, EntitiesNotRegistred {
		return (User) this.searchEntities(id);
	}
	
	public ArrayList <User> getUsersList(){
		ArrayList<User> usersList = castList(this.getList());
		return usersList;
	}
}
