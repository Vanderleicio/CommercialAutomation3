package app;
	
import java.io.IOException;
import java.net.URL;
import app.model.facades.UserFacade;
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

