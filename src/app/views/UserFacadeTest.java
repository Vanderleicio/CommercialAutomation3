package app.views;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.model.exceptions.EmptyStringException;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.ExistentNicknameException;
import app.model.exceptions.IdDoesntExist;
import app.model.exceptions.LoginDoesntMatch;
import app.model.facades.UserFacade;
import app.model.models.User;

class UserFacadeTest {

	@Test
	void testExistNicknameException() throws ExistentNicknameException, IdDoesntExist, EntitiesNotRegistred, EmptyStringException {
		UserFacade.create("josee", "abcdefgh", "Jose Souza", "Gerente"); //inserindo dados do usuario
		assertThrows(ExistentNicknameException.class, () ->UserFacade.create("josee", "abcdef", "Jose", "Gerente"), "Verificando se o nickname 'josee' existe.");
		
		UserFacade.create("carlinhos21", "caca123", "Carlos", "Funcionario"); 
		assertThrows(ExistentNicknameException.class, () -> UserFacade.create("carlinhos21", "qwer123", "Carlos Almeida", "Gerente"), "Verificando se o nickname 'carlinhos21' existe.");
	}
	
	@Test
	void nicknameAndPasswordCheckForLogin() throws ExistentNicknameException, EmptyStringException {
		UserFacade.create("carlinhos21", "caca1243", "Carlos", "Funcionario");//inserindo dados do usuario
		assertThrows(LoginDoesntMatch.class, () -> UserFacade.login("carlinhos21", "caca123"), "Verificando se as informacoes do login 'senha' são verdadeiras.");
		
		UserFacade.create("melissazinha", "abcd1234", "Melissa", "Gerente");
		assertThrows(LoginDoesntMatch.class, () -> UserFacade.login("melisazinha", "abcd1234"), "Verificando se as informacoes do login 'nickname' são verdadeiras.");
	}
	
	@Test
	void testRegistrationOfANewUser() throws ExistentNicknameException, IdDoesntExist, EntitiesNotRegistred {
		UserFacade.create("joao12", "abc123", "Joao Silva", "Funcionario"); //inserindo dados do usuario
		assertEquals(1, UserFacade.listUser().size(),"Tamanho da lista de usuarios apos uma adicao." );
	
		String idTest1 = UserFacade.listUser().get(0).getId(); //encontrando o id do usuario a ser utilizado no teste
		
		User userTest1 = (User) UserFacade.searchEntities(idTest1);
		assertNotNull(userTest1, "Certifica que o id existe.");
		
		assertEquals("joao12", userTest1.getNickname(), "Certificar que o nickname 'joao12' foi cadastrado certo.");
		assertEquals("abc123", userTest1.getPassword(), "Certificar que a senha 'abc123' foi registrado certo.");
		assertEquals("Joao Silva", userTest1.getName(), "Certificar que o nome 'Joao Silva' foi cadastrado certo.");
		assertEquals("Funcionario", userTest1.getCategory(), "Certificar que a categoria 'Funcionario' foi salva corretamente.");
		
		UserFacade.create("maria_5", "yzw456", "Maria", "Gerente");
		assertEquals(2, UserFacade.listUser().size(),"Tamanho da lista de usuarios apos duas adicoes.");
		
		String idTest2 = UserFacade.listUser().get(1).getId();
		
		User userTest2 = (User) UserFacade.chooseAUser(idTest2);
		assertNotNull(userTest2, "Certifica que o id existe.");
	
		assertEquals("maria_5", userTest2.getNickname(), "Certificar que o nickname 'maria_5' foi cadastrado certo.");
		assertEquals("yzw456", userTest2.getPassword(), "Certificar que a senha 'yzw456' foi registrado certo.");
		assertEquals("Maria", userTest2.getName(), "Certificar que o nome 'Maria' foi cadastrado certo.");
		assertEquals("Gerente", userTest2.getCategory(), "Certificar que a categoria 'Gerente' foi salva corretamente.");
	}
	
	@Test
	void testEditingAUserInformation() throws ExistentNicknameException, IdDoesntExist, EntitiesNotRegistred {
		UserFacade.create("melissazinha", "abcd1234", "Melissa", "Gerente"); //inserindo dados do usuario
		String idTest1 = UserFacade.listUser().get(0).getId(); //recebendo o id do usuario a ser editado
		String idTest11 = UserFacade.listUser().get(0).getNickname();
		String idTest12 = UserFacade.listUser().get(0).getPassword();
		String idTest13 = UserFacade.listUser().get(0).getCategory();
		User userTest1 = (User) UserFacade.searchEntities(idTest1); // verifica se o id existe
		UserFacade.editUser(idTest1, "Mel Marinho", idTest11, idTest12, idTest13); //editando nome
		assertEquals("Mel Marinho", userTest1.getName(), "Mudanca de nome do usuario para 'Mel Marinho'.");
		
		UserFacade.create("nando", "nandinho", "Fernando", "Gerente");
		String idTest2 = UserFacade.listUser().get(1).getId(); //id
		String idTest21 = UserFacade.listUser().get(1).getName(); //nome
		String idTest22 = UserFacade.listUser().get(1).getNickname(); //nick
		String idTest23 = UserFacade.listUser().get(1).getPassword(); //senha
		//String idTest24 = UserFacade.listUser().get(1).getCategory(); //categoria
		User userTest2 = (User) UserFacade.searchEntities(idTest2);
		UserFacade.editUser(idTest2, idTest21, idTest22, idTest23, "Funcionario");
		assertEquals("Funcionario", userTest2.getCategory(), "Mudanca de cargo do usuario para 'Funcionario'.");
		
		UserFacade.create("nandinha1", "fer789", "Fernanda", "Funcionario");
		String idTest3 = UserFacade.listUser().get(2).getId(); //id
		String idTest31 = UserFacade.listUser().get(2).getName(); //nome
		String idTest32 = UserFacade.listUser().get(2).getPassword(); //senha
		String idTest33 = UserFacade.listUser().get(2).getCategory(); //categoria
		User userTest3 = (User) UserFacade.searchEntities(idTest3);
		UserFacade.editUser(idTest3, idTest31, "nandainha1", idTest32, idTest33);
		assertEquals("nandainha1", userTest3.getNickname(), "Mudanca de nickname do usuario para 'Funcionario'.");
		
		UserFacade.create("lulu11", "lu11", "Luiza", "Funcionario");
		String idTest4 = UserFacade.listUser().get(3).getId(); //id
		String idTest41 = UserFacade.listUser().get(3).getId(); //nome
		String idTest42 = UserFacade.listUser().get(3).getId(); //nick
		String idTest43 = UserFacade.listUser().get(3).getId(); //categoria
		User userTest4 = (User) UserFacade.searchEntities(idTest4);
		UserFacade.editUser(idTest4, idTest41, idTest42, "0011abc", idTest43);
		assertEquals("0011abc", userTest4.getPassword(), "Mudança de senha do usuario para '0011abc'");
	}
	
	
	@Test
	void testChecksListForErrors() throws ExistentNicknameException, EmptyStringException {
		UserFacade.create("josee", "abcdefgh", "Jose Souza", "Gerente");
		
		assertDoesNotThrow(() -> { 
			UserFacade.listUser();
		}, "Verifica se lista de usuarios possui erros");
	}
	
	//delUser
	
	//chosenUser
}

