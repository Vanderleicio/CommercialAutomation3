package app.model.daos;

import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.models.User;

public class UserDAO extends AbstractDAO{
	/**
	 * Procura e retorna uma Entidade a partir do seu nickname
	 * @param nick: nickname 
	 * @return item da lista
	 */
	
	public UserDAO() {
		super()
	}
	public User searchEntitiesNick(String nick) {
		for (int i = 0; i < this.getList().size(); i++) {
			String currentNick = ((User) this.getList().get(i)).getNickname();
			if (nick.equals(currentNick)) {
				return (User) this.getList().get(i);
			}
		}
		return null;
	}
	
	public User getOneUser(String id) throws IdDoesntExist, EntitiesNotRegistred {
		return (User) this.searchEntities(id);
	}
}
