package app;

import app.model.daos.ProviderDAO;
import app.model.daos.UserDAO;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.ExistentNicknameException;
import app.model.exceptions.IdDoesntExist;
import app.model.models.Entity;
import app.model.models.Provider;
import app.model.models.User;

public class TestMain {
	
	public static void main(String[] args) throws ExistentNicknameException, IdDoesntExist, EntitiesNotRegistred {
		UserDAO uDao = new UserDAO();
		//ProviderDAO pDao = new ProviderDAO();
		User teste = new User("nick", "pass", "qde", "dwd");
		User test2 = new User("nick2", "pass2", "qde2", "dwd2");
		//Provider tet2 = new Provider("um", "dois", "tres");
		uDao.add(teste);
		uDao.add(test2);
		//pDao.add(tet2);
		System.out.println(uDao.getUsersList().get(0).getName());
		System.out.println(uDao.getUsersList().get(1).getName());
	}
}
