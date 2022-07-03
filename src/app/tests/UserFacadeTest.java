package app.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.model.exceptions.CurrentUserException;
import app.model.exceptions.EmptyStringException;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.ExistentNicknameException;
import app.model.exceptions.IdDoesntExist;
import app.model.exceptions.LoginDoesntMatch;
import app.model.exceptions.NickNonexistent;
import app.model.facades.UserFacade;
import app.model.models.User;

class UserFacadeTest {
	
	@BeforeEach
	void resetList() throws CurrentUserException, IdDoesntExist, EntitiesNotRegistred {
		// Zera os usuários na lista para realizar todos os testes.
		while (UserFacade.listUser().size() > 0) {
			UserFacade.delUser(UserFacade.listUser().get(0).getId());
		}
	}
	
	@Test
	void testExistNicknameException() throws ExistentNicknameException, IdDoesntExist, EntitiesNotRegistred, EmptyStringException {
		// Testa se há a verificação de nicks repetidos.
		UserFacade.create("josee", "abcdefgh", "Jose Souza", "Gerente"); //inserindo dados do usuario
		assertThrows(ExistentNicknameException.class, () ->UserFacade.create("josee", "abcdef", "Jose", "Gerente"), "Verificando se o nickname 'josee' existe.");
		
		UserFacade.create("carlinhos21", "caca123", "Carlos", "Funcionario"); 
		assertThrows(ExistentNicknameException.class, () -> UserFacade.create("carlinhos21", "qwer123", "Carlos Almeida", "Gerente"), "Verificando se o nickname 'carlinhos21' existe.");
	}
	
	@Test
	void nicknameAndPasswordCheckForLogin() throws ExistentNicknameException, EmptyStringException {
		// Testa se o login não é liberado para informações incorretas.
		UserFacade.create("carlinhos21", "caca1243", "Carlos", "Funcionario");//inserindo dados do usuario
		assertThrows(LoginDoesntMatch.class, () -> UserFacade.login("carlinhos21", "caca123"), "Verificando se as informacoes do login 'senha' são verdadeiras.");
		
		UserFacade.create("melissazinha", "abcd1234", "Melissa", "Gerente");
		assertThrows(NickNonexistent.class, () -> UserFacade.login("melisazinha", "abcd1234"), "Verificando se as informacoes do login 'nickname' são verdadeiras.");
	}
	
	@Test
	void testRegistrationOfANewUser() throws ExistentNicknameException, IdDoesntExist, EntitiesNotRegistred, EmptyStringException {
		// Testa se um novo usuário é cadastrado com as informações corretas.
		UserFacade.create("joao12", "abc123", "Joao Silva", "Funcionario"); //inserindo dados do usuario
		assertEquals(1, UserFacade.listUser().size(),"Tamanho da lista de usuarios apos uma adicao." );
	
		String idTest1 = UserFacade.listUser().get(0).getId(); //encontrando o id do usuario a ser utilizado no teste
		UserFacade.chooseAUser(idTest1);
		User userTest1 = UserFacade.chosenUser();
		assertNotNull(userTest1, "Certifica que o id existe.");
		
		assertEquals("joao12", userTest1.getNickname(), "Certificar que o nickname 'joao12' foi cadastrado certo.");
		assertEquals("abc123", userTest1.getPassword(), "Certificar que a senha 'abc123' foi registrado certo.");
		assertEquals("Joao Silva", userTest1.getName(), "Certificar que o nome 'Joao Silva' foi cadastrado certo.");
		assertEquals("Funcionario", userTest1.getCategory(), "Certificar que a categoria 'Funcionario' foi salva corretamente.");
		
		UserFacade.create("maria_5", "yzw456", "Maria", "Gerente");
		assertEquals(2, UserFacade.listUser().size(),"Tamanho da lista de usuarios apos duas adicoes.");
		
		String idTest2 = UserFacade.listUser().get(1).getId();
		UserFacade.chooseAUser(idTest2);
		User userTest2 = UserFacade.chosenUser();
		assertNotNull(userTest2, "Certifica que o id existe.");
	
		assertEquals("maria_5", userTest2.getNickname(), "Certificar que o nickname 'maria_5' foi cadastrado certo.");
		assertEquals("yzw456", userTest2.getPassword(), "Certificar que a senha 'yzw456' foi registrado certo.");
		assertEquals("Maria", userTest2.getName(), "Certificar que o nome 'Maria' foi cadastrado certo.");
		assertEquals("Gerente", userTest2.getCategory(), "Certificar que a categoria 'Gerente' foi salva corretamente.");
	}
	
	@Test
	void testEditingAUserInformation() throws ExistentNicknameException, IdDoesntExist, EntitiesNotRegistred, EmptyStringException {
		// Testa se as informações de um usuário são editadas corretamente
		UserFacade.create("melissazinha", "abcd1234", "Melissa", "Gerente"); //inserindo dados do usuario
		String idTest1 = UserFacade.listUser().get(0).getId(); //recebendo o id do usuario a ser editado
		String nickTest1 = UserFacade.listUser().get(0).getNickname();
		String passTest1 = UserFacade.listUser().get(0).getPassword();
		String categTest1 = UserFacade.listUser().get(0).getCategory();

		UserFacade.chooseAUser(idTest1);
		User userTest1 = UserFacade.chosenUser();
		
		UserFacade.editUser(idTest1, nickTest1, passTest1, "Mel Marinho", categTest1); //editando nome
		assertEquals("Mel Marinho", userTest1.getName(), "Mudanca de nome do usuario para 'Mel Marinho'.");
		
		UserFacade.create("nando", "nandinho", "Fernando", "Gerente");
		String idTest2 = UserFacade.listUser().get(1).getId(); //id
		String nameTest2 = UserFacade.listUser().get(1).getName(); //nome
		String nickTest2 = UserFacade.listUser().get(1).getNickname(); //nick
		String passTest2 = UserFacade.listUser().get(1).getPassword(); //senha
		
		UserFacade.chooseAUser(idTest2);
		User userTest2 = UserFacade.chosenUser();
		
		UserFacade.editUser(idTest2, nickTest2, passTest2, nameTest2, "Funcionario");
		assertEquals("Funcionario", userTest2.getCategory(), "Mudanca de cargo do usuario para 'Funcionario'.");
		
		UserFacade.create("nandinha1", "fer789", "Fernanda", "Funcionario");
		String idTest3 = UserFacade.listUser().get(2).getId(); //id
		String nameTest3 = UserFacade.listUser().get(2).getName(); //nome
		String passTest3 = UserFacade.listUser().get(2).getPassword(); //senha
		String categTest3 = UserFacade.listUser().get(2).getCategory(); //categoria
		
		UserFacade.chooseAUser(idTest3);
		User userTest3 = UserFacade.chosenUser();
		
		UserFacade.editUser(idTest3, "nandainha1", passTest3, nameTest3, categTest3);
		assertEquals("nandainha1", userTest3.getNickname(), "Mudanca de nickname do usuario para 'nandainha1'.");
		
		UserFacade.create("lulu11", "lu11", "Luiza", "Funcionario");
		String idTest4 = UserFacade.listUser().get(3).getId(); //id
		String nameTest4 = UserFacade.listUser().get(3).getName(); //nome
		String nickTest4 = UserFacade.listUser().get(3).getNickname(); //nick
		String categTest4 = UserFacade.listUser().get(3).getCategory(); //categoria

		UserFacade.chooseAUser(idTest4);
		User userTest4 = UserFacade.chosenUser();
		
		UserFacade.editUser(idTest4, nickTest4, "0011abc", nameTest4, categTest4);
		assertEquals("0011abc", userTest4.getPassword(), "Mudança de senha do usuario para '0011abc'");
	}
	
	@Test
	void testDeletingAnUser() throws ExistentNicknameException, EmptyStringException, CurrentUserException, IdDoesntExist, EntitiesNotRegistred {
		// Testa deletar um usuário através do seu ID.
		UserFacade.create("lulu11", "lu11", "Luiza", "Funcionario");
		UserFacade.create("nandinha1", "fer789", "Fernanda", "Funcionario");
		
		String idTest1 = UserFacade.listUser().get(0).getId();
		UserFacade.chooseAUser(idTest1);
		User userTest1 = UserFacade.chosenUser();
		
		assertTrue(UserFacade.listUser().contains(userTest1), "Usuário foi cadastrado");
		
		UserFacade.delUser(idTest1);
		assertFalse(UserFacade.listUser().contains(userTest1), "Usuário foi deletado.");
	}
}

