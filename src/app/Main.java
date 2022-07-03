package app;
	
/***************************
Autores: Alana Sampaio e Vanderleicio Junior
Componente Curricular: Programacao II
Concluido em: 02/07/2022
Declaro que este codigo foi elaborado por mim de forma individual e nao contem nenhum
trecho de codigo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e paginas ou documentos eletronicos da Internet. Qualquer trecho de codigo
de outra autoria que nao a minha esta destacado com uma citacao para o autor e a fonte
do codigo, e estou ciente que estes trechos nao serao considerados para fins de avaliacao.
******************************/

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;

import app.model.exceptions.EmptyStringException;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.ExistentNicknameException;
import app.model.exceptions.IdDoesntExist;
import app.model.exceptions.InvalidDateException;
import app.model.exceptions.InvalidQuantityException;
import app.model.facades.*;
import app.model.models.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
/** Principal onde se inicia o programa
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class Main extends Application {
	/**
	 * Cenas estaticas
	 */
	public static Stage stage;
	public static Scene loginScene;
	public static Scene mainScene;
	/**
	 * iniciar cenas
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			
			UserFacade.create("admin", "admin", "admin", "Gerente"); //Criação de usuário padrão para o primeiro login
			
			//testSituation() <-- Descomentar se quiser popular o programa para testá-lo
			stage = primaryStage;
			AnchorPane root1 = FXMLLoader.load(getClass().getResource("/app/views/login.fxml"));
			loginScene = new Scene(root1);
			
			AnchorPane root2 = FXMLLoader.load(getClass().getResource("/app/views/main.fxml"));
			mainScene = new Scene(root2);
			
			primaryStage.setScene(loginScene);
			stage.centerOnScreen();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Função opcional para popular o programa e testá-lo.
	 * @throws ExistentNicknameException
	 * @throws InvalidDateException
	 * @throws InvalidQuantityException
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 * @throws EmptyStringException
	 */
	public static void testSituation() throws ExistentNicknameException, InvalidDateException, InvalidQuantityException, IdDoesntExist, EntitiesNotRegistred, EmptyStringException {
	    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
	    		.withResolverStyle(ResolverStyle.STRICT);
	    
	    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
	    		.withResolverStyle(ResolverStyle.STRICT);
	    
	    LocalDate validity = LocalDate.parse("12/03/2025", dateTimeFormatter);
	    LocalDate data = LocalDate.parse("25/07/2022", dateTimeFormatter);
	    LocalTime hora = LocalTime.parse("12:30", timeFormatter);
	    
		UserFacade.create("Nick", "Pass", "Nome", "Funcionário");
		ProviderFacade.createProvider("Nome", "11.222.333/4444-55", "Praça");
		ProductFacade.createProduct("Maçã", new BigDecimal("1.25"), validity, 10, ProviderFacade.listProvider().get(0));
		HashMap<String, Integer> ingredientes = new HashMap<String, Integer>();
		ingredientes.put(ProductFacade.listProduct().get(0).getId(), 5);
		MenuFacade.createItem("Torta de maçã", "Torta feita de maçã", new BigDecimal("10"), "Sobremesa", ingredientes);
		
		ArrayList<Item> items = new ArrayList<Item>();
		ClientFacade.createClient("Nome2", "11122233345", "nome2@yahoo.com.br", "(11)23344-5566");
		SaleFacade.createSale(data, hora, "Pix", items, ClientFacade.listClient().get(0));
		MenuFacade.chooseAItem("I4");
		SaleFacade.addItem("V6", MenuFacade.chosenItem());
		
		
	}
	
	/**
	 * Trocando a cena
	 * @param src
	 */
	public static void changeScene(String src) {
		switch(src) {
			case "screenLogin":
				stage.setScene(loginScene);
				stage.centerOnScreen();
				break;
			case "Acesso Liberado":
				stage.setScene(mainScene);
				stage.centerOnScreen();
				break;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

