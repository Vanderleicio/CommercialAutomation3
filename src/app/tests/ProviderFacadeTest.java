package app.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.model.exceptions.EmptyStringException;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.facades.ClientFacade;
import app.model.facades.ProviderFacade;
import app.model.models.Client;
import app.model.models.Product;
import app.model.models.Provider;

class ProviderFacadeTest {
	
	@BeforeEach
	void resetList() throws IdDoesntExist, EntitiesNotRegistred {
		// Zera os fornecedor na lista para realizar todos os testes.
		while (ProviderFacade.listProvider().size() > 0) {
			ProviderFacade.delProvider(ProviderFacade.listProvider().get(0).getId());
		}
	}
	
	@Test
	void testRegistrationOfANewProvider() throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException {
		// Testa se um novo fornecedor é cadastrado com as informações corretas.
		ProviderFacade.createProvider("Empresa Ltda.", "111111111", "Praça"); 
		assertEquals(1, ProviderFacade.listProvider().size(),"Tamanho da lista de fornecedores apos uma adicao." );
	
		String idTest1 = ProviderFacade.listProvider().get(0).getId(); 
		ProviderFacade.chooseAProvider(idTest1);
		Provider providerTest1 = ProviderFacade.chosenProvider();
		assertNotNull(providerTest1, "Certifica que o id existe.");
		
		assertEquals("Praça", providerTest1.getAddress(), "Certificar que o endereço 'Praça' foi cadastrado certo.");
		assertEquals("Empresa Ltda.", providerTest1.getName(), "Certificar que o nome 'Empresa Ltda.' foi cadastrado certo.");
		assertEquals("111111111", providerTest1.getCnpj(), "Certificar que o cnpj '111111111' foi salva corretamente.");
		
		ProviderFacade.createProvider("Quitanda", "222222222", "Centro");
		assertEquals(2, ProviderFacade.listProvider().size(),"Tamanho da lista de fornecedor apos duas adicoes.");
		
		String idTest2 = ProviderFacade.listProvider().get(1).getId();
		ProviderFacade.chooseAProvider(idTest2);
		Provider providerTest2 = ProviderFacade.chosenProvider();
		assertNotNull(providerTest2, "Certifica que o id existe.");
	
		assertEquals("Centro", providerTest2.getAddress(), "Certificar que o endereço 'Centro' foi cadastrado certo.");
		assertEquals("Quitanda", providerTest2.getName(), "Certificar que o nome 'Quitanda' foi cadastrado certo.");
		assertEquals("222222222", providerTest2.getCnpj(), "Certificar que o cnpj '222222222' foi salva corretamente.");
	}
	
	@Test
	void testEditingAProviderInformation() throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException {
		// Testa se as informações de um usuário são editadas corretamente
		ProviderFacade.createProvider("Empresa Ltda.", "111111111", "Praça"); 
		String idTest1 = ProviderFacade.listProvider().get(0).getId();
		String andressTest1 = ProviderFacade.listProvider().get(0).getAddress();
		String cpfTest1 = ProviderFacade.listProvider().get(0).getCnpj();
		String nameTest1 = ProviderFacade.listProvider().get(0).getName();
		ArrayList<Product> products = new ArrayList<Product>();

		ProviderFacade.chooseAProvider(idTest1);
		Provider providerTest1 = ProviderFacade.chosenProvider();
		
		ProviderFacade.editProvider(idTest1, "Startup", cpfTest1, andressTest1, products);
		assertEquals("Startup", providerTest1.getName(), "Mudanca de nome do fornecedor para 'Joao Cruz'.");
		
		ProviderFacade.editProvider(idTest1, nameTest1, "222222223", andressTest1, products);
		assertEquals("222222223", providerTest1.getCnpj(), "Mudanca de cnpj do usuario para '222222223'.");
		
		ProviderFacade.editProvider(idTest1, nameTest1, cpfTest1, "Centro", products);
		assertEquals("Centro", providerTest1.getAddress(), "Mudanca do endereço do fornecedor para 'joaoC@uefs.br'.");
	}
	
	@Test
	void testDeletingAnProvider() throws EmptyStringException, IdDoesntExist, EntitiesNotRegistred {
		// Testa deletar um fornecedor através do seu ID.
		ProviderFacade.createProvider("Empresa Ltda.", "111111111", "Praça"); 
		ProviderFacade.createProvider("Quitanda", "222222222", "Centro");
		
		String idTest1 = ProviderFacade.listProvider().get(0).getId();
		ProviderFacade.chooseAProvider(idTest1);
		Provider providerTest1 = ProviderFacade.chosenProvider();
		
		assertTrue(ProviderFacade.listProvider().contains(providerTest1), "Fornecedor foi cadastrado");
		
		ProviderFacade.delProvider(idTest1);
		assertFalse(ProviderFacade.listProvider().contains(providerTest1), "Fornecedor foi deletado.");
	}
	
}

