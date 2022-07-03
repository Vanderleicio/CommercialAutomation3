package app.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.model.exceptions.EmptyStringException;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.facades.ClientFacade;
import app.model.models.Client;

class ClientFacadeTest {
	
	@BeforeEach
	void resetList() throws IdDoesntExist, EntitiesNotRegistred {
		// Zera os clientes na lista para realizar todos os testes.
		while (ClientFacade.listClient().size() > 0) {
			ClientFacade.delClient(ClientFacade.listClient().get(0).getId());
		}
	}
	
	@Test
	void testRegistrationOfANewClient() throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException {
		// Testa se um novo cliente é cadastrado com as informações corretas.
		ClientFacade.createClient("Joao Silva", "111111111", "joao@uefs.br", "(11)111111111"); 
		assertEquals(1, ClientFacade.listClient().size(),"Tamanho da lista de clientes apos uma adicao." );
	
		String idTest1 = ClientFacade.listClient().get(0).getId(); 
		ClientFacade.chooseAClient(idTest1);
		Client clientTest1 = ClientFacade.chosenClient();
		assertNotNull(clientTest1, "Certifica que o id existe.");
		
		assertEquals("joao@uefs.br", clientTest1.getEmail(), "Certificar que o email 'joao@uefs.br' foi cadastrado certo.");
		assertEquals("(11)111111111", clientTest1.getPhoneNumber(), "Certificar que a número '(11)111111111' foi registrado certo.");
		assertEquals("Joao Silva", clientTest1.getName(), "Certificar que o nome 'Joao Silva' foi cadastrado certo.");
		assertEquals("111111111", clientTest1.getCpf(), "Certificar que o cpf '111111111' foi salva corretamente.");
		
		ClientFacade.createClient("Maria", "222222222", "maria@uefs.br", "(22)222222222");
		assertEquals(2, ClientFacade.listClient().size(),"Tamanho da lista de clientes apos duas adicoes.");
		
		String idTest2 = ClientFacade.listClient().get(1).getId();
		ClientFacade.chooseAClient(idTest2);
		Client clientTest2 = ClientFacade.chosenClient();
		assertNotNull(clientTest2, "Certifica que o id existe.");
	
		assertEquals("maria@uefs.br", clientTest2.getEmail(), "Certificar que o email 'maria@uefs.br' foi cadastrado certo.");
		assertEquals("(22)222222222", clientTest2.getPhoneNumber(), "Certificar que o telefone '(22)222222222' foi registrado certo.");
		assertEquals("Maria", clientTest2.getName(), "Certificar que o nome 'Maria' foi cadastrado certo.");
		assertEquals("222222222", clientTest2.getCpf(), "Certificar que o cpf '222222222' foi salva corretamente.");
	}
	
	@Test
	void testEditingAClientInformation() throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException {
		// Testa se as informações de um usuário são editadas corretamente
		ClientFacade.createClient("Joao Silva", "111111111", "joao@uefs.br", "(11)111111111"); 
		String idTest1 = ClientFacade.listClient().get(0).getId();
		String emailTest1 = ClientFacade.listClient().get(0).getEmail();
		String phoneTest1 = ClientFacade.listClient().get(0).getPhoneNumber();
		String cpfTest1 = ClientFacade.listClient().get(0).getCpf();
		String nameTest1 = ClientFacade.listClient().get(0).getName();

		ClientFacade.chooseAClient(idTest1);
		Client clientTest1 = ClientFacade.chosenClient();
		
		ClientFacade.editClient(idTest1, "Joao Cruz", cpfTest1, emailTest1, phoneTest1);
		assertEquals("Joao Cruz", clientTest1.getName(), "Mudanca de nome do cliente para 'Joao Cruz'.");
		
		ClientFacade.editClient(idTest1, nameTest1, "222222223", emailTest1, phoneTest1);
		assertEquals("222222223", clientTest1.getCpf(), "Mudanca de cpf do usuario para '222222223'.");
		
		ClientFacade.editClient(idTest1, nameTest1, cpfTest1, "joaoC@uefs.br", phoneTest1);
		assertEquals("joaoC@uefs.br", clientTest1.getEmail(), "Mudanca do email do cliente para 'joaoC@uefs.br'.");
		
		ClientFacade.editClient(idTest1, nameTest1, cpfTest1, emailTest1, "(22)111111111");
		assertEquals("(22)111111111", clientTest1.getPhoneNumber(), "Mudança de telefone do usuario para '(22)111111111'");
	}
	
	@Test
	void testDeletingAnClient() throws EmptyStringException, IdDoesntExist, EntitiesNotRegistred {
		// Testa deletar um cliente através do seu ID.
		ClientFacade.createClient("Joao Silva", "111111111", "joao@uefs.br", "(11)111111111"); 
		ClientFacade.createClient("Maria", "222222222", "maria@uefs.br", "(22)222222222");
		
		String idTest1 = ClientFacade.listClient().get(0).getId();
		ClientFacade.chooseAClient(idTest1);
		Client clientTest1 = ClientFacade.chosenClient();
		
		assertTrue(ClientFacade.listClient().contains(clientTest1), "Cliente foi cadastrado");
		
		ClientFacade.delClient(idTest1);
		assertFalse(ClientFacade.listClient().contains(clientTest1), "Cliente foi deletado.");
	}
}

