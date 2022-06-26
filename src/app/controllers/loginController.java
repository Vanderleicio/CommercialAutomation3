package app.controllers;

import app.EnumSequence;
import app.Main;
import app.model.exceptions.LoginDoesntMatch;
import app.model.exceptions.NickNonexistent;
import app.model.facades.UserFacade;
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
    	try {
    		String userNick =  textField.getText();
    		String userPass = new String( passwordField.getText());
    		UserFacade.login(userNick, userPass);
    		System.out.println("Acesso Liberado");
    		Main.changeScene("Acesso Liberado");
    	} catch(LoginDoesntMatch | NickNonexistent loginExcept) {
    		alertLogin.setText("Dados incorreto, tente novamente!");
    		System.out.println("Acesso não liberado!");
    	}
    }
}