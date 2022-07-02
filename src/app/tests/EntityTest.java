package app.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.junit.jupiter.api.Test;

import app.model.models.Product;
import app.model.models.Provider;
import app.model.models.User;

class EntityTest {

	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
    		.withResolverStyle(ResolverStyle.STRICT);

	User userTest = new User("luizi25", "abc123", "Luiza", "gerente");
	Provider provTest = new Provider("Empresa 1", "123789654", "Centro");
	LocalDate data = LocalDate.parse("20/12/2022", dateTimeFormatter);
	Product prodTest = new Product("batata", new BigDecimal("5.25"), data, 25, provTest);
	
	@Test
	void testsTwoEqualObjectsWithDifferentIDs() {
		User userTest2 = new User("caca1", "1234", "Carla", "gerente");
		assertNotEquals(userTest.getId(), userTest2.getId(), "Verifica se nao possui ID iguais em usuarios");
		
		Provider provTest2 = new Provider("Empresa 2", "123780014", "Rua Alameda");
		assertNotEquals(provTest.getId(), provTest2.getId(), "Verifica se nao possui ID iguais em fornecedores");

		Product prodTest2 = new Product("tomate", new BigDecimal("2.95"), data, 30, provTest);
		assertNotEquals(prodTest.getId(), prodTest2.getId(), "Verifica se nao possui ID iguais em produtos");

	}
}
