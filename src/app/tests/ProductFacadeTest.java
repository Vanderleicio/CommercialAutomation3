package app.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.model.exceptions.EmptyStringException;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.exceptions.InvalidDateException;
import app.model.exceptions.InvalidQuantityException;
import app.model.exceptions.LinkedItemException;
import app.model.exceptions.NotEnoughStock;
import app.model.facades.MenuFacade;
import app.model.facades.ProductFacade;
import app.model.models.Product;
import app.model.models.Provider;

class ProductFacadeTest{
	
	private static Provider provider1 = new Provider("Frutas ltda", "23456789", "Pra�a");
	private static Provider provider2 = new Provider("Empresa ltda", "12345678", "Centro");
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
    		.withResolverStyle(ResolverStyle.STRICT);
    private static LocalDate date1 = LocalDate.parse("12/03/2023", dateTimeFormatter);
    private static LocalDate date2 = LocalDate.parse("13/01/2023", dateTimeFormatter);
	@BeforeEach
	void resetList() throws IdDoesntExist, EntitiesNotRegistred, LinkedItemException {
		// Zera os produtos na lista para realizar todos os testes.
		while (ProductFacade.listProduct().size() > 0) {
			ProductFacade.delProduct(ProductFacade.listProduct().get(0).getId());
		}
	}
	
	@Test
	void testRegistrationOfANewProduct() throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException, InvalidDateException, InvalidQuantityException {
		// Testa se um novo produto é cadastrado com as informações corretas.
		ProductFacade.createProduct("maçã", new BigDecimal("1.11"), date1, 11, provider1); 
		assertEquals(1, ProductFacade.listProduct().size(),"Tamanho da lista de produtos apos uma adicao." );
	
		String idTest1 = ProductFacade.listProduct().get(0).getId(); 
		ProductFacade.chooseAProduct(idTest1);
		Product productTest1 = ProductFacade.chosenProduct();
		assertNotNull(productTest1, "Certifica que o id existe.");
		
		assertEquals(date1, productTest1.getValidity(), "Certificar que o validade foi cadastrada certa.");
		assertEquals(11, productTest1.getQuantity(), "Certificar que a quantidade foi registrada certa.");
		assertEquals("maçã", productTest1.getName(), "Certificar que o nome 'maçã' foi cadastrado certo.");
		assertEquals(new BigDecimal("1.11"), productTest1.getPrice(), "Certificar que o preço 1.11 foi salva corretamente.");
		assertEquals(provider1, productTest1.getProvider(), "Certificar que o fornecedor foi salvo corretamente.");
		
		ProductFacade.createProduct("queijo", new BigDecimal("2.22"), date2, 22, provider2);
		assertEquals(2, ProductFacade.listProduct().size(),"Tamanho da lista de produtos apos duas adicoes.");
		
		String idTest2 = ProductFacade.listProduct().get(1).getId();
		ProductFacade.chooseAProduct(idTest2);
		Product productTest2 = ProductFacade.chosenProduct();
		assertNotNull(productTest2, "Certifica que o id existe.");
	
		assertEquals(date2, productTest2.getValidity(), "Certificar que o validade foi cadastrada certa.");
		assertEquals(22, productTest2.getQuantity(), "Certificar que a quantidade foi registrada certa.");
		assertEquals("queijo", productTest2.getName(), "Certificar que o nome 'queijo' foi cadastrado certo.");
		assertEquals(new BigDecimal("2.22"), productTest2.getPrice(), "Certificar que o preço 2.22 foi salva corretamente.");
		assertEquals(provider2, productTest2.getProvider(), "Certificar que o fornecedor foi salvo corretamente.");
	}
	
	@Test
	void testEditingAProductInformation() throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException, InvalidDateException, InvalidQuantityException {
		// Testa se as informações de um produto são editadas corretamente
		ProductFacade.createProduct("maçã", new BigDecimal("1.11"), date1, 11, provider1); 
		String idTest1 = ProductFacade.listProduct().get(0).getId();
		LocalDate validityTest1 = ProductFacade.listProduct().get(0).getValidity();
		int quantityTest1 = ProductFacade.listProduct().get(0).getQuantity();
		BigDecimal priceTest1 = ProductFacade.listProduct().get(0).getPrice();
		String nameTest1 = ProductFacade.listProduct().get(0).getName();

		ProductFacade.chooseAProduct(idTest1);
		Product productTest1 = ProductFacade.chosenProduct();
		
		ProductFacade.editProduct(idTest1, "manga", priceTest1, validityTest1, quantityTest1, provider1);
		assertEquals("manga", productTest1.getName(), "Mudanca de nome do produto para 'manga'.");
		
		ProductFacade.editProduct(idTest1, nameTest1, new BigDecimal("1.22"), validityTest1, quantityTest1, provider1);
		assertEquals(new BigDecimal("1.22"), productTest1.getPrice(), "Mudanca de preço do produto.");
		
		ProductFacade.editProduct(idTest1, nameTest1, priceTest1, date2, quantityTest1, provider1);
		assertEquals(date2, productTest1.getValidity(), "Mudanca da validade do produto.");
		
		ProductFacade.editProduct(idTest1, nameTest1, priceTest1, validityTest1, 12, provider1);
		assertEquals(12, productTest1.getQuantity(), "Mudança de quantidade do produto.");
	}
	
	@Test
	void testDeletingAnProduct() throws EmptyStringException, IdDoesntExist, EntitiesNotRegistred, InvalidDateException, InvalidQuantityException, LinkedItemException {
		// Testa deletar um produto através do seu ID.
		ProductFacade.createProduct("maçã", new BigDecimal("1.11"), date1, 11, provider1); 
		ProductFacade.createProduct("queijo", new BigDecimal("2.22"), date2, 22, provider2);
		
		String idTest1 = ProductFacade.listProduct().get(0).getId();
		ProductFacade.chooseAProduct(idTest1);
		Product productTest1 = ProductFacade.chosenProduct();
		
		assertTrue(ProductFacade.listProduct().contains(productTest1), "Produto foi cadastrado");
		
		ProductFacade.delProduct(idTest1);
		assertFalse(ProductFacade.listProduct().contains(productTest1), "Produto foi deletado.");
	}
	
	
	@Test
	void testUpdateStock() throws InvalidDateException, InvalidQuantityException, EmptyStringException, NotEnoughStock, IdDoesntExist, EntitiesNotRegistred {
		// Testa se a quantidade de produtos é diminuida ao atualizar o estoque.
		ProductFacade.createProduct("maçã", new BigDecimal("1.11"), date1, 11, provider1);
		ProductFacade.createProduct("queijo", new BigDecimal("2.22"), date2, 22, provider2);
		
		String id1 = ProductFacade.listProduct().get(0).getId();
		ProductFacade.chooseAProduct(id1);
		Product productTest1 = ProductFacade.chosenProduct();
		
		String id2 = ProductFacade.listProduct().get(1).getId();
		ProductFacade.chooseAProduct(id2);
		Product productTest2 = ProductFacade.chosenProduct();
		
		HashMap<String, Integer> prodsUsed = new HashMap<String, Integer>();
		prodsUsed.put(id1, 5);
		prodsUsed.put(id2, 15);
		
		ProductFacade.updateStock(prodsUsed);
		assertEquals(6, productTest1.getQuantity(), "Verifica se foram retiradas 5 unidades do produto");
		assertEquals(7, productTest2.getQuantity(), "Verifica se foram retiradas 15 unidades do produto");
	}
	
	
	@Test
	void testExceptions() {
		// Testa se as exceções relacionadas ao produto são lançadas.
		
		assertThrows(InvalidDateException.class, () -> {
			LocalDate date3 = LocalDate.parse("12/03/2000", dateTimeFormatter);
			ProductFacade.createProduct("maçã", new BigDecimal("1.11"), date3, 11, provider1); 
		}, "Lançada quando a validade a ser cadastrada já passou.");
		
		assertThrows(InvalidQuantityException.class, () -> {
			ProductFacade.createProduct("maçã", new BigDecimal("1.11"), date1, 0, provider1); 
		}, "Lançada quando a quantidade a ser cadastrada não é maior que 0.");
		
		assertThrows(InvalidQuantityException.class, () -> {
			ProductFacade.createProduct("maçã", new BigDecimal("1.11"), date1, -1, provider1); 
		}, "Lançada quando a quantidade a ser cadastrada não é maior que 0.");
	}
}

