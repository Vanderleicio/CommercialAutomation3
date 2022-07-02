package app.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.model.models.User;

class UserTest {

	@Test
	void testUserBuild() {
		User userTest = new User("jooao", "1234", "Joao", "funcionario");
		
		assertEquals("jooao", userTest.getNickname(), "Verifica se o nickname do usuario e 'jooao'");
		assertEquals("1234", userTest.getPassword(), "Verifica se a senha do usuario e '1234'");
		assertEquals("Joao", userTest.getName(), "Verifica se o nome do usuario e 'Joao'");
		assertEquals("funcionario", userTest.getCategory(), "Verifica se a categoria do usuario e 'funcionario'");
	
		
		userTest.setNickname("jooao2009");
		assertEquals("jooao2009", userTest.getNickname(), "Verifica se o nickname do usuario e 'jooao2009'");

	}
	
	@Test
	void testSetFunctionality() {
		User userTest = new User("mariah", "1234", "Maria H", "funcionario");
		
		userTest.setNickname("mariah2009");
		assertEquals("mariah2009", userTest.getNickname(), "Verifica se o nickname do usuario e 'mariah2009'");
		
		userTest.setPassword("maria1234");
		assertEquals("maria1234", userTest.getPassword(), "Verifica se a senha do usuario e 'maria1234'");
		
		userTest.setName("Maria Helena");
		assertEquals("Maria Helena", userTest.getName(), "Verifica se o nome do usuario e 'Maria Helena'");
		
		userTest.setCategory("gerente");
		assertEquals("gerente", userTest.getCategory(), "Verifica se a categoria do usuario e 'gerente'");

	}

}
