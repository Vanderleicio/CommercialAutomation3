package app.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.model.exceptions.*;
import app.model.facades.*;
import app.model.models.*;

class SaleFacadeTest{
	
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
    		.withResolverStyle(ResolverStyle.STRICT);
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    		.withResolverStyle(ResolverStyle.STRICT);
    private static LocalTime time1 = LocalTime.parse("11:34", timeFormatter);
    private static LocalTime time2 = LocalTime.parse("13:13", timeFormatter);
    private static LocalDate date1 = LocalDate.parse("12/03/2023", dateTimeFormatter);
    private static LocalDate date2 = LocalDate.parse("13/01/2023", dateTimeFormatter);
	private static Provider provider1 = new Provider("Frutas ltda", "23456789", "Praça");
	private static Provider provider2 = new Provider("Empresa ltda", "12345678", "Centro");
	private static Product product1 = new Product("maçã", new BigDecimal("3.99"), date1, 10, provider1);
	private static Product product2 = new Product("queijo", new BigDecimal("5.00"), date1, 22, provider2);
	private static Client client1 = new Client("Nome1", "11111", "nome1@email.com", "(11)111111111");
	private static Client client2 = new Client("Nome2", "22222", "nome2@email.com", "(22)222222222");
	private static Item item1;
	private static Item item2;
	private static ArrayList<Item> itemsPurchased1 = new ArrayList<Item>();
	private static ArrayList<Item> itemsPurchased2 = new ArrayList<Item>();
	
	@BeforeEach
	void resetList() throws IdDoesntExist, EntitiesNotRegistred, InvalidDateException, InvalidQuantityException, EmptyStringException {
		// Zera as vendas na lista para realizar todos os testes.
		while (SaleFacade.listSale().size() > 0) {
			SaleFacade.delSale(SaleFacade.listSale().get(0).getId());
		}
		HashMap<String, Integer> comp = new HashMap<String, Integer>();
		
		item1 = new Item("torta de maçã", "Torta feita de maçã", new BigDecimal("11.1"), "Sobremesa", comp);
		itemsPurchased1.add(item1);
		item2 = new Item("Torta de queijo", "Torta feita de queijo", new BigDecimal("22.2"), "Principal", comp);
		itemsPurchased2.add(item2);
	}
	
	@Test
	void testRegistrationOfANewSale() throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException, InvalidDateException {
		// Testa se um novo item é cadastrado com as informações corretas.
		SaleFacade.createSale(date1, time1, "Pix", itemsPurchased1, client1); 
		assertEquals(1, SaleFacade.listSale().size(),"Tamanho da lista de itens apos uma adicao." );
		
		String idTest1 = SaleFacade.listSale().get(0).getId(); 
		SaleFacade.chooseASale(idTest1);
		Sale saleTest1 = SaleFacade.chosenSale();
		assertNotNull(saleTest1, "Certifica que o id existe.");
		
		assertEquals(new BigDecimal("11.1"), saleTest1.getPriceTotal(), "Certificar que o preço foi calculado.");
		assertEquals(date1, saleTest1.getDay(), "Certificar que a data foi registrada.");
		assertEquals(time1, saleTest1.getHour(), "Certificar que a hora da venda foi cadastrada certa.");
		assertEquals("Pix", saleTest1.getPaymentMethod(), "Certificar que o método de pagamento foi salva corretamente.");
		assertEquals(itemsPurchased1, saleTest1.getItemsPurchased(), "Certificar que a composiçã foi salva corretamente.");
		assertEquals(client1, saleTest1.getClient(), "Certificar que a composiçã foi salva corretamente.");
		
		SaleFacade.createSale("Torta de queijo", "Torta feita de queijo", new BigDecimal("22.2"), "Principal", client2);
		assertEquals(2, SaleFacade.listSale().size(),"Tamanho da lista de itens apos duas adicoes.");
		
		String idTest2 = SaleFacade.listSale().get(1).getId();
		SaleFacade.chooseASale(idTest2);
		Sale saleTest2 = SaleFacade.chosenSale();
		assertNotNull(saleTest2, "Certifica que o id existe.");
	
		assertEquals(new BigDecimal("22.2"), saleTest2.getPrice(), "Certificar que o preço foi cadastrado.");
		assertEquals("Principal", saleTest2.getCategorySales(), "Certificar que a categoria foi registrada.");
		assertEquals("Torta de queijo", saleTest2.getName(), "Certificar que o nome 'torta de maçã' foi cadastrado certo.");
		assertEquals("Torta feita de queijo", saleTest2.getDescription(), "Certificar que a descrição foi salva corretamente.");
		assertEquals(client2, saleTest2.getComposition(), "Certificar que a composiçã foi salva corretamente.");
	}
	
	@Test
	void testEditingASaleInformation() throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException, InvalidDateException {
		// Testa se as informações de um item são editadas corretamente
		SaleFacade.createSale("torta de maçã", "Torta feita de maçã", new BigDecimal("11.1"), "Sobremesa", client1); 
		String idTest1 = SaleFacade.listSale().get(0).getId();
		BigDecimal paymentMethodTest1 = SaleFacade.listSale().get(0).getPrice();
		String itemsTest1 = SaleFacade.listSale().get(0).getCategorySales();
		String hourTest1 = SaleFacade.listSale().get(0).getDescription();
		String dayTest1 = SaleFacade.listSale().get(0).getName();

		SaleFacade.chooseASale(idTest1);
		Sale saleTest1 = SaleFacade.chosenSale();
		
		SaleFacade.editSale(idTest1, "Doce de maçã", hourTest1, paymentMethodTest1, itemsTest1, client1);
		assertEquals("Doce de maçã", saleTest1.getName(), "Mudanca de nome do item para 'Doce de maçã'.");
		
		SaleFacade.editSale(idTest1, dayTest1, "Doce feito de maçã", paymentMethodTest1, itemsTest1, client1);
		assertEquals("Doce feito de maçã", saleTest1.getDescription(), "Mudanca da descrição do item.");
		
		SaleFacade.editSale(idTest1, dayTest1, hourTest1, new BigDecimal("23.45"), itemsTest1, client1);
		assertEquals(new BigDecimal("23.45"), saleTest1.getPrice(), "Mudanca do preço do item.");
		
		SaleFacade.editSale(idTest1, dayTest1, hourTest1, paymentMethodTest1, "Salada", client1);
		assertEquals("Salada", saleTest1.getCategorySales(), "Mudança da categoria do item.");
		
		SaleFacade.editSale(idTest1, dayTest1, hourTest1, paymentMethodTest1, itemsTest1, client2);
		assertEquals(client2, saleTest1.getComposition(), "Mudança da composição do item.");
	}
	
	@Test
	void testDeletingAnSale() throws EmptyStringException, IdDoesntExist, EntitiesNotRegistred, InvalidDateException {
		// Testa deletar um item através do seu ID.
		SaleFacade.createSale("torta de maçã", "Torta feita de maçã", new BigDecimal("11.1"), "Sobremesa", client1); 
		SaleFacade.createSale("Torta de queijo", "Torta feita de queijo", new BigDecimal("22.2"), "Principal", client2);
		
		String idTest1 = SaleFacade.listSale().get(0).getId();
		SaleFacade.chooseASale(idTest1);
		Sale saleTest1 = SaleFacade.chosenSale();
		
		assertTrue(SaleFacade.listSale().contains(saleTest1), "Produto foi cadastrado");
		
		SaleFacade.delSale(idTest1);
		assertFalse(SaleFacade.listSale().contains(saleTest1), "Produto foi deletado.");
	}
	
	@Test
	void testAddEditAndDeleteItemFromSale() throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException, InvalidQuantityException {
		SaleFacade.createSale("torta de maçã", "Torta feita de maçã", new BigDecimal("11.1"), "Sobremesa", client1);
		
		String id1 = SaleFacade.listSale().get(0).getId();
		SaleFacade.chooseASale(id1);
		Sale saleTest1 = SaleFacade.chosenSale();
		
		SaleFacade.addProductsSales(id1, product1, 5);
		assertTrue(saleTest1.getComposition().containsKey(product1.getId()));
		
		SaleFacade.addProductsSales(id1, product2, 15);
		assertTrue(saleTest1.getComposition().containsKey(product2.getId()));
		
		SaleFacade.editProdQnt(id1, product1.getId(), 10);
		assertEquals(10, saleTest1.getComposition().get(product1.getId()));
		
		SaleFacade.removeProductFromSale(id1, product1);
		assertFalse(saleTest1.getComposition().containsKey(product1.getId()));
		
		SaleFacade.removeProductFromSale(id1, product2);
		assertFalse(saleTest1.getComposition().containsKey(product2.getId()));
	}
	
}

