/*package app.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

import app.model.daos.UserDAO;
import app.model.exceptions.EmptyStringException;
import app.model.exceptions.ExistentNicknameException;
import app.model.exceptions.NickNonexistent;
import app.model.facades.UserFacade;

class UserDAOTest {

	@Test
	void test() throws ExistentNicknameException, EmptyStringException, NickNonexistent {
		UserFacade.create("mary01", "123456", "Maria", "Gerente");
		
		UserDAO uDao = new UserDAO();
		System.out.println();
		assertNotNull(uDao.searchNick("mary01"));
	}

}*/
