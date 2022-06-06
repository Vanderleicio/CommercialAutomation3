package app.controllers;

import app.EnumSequence;
import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class loginController {

    @FXML
    private Button buttonLogin;
    
    @FXML
    private Label alertLogin;

    @FXML
    private AnchorPane containerLogin;
    
    @FXML
    private ImageView imageLogo;

    @FXML
    private AnchorPane nickFieldAndNickText;

    @FXML
    private Label nickname;

    @FXML
    private AnchorPane passFieldAndTextField;

    @FXML
    private Label password;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField textField;

    @FXML
    void freeAcess(MouseEvent event) {
    	if (checkAcess(textField.getText(), new String( passwordField.getText()))) {
    		System.out.println("Acesso Liberado");
    		Main.changeScene("Acesso Liberado");
    	} else {
    	    alertLogin.setText("Dados incorreto, tente novamente!");
    		System.out.println("Acesso não liberado!");
    	}
    }
    
 

	public boolean checkAcess(String nick, String pass) {
		return nick.equals("admin") && pass.equals("admin");
    	
    }
}