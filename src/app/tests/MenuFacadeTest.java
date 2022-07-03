package app.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.model.exceptions.EmptyStringException;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.exceptions.InvalidDateException;
import app.model.exceptions.NotEnoughStock;
import app.model.facades.MenuFacade;
import app.model.facades.ProductFacade;
import app.model.models.Item;
import app.model.models.Product;
import app.model.models.Provider;

class MenuFacadeTest{
	
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
    		.withResolverStyle(ResolverStyle.STRICT);
    private static LocalDate date1 = LocalDate.parse("12/03/2023", dateTimeFormatter);
    private static LocalDate date2 = LocalDate.parse("13/01/2023", dateTimeFormatter);
	private static Provider provider1 = new Provider("Frutas ltda", "23456789", "Praça");
	private static Provider provider2 = new Provider("Empresa ltda", "12345678", "Centro");
	private static Product item1 = new Product("maçã", new BigDecimal("3.99"), date1, 10, provider1);
	private static Product item2 = new Product("queijo", new BigDecimal("5.00"), date2, 22, provider2);
	private static HashMap<String, Integer> composition1 = new HashMap<String, Integer>();
	private static HashMap<String, Integer> composition2 = new HashMap<String, Integer>();
	
	@BeforeAll
	void setComposition() {
		String id1 = ProductFacade.listProduct().get(0).getId();
		String id2 = ProductFacade.listProduct().get(1).getId();
		composition1.put(id1, 5);
		composition2.put(id2, 10);
	}
	
	@BeforeEach
	void resetList() throws IdDoesntExist, EntitiesNotRegistred {
		// Zera os itens na lista para realizar todos os testes.
		while (MenuFacade.listItem().size() > 0) {
			MenuFacade.delItem(MenuFacade.listItem().get(0).getId());
		}
	}
	
	@Test
	void testRegistrationOfANewMenu() throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException, InvalidDateException {
		// Testa se um novo item é cadastrado com as informações corretas.
		MenuFacade.createItem("torta de maçã", "Torta feita de maçã", new BigDecimal("11.1"), "Sobremesa", composition1); 
		assertEquals(1, MenuFacade.listItem().size(),"Tamanho da lista de itens apos uma adicao." );
	
		String idTest1 = MenuFacade.listItem().get(0).getId(); 
		MenuFacade.chooseAItem(idTest1);
		Item itemTest1 = MenuFacade.chosenItem();
		assertNotNull(itemTest1, "Certifica que o id existe.");
		
		assertEquals(new BigDecimal("11.1"), itemTest1.getPrice(), "Certificar que o preço foi cadastrado.");
		assertEquals("Sobremesa", itemTest1.getCategoryItems(), "Certificar que a categoria foi registrada.");
		assertEquals("torta de maçã", itemTest1.getName(), "Certificar que o nome 'torta de maçã' foi cadastrado certo.");
		assertEquals("Torta feita de maçã", itemTest1.getDescription(), "Certificar que a descrição foi salva corretamente.");
		assertEquals(composition1, itemTest1.getComposition(), "Certificar que a composiçã foi salva corretamente.");
		
		MenuFacade.createItem("Torta de queijo", "Torta feita de queijo", new BigDecimal("22.2"), "Principal", composition2);
		assertEquals(2, MenuFacade.listItem().size(),"Tamanho da lista de itens apos duas adicoes.");
		
		String idTest2 = MenuFacade.listItem().get(1).getId();
		MenuFacade.chooseAItem(idTest2);
		Item itemTest2 = MenuFacade.chosenItem();
		assertNotNull(itemTest2, "Certifica que o id existe.");
	
		assertEquals(new BigDecimal("22.2"), itemTest2.getPrice(), "Certificar que o preço foi cadastrado.");
		assertEquals("Principal", itemTest2.getCategoryItems(), "Certificar que a categoria foi registrada.");
		assertEquals("queijo", itemTest2.getName(), "Certificar que o nome 'torta de maçã' foi cadastrado certo.");
		assertEquals("Torta feita de queijo", itemTest2.getDescription(), "Certificar que a descrição foi salva corretamente.");
		assertEquals(composition2, itemTest2.getComposition(), "Certificar que a composiçã foi salva corretamente.");
	}
	
	@Test
	void testEditingAMenuInformation() throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException, InvalidDateException {
		// Testa se as informações de um item são editadas corretamente
		MenuFacade.createItem("torta de maçã", "Torta feita de maçã", new BigDecimal("11.1"), "Sobremesa", composition1); 
		String idTest1 = MenuFacade.listItem().get(0).getId();
		BigDecimal priceTest1 = MenuFacade.listItem().get(0).getPrice();
		String categoryTest1 = MenuFacade.listItem().get(0).getCategoryItems();
		String descriptionTest1 = MenuFacade.listItem().get(0).getDescription();
		String nameTest1 = MenuFacade.listItem().get(0).getName();

		MenuFacade.chooseAItem(idTest1);
		Item itemTest1 = MenuFacade.chosenItem();
		
		MenuFacade.editItem(idTest1, "Doce de maçã", descriptionTest1, priceTest1, categoryTest1, composition1);
		assertEquals("Doce de maçã", itemTest1.getName(), "Mudanca de nome do item para 'Doce de maçã'.");
		
		MenuFacade.editItem(idTest1, nameTest1, "Doce feito de maçã", priceTest1, categoryTest1, composition1);
		assertEquals("Doce feito de maçã", itemTest1.getDescription(), "Mudanca da descrição do item.");
		
		MenuFacade.editItem(idTest1, nameTest1, descriptionTest1, new BigDecimal("23.45"), categoryTest1, composition1);
		assertEquals(new BigDecimal("23.45"), itemTest1.getPrice(), "Mudanca do preço do item.");
		
		MenuFacade.editItem(idTest1, nameTest1, descriptionTest1, priceTest1, "Salada", composition1);
		assertEquals("Salada", itemTest1.getCategoryItems(), "Mudança da categoria do item.");
		
		MenuFacade.editItem(idTest1, nameTest1, descriptionTest1, priceTest1, categoryTest1, composition2);
		assertEquals(composition2, itemTest1.getComposition(), "Mudança da composição do item.");
	}
	
	@Test
	void testDeletingAnMenu() throws EmptyStringException, IdDoesntExist, EntitiesNotRegistred, InvalidDateException {
		// Testa deletar um item através do seu ID.
		MenuFacade.createItem("torta de maçã", "Torta feita de maçã", new BigDecimal("11.1"), "Sobremesa", composition1); 
		MenuFacade.createItem("Torta de queijo", "Torta feita de queijo", new BigDecimal("22.2"), "Principal", composition2);
		
		String idTest1 = MenuFacade.listItem().get(0).getId();
		MenuFacade.chooseAItem(idTest1);
		Item itemTest1 = MenuFacade.chosenItem();
		
		assertTrue(MenuFacade.listItem().contains(itemTest1), "Produto foi cadastrado");
		
		MenuFacade.delItem(idTest1);
		assertFalse(MenuFacade.listItem().contains(itemTest1), "Produto foi deletado.");
	}
	
	
	@Test
	void testUpdateStock() throws InvalidDateException, EmptyStringException, NotEnoughStock, IdDoesntExist, EntitiesNotRegistred {
		// Testa se a quantidade de itens é diminuida ao atualizar o estoque.
		MenuFacade.createItem("torta de maçã", "Torta feita de maçã", new BigDecimal("11.1"), "Sobremesa", composition1);
		MenuFacade.createItem("Torta de queijo", "Torta feita de queijo", new BigDecimal("22.2"), "Principal", composition2);
		
		String id1 = MenuFacade.listItem().get(0).getId();
		MenuFacade.chooseAItem(id1);
		Item itemTest1 = MenuFacade.chosenItem();
		
		String id2 = MenuFacade.listItem().get(1).getId();
		MenuFacade.chooseAItem(id2);
		Item itemTest2 = MenuFacade.chosenItem();
		
		HashMap<String, Integer> prodsUsed = new HashMap<String, Integer>();
		prodsUsed.put(id1, 5);
		prodsUsed.put(id2, 15);
		
		MenuFacade.updateStock(prodsUsed);
		assertEquals(6, itemTest1.getCategoryItems(), "Verifica se foram retiradas 5 unidades do item");
		assertEquals(7, itemTest2.getCategoryItems(), "Verifica se foram retiradas 15 unidades do item");
	}
	
	
	@Test
	void testExceptions() {
		// Testa se as exceções relacionadas ao item são lançadas.
		
		assertThrows(InvalidDateException.class, () -> {
			LocalDate date3 = LocalDate.parse("12/03/2000", dateTimeFormatter);
			MenuFacade.createItem("torta de maçã", "Torta feita de maçã", date3, "Sobremesa", composition1); 
		}, "Lançada quando a validade a ser cadastrada já passou.");
		
		assertThrows(InvalidCategoryItemsException.class, () -> {
			MenuFacade.createItem("torta de maçã", "Torta feita de maçã", new BigDecimal("11.1"), 0, composition1); 
		}, "Lançada quando a quantidade a ser cadastrada não é maior que 0.");
		
		assertThrows(InvalidCategoryItemsException.class, () -> {
			MenuFacade.createItem("torta de maçã", "Torta feita de maçã", new BigDecimal("11.1"), -1, composition1); 
		}, "Lançada quando a quantidade a ser cadastrada não é maior que 0.");
	}
}

