package app.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import app.model.models.Product;
import app.model.models.Provider;

class ProviderTest {

	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
    		.withResolverStyle(ResolverStyle.STRICT);
	
	@Test
	void testsTheSupplierBuild() {
		Provider providerTest = new Provider("Empresa ltda", "123456789", "Centro");
		
		assertEquals("Empresa ltda", providerTest.getName(), "Verificacao do nome");
		assertEquals("123456789", providerTest.getCnpj(), "Verificacao do CNPJ");
		assertEquals("Centro", providerTest.getAddress(), "Verificacao do endere�o");
	}
	
	@Test
	void productListingTestLinkedWithSuppliers() {
		Provider providerTest = new Provider("Empresa ltda", "123456789", "Centro");
		
		assertDoesNotThrow(() -> { 
			providerTest.getProductsProvided();
		}, "Verifica se lista possui erros");
	}

	@Test
	void attributeListingTestOnProducts() {
		Provider providerTest = new Provider("Empresa 1 ltda", "123402789", "Rua A");
		
		assertDoesNotThrow(() -> { 
			providerTest.getProductsProvided();
		}, "Verifica se lista de fornecedores possui erros com um fornecedor");
	
		Provider providerTest1 = new Provider("Empresa Laticínios ltda", "1234021009", "Rua E");
		
		assertDoesNotThrow(() -> { 
			providerTest.getProductsProvided();
		}, "Verifica se lista de fornecedores possui erros com dois fornecedores");
	}
	
	@Test
	void testExchangeValuesManually() {
		Provider providerTest = new Provider("Empresa 2 ltda", "1244542789", "Rua Jurema");
		providerTest.setName("Nabos e verduras ltda");
		assertEquals("Nabos e verduras ltda", providerTest.getName(), "Verificar se ocorre erros com a troca manual do nome");
		
		providerTest.setCnpj("33216548970");
		assertEquals("33216548970", providerTest.getCnpj(), "Verificar se ocorre erros com a troca manual do cnpj");
		
		providerTest.setAddress("Rua Ju");
		assertEquals("Rua Ju", providerTest.getAddress(), "Verificar se ocorre erros com a troca manual do endereco");
	}
	
}
