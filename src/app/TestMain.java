package app;

import app.model.daos.ProviderDAO;
import app.model.daos.UserDAO;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.ExistentNicknameException;
import app.model.exceptions.IdDoesntExist;
import app.model.models.Provider;
import app.model.models.User;

public class TestMain {
	
	public static void main(String[] args) throws ExistentNicknameException, IdDoesntExist, EntitiesNotRegistred {
		UserDAO uDao = new UserDAO();
		ProviderDAO pDao = new ProviderDAO();
		User teste = new User("nick", "pass", "qde", "dwd");
		Provider tet2 = new Provider("um", "dois", "tres");
		uDao.add(teste);
		pDao.add(tet2);
		System.out.println(pDao.getList().get(0));
	}
}
