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
		
		itemsPurchased1 = new ArrayList<Item>();
		item1 = new Item("torta de maçã", "Torta feita de maçã", new BigDecimal("11.1"), "Sobremesa", comp);
		itemsPurchased1.add(item1);
		
		itemsPurchased2 = new ArrayList<Item>();
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
		assertEquals(itemsPurchased1, saleTest1.getItemsPurchased(), "Certificar que os itens comprados foram salvos corretamente.");
		assertEquals(client1, saleTest1.getClient(), "Certificar que o cliente foi salvo corretamente.");
		
		SaleFacade.createSale(date2, time2, "Dinheiro", itemsPurchased2, client2);
		assertEquals(2, SaleFacade.listSale().size(),"Tamanho da lista de itens apos duas adicoes.");
		
		String idTest2 = SaleFacade.listSale().get(1).getId();
		SaleFacade.chooseASale(idTest2);
		Sale saleTest2 = SaleFacade.chosenSale();
		assertNotNull(saleTest2, "Certifica que o id existe.");
	
		assertEquals(new BigDecimal("22.2"), saleTest2.getPriceTotal(), "Certificar que o preço foi calculado.");
		assertEquals(date2, saleTest2.getDay(), "Certificar que a data foi registrada.");
		assertEquals(time2, saleTest2.getHour(), "Certificar que a hora da venda foi cadastrada certa.");
		assertEquals("Dinheiro", saleTest2.getPaymentMethod(), "Certificar que o método de pagamento foi salva corretamente.");
		assertEquals(itemsPurchased2, saleTest2.getItemsPurchased(), "Certificar que os itens comprados foram salvos corretamente.");
		assertEquals(client2, saleTest2.getClient(), "Certificar que o cliente foi salvo corretamente.");
	}
	
	@Test
	void testEditingASaleInformation() throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException, InvalidDateException {
		// Testa se as informações de um item são editadas corretamente
		SaleFacade.createSale(date1, time1, "Pix", itemsPurchased1, client1); 
		String idTest1 = SaleFacade.listSale().get(0).getId();
		String paymentMethodTest1 = SaleFacade.listSale().get(0).getPaymentMethod();
		ArrayList<Item> itemsTest1 = SaleFacade.listSale().get(0).getItemsPurchased();
		LocalTime hourTest1 = SaleFacade.listSale().get(0).getHour();
		LocalDate dayTest1 = SaleFacade.listSale().get(0).getDay();
		
		SaleFacade.chooseASale(idTest1);
		Sale saleTest1 = SaleFacade.chosenSale();
		
		SaleFacade.editSale(idTest1, date2, hourTest1, paymentMethodTest1, itemsTest1, client1);
		assertEquals(date2, saleTest1.getDay(), "Mudanca do dia da venda.");
		
		SaleFacade.editSale(idTest1, dayTest1, time2, paymentMethodTest1, itemsTest1, client1);
		assertEquals(time2, saleTest1.getHour(), "Mudanca da hora da venda.");
		
		SaleFacade.editSale(idTest1, dayTest1, hourTest1, "Cartão", itemsTest1, client1);
		assertEquals("Cartão", saleTest1.getPaymentMethod(), "Mudanca da forma de pagamento da venda.");
		
		SaleFacade.editSale(idTest1, dayTest1, hourTest1, paymentMethodTest1, itemsPurchased2, client1);
		assertEquals(itemsPurchased2, saleTest1.getItemsPurchased(), "Mudança dos itens da venda.");
		
		SaleFacade.editSale(idTest1, dayTest1, hourTest1, paymentMethodTest1, itemsTest1, client2);
		assertEquals(client2, saleTest1.getClient(), "Mudança do cliente da venda.");
	}
	
	@Test
	void testDeletingAnSale() throws EmptyStringException, IdDoesntExist, EntitiesNotRegistred, InvalidDateException {
		// Testa deletar um item através do seu ID.
		SaleFacade.createSale(date1, time1, "Pix", itemsPurchased1, client1);  
		SaleFacade.createSale(date2, time2, "Dinheiro", itemsPurchased2, client2);
		
		String idTest1 = SaleFacade.listSale().get(0).getId();
		SaleFacade.chooseASale(idTest1);
		Sale saleTest1 = SaleFacade.chosenSale();
		
		assertTrue(SaleFacade.listSale().contains(saleTest1), "Venda foi cadastrada");
		
		SaleFacade.delSale(idTest1);
		assertFalse(SaleFacade.listSale().contains(saleTest1), "Venda foi deletada.");
	}
	
	@Test
	void testAddAndDeleteItemFromSale() throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException, InvalidQuantityException {
		SaleFacade.createSale(date1, time1, "Pix", itemsPurchased1, client1);  
		
		String id1 = SaleFacade.listSale().get(0).getId();
		SaleFacade.chooseASale(id1);
		Sale saleTest1 = SaleFacade.chosenSale();
		
		HashMap<String, Integer> compTest = new HashMap<String, Integer>();
		Item itemTest1 = new Item("Doce", "Comida doce", new BigDecimal("33.33"), "Sobremesa", compTest);
		Item itemTest2 = new Item("Salgado", "Comida salgada", new BigDecimal("44.44"), "Entrada", compTest);
		
		
		SaleFacade.addItem(id1, itemTest1);
		assertTrue(saleTest1.getItemsPurchased().contains(itemTest1));
		
		SaleFacade.addItem(id1, itemTest2);
		assertTrue(saleTest1.getItemsPurchased().contains(itemTest2));
		
		SaleFacade.deleteItem(id1, itemTest1);
		assertFalse(saleTest1.getItemsPurchased().contains(itemTest1));
		
		SaleFacade.deleteItem(id1, itemTest2);
		assertFalse(saleTest1.getItemsPurchased().contains(itemTest2));
	}
	
	@Test
	void testGettingItemsFromASale() throws EmptyStringException {
		HashMap<String, Integer> compTest = new HashMap<String, Integer>();
		Item itemTest1 = new Item("Doce", "Comida doce", new BigDecimal("33.33"), "Sobremesa", compTest);
		Item itemTest2 = new Item("Salgado", "Comida salgada", new BigDecimal("44.44"), "Entrada", compTest);
		
		ArrayList<Item> itemsPurchasedTest = new ArrayList<Item>();
		itemsPurchasedTest.add(itemTest1);
		itemsPurchasedTest.add(itemTest2);
		
		SaleFacade.createSale(date1, time1, "Pix", itemsPurchasedTest, client1);
		String id1 = SaleFacade.listSale().get(0).getId();
		
		
		assertTrue(SaleFacade.getSaleItems(id1).contains(itemTest1) && SaleFacade.getSaleItems(id1).contains(itemTest2));
	}
	
	@Test
	void testGettingAllProductsUsedInASale() throws EmptyStringException {
		HashMap<String, Integer> compTest1 = new HashMap<String, Integer>();
		compTest1.put(product1.getId(), 5);
		compTest1.put(product2.getId(), 15);
		
		HashMap<String, Integer> compTest2 = new HashMap<String, Integer>();
		compTest2.put(product1.getId(), 3);
		compTest2.put(product2.getId(), 13);
		
		Item itemTest1 = new Item("Doce", "Comida doce", new BigDecimal("33.33"), "Sobremesa", compTest1);
		Item itemTest2 = new Item("Salgado", "Comida salgada", new BigDecimal("44.44"), "Entrada", compTest2);
		
		ArrayList<Item> itemsPurchasedTest = new ArrayList<Item>();
		itemsPurchasedTest.add(itemTest1);
		itemsPurchasedTest.add(itemTest2);
		
		SaleFacade.createSale(date1, time1, "Pix", itemsPurchasedTest, client1);
		
		String idProd1 = product1.getId();
		String idProd2 = product2.getId();
		
		assertTrue(SaleFacade.getAllProductsUsed(itemsPurchasedTest).get(idProd1) == 8);
		assertTrue(SaleFacade.getAllProductsUsed(itemsPurchasedTest).get(idProd2) == 28);
	}

}

