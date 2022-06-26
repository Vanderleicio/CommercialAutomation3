package app;
	
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;

import app.model.exceptions.ExistentNicknameException;
import app.model.exceptions.InvalidDateException;
import app.model.exceptions.InvalidQuantityException;
import app.model.facades.*;
import app.model.models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	
	public static Stage stage;
	public static Scene loginScene;
	public static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			UserFacade.create("admin", "admin", "admin", "Gerente");
			testSituation();
			
			stage = primaryStage;
			AnchorPane root1 = FXMLLoader.load(getClass().getResource("/app/views/login.fxml"));
			loginScene = new Scene(root1);
			
			AnchorPane root2 = FXMLLoader.load(getClass().getResource("/app/views/main.fxml"));
			mainScene = new Scene(root2);
			//mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
			//Image iconApp = new Image("file:logo.png"); COLOCAR ICONE NA BARRINHA QUE TEM FECHAR...
			//primaryStage.getIcons().add(iconApp);
			
			primaryStage.setScene(loginScene);
			stage.centerOnScreen();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testSituation() throws ExistentNicknameException, InvalidDateException, InvalidQuantityException {
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
		ingredientes.put("Maçã", 5);
		MenuFacade.createItem("Torta de maçã", "Torta feita de maçã", new BigDecimal("10"), "Sobremesa", ingredientes);
		
		ArrayList<Item> items = new ArrayList<Item>();
		SaleFacade.createSale(data, hora, "Pix", items, "C1");
		ClientFacade.createClient("Nome2", "11122233345", "nome2@yahoo.com.br", "(11)23344-5566");
		
	}
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

