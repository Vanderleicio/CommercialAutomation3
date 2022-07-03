package app.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.model.models.Client;

class ClientTest {

	@Test
	void testClientBuild() {
		Client clientTest1 = new Client("Adelaide", "19845576205", "adelaide@gmail.com", "11999999999");
		
		assertEquals("Adelaide", clientTest1.getName(), "Verifica se o nome do cliente é 'Adelaide'");
		assertEquals("19845576205", clientTest1.getCpf(), "Verifica se o cpf do cliente é '19845576205'");
		assertEquals("adelaide@gmail.com", clientTest1.getEmail(), "Verifica se o email do cliente é 'adelaide@gmail.com'");
		assertEquals("11999999999", clientTest1.getPhoneNumber(), "Verifica se o numero do cliente é '11999999999'");
		
		clientTest1.setName("Adelaide Souza");
		assertEquals("Adelaide Souza", clientTest1.getName(), "Verifica se o nome do cliente é 'Adelaide Souza'");
		
		clientTest1.setEmail("adelaides@gmail.com");
		assertEquals("adelaides@gmail.com", clientTest1.getEmail(), "Verifica se o email do cliente é 'adelaides@gmail.com'");
		
		clientTest1.setPhoneNumber("75999999999");
		assertEquals("75999999999", clientTest1.getPhoneNumber(), "Verifica se o numero do cliente é '75999999999'");
	}

}
