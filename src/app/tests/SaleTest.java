package app.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.model.models.Item;
import app.model.models.Product;
import app.model.models.Provider;
import app.model.models.Sale;
import app.model.models.User;

class SaleTest {

	private static Item itemTest1;
	private static Item itemTest2;
	private static Sale saleTest;
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
    		.withResolverStyle(ResolverStyle.STRICT);
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    		.withResolverStyle(ResolverStyle.STRICT); 
	
	@BeforeAll
	static void creationOfTheItemsToComposeTheSales() {
		LocalDate date1 = LocalDate.parse("15/08/2022", dateTimeFormatter);
		LocalDate date2 = LocalDate.parse("12/09/2023", dateTimeFormatter);
		LocalDate date3 = LocalDate.parse("30/05/2022", dateTimeFormatter);
		
		Provider providerTest = new Provider("Empresa ltda", "123456789", "Rua A");
		Product productTest1 = new Product("batata", new BigDecimal("1.50"), date1, 10, providerTest );
		Product productTest2 = new Product("sal", new BigDecimal("0.50"), date2, 5, providerTest );
		Product productTest3 = new Product("tomate", new BigDecimal("2.50"), date3, 23, providerTest );
		
		HashMap<String, Integer> compositionTest1 = new HashMap<String, Integer>();
		HashMap<String, Integer> compositionTest2 = new HashMap<String, Integer>();
		
		itemTest1 = new Item("porcao de batata", "batata frita", new BigDecimal("23.50"), "entrada", compositionTest1);
		itemTest1.addProduct(3, "batata");
		itemTest1.addProduct(1, "sal");
		itemTest1.addProduct(4, "tomate");
		
		itemTest2 = new Item("molho de tomate", "porcao P de molho de tomate", new BigDecimal("3.50"), "acompanhamento", compositionTest2);
		itemTest2.addProduct(1, "sal");
		itemTest2.addProduct(10, "tomate");
	}
	

}
