package app.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.EnumSequence;
import app.Main;
import app.model.exceptions.ExistentNicknameException;
import app.model.exceptions.LoginDoesntMatch;
import app.model.exceptions.NickNonexistent;
import app.model.facades.UserFacade;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class createUserController implements Initializable{

    @FXML
    private Button buttonCreate;

    @FXML
    private ComboBox<String> userCategory;

    @FXML
    private Label nickname;

    @FXML
    private PasswordField userPassField;

    @FXML
    private TextField userNameTextField;
    
    @FXML
    private TextField userNickTextField;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		ArrayList<String> categories = new ArrayList<String>();
		categories.add("Funcionário");
		categories.add("Gerente");
		userCategory.setItems(FXCollections.observableArrayList(categories));
	}
	
	@FXML
	void createUser(ActionEvent event){
		try {
    		String userNick = userNickTextField.getText();
    		String userPass = new String(userPassField.getText());
    		String userName = userNameTextField.getText();
    		String userCateg = (String) userCategory.getValue();
    		
    		UserFacade.create(userNick, userPass, userName, userCateg);
		} catch(ExistentNicknameException loginExcept) {
    		System.out.println("Nick já existe!");
    	} 
	}
	
	
    //@FXML
    //void createUser(MouseEvent event) {
    	//try {
    		//String userNick = userNickTextField.getText();
    		//String userPass = new String(userPassField.getText());
    		//String userName = userNameTextField.getText();
    		
    		//UserFacade.create(userNick, userPass, userName, null);
    		//System.out.println("Acesso Liberado");
    		//Main.changeScene("Acesso Liberado");
    	//} catch(LoginDoesntMatch | NickNonexistent loginExcept) {
    		//alertLogin.setText("Dados incorreto, tente novamente!");
    		//System.out.println("Acesso não liberado!");
    	//}
    //}
}