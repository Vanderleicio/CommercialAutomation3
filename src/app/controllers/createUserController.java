package app.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.ExistentNicknameException;
import app.model.exceptions.IdDoesntExist;
import app.model.facades.UserFacade;
import app.model.models.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class createUserController implements Initializable{

    @FXML
    private Button buttonCreate;

    @FXML
    private ComboBox<String> userCategory;
    
    @FXML
    private Label alertNick;
    
    @FXML
    private Label nickname;

    @FXML
    private PasswordField userPassField;

    @FXML
    private TextField userNameTextField;
    
    @FXML
    private TextField userNickTextField;
    
    private User selected = UserFacade.chosenUser();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		setComboBox();
		if (selected != null) {
			setUserData();
		}
	}
	
	public void setUserData() {
		userNickTextField.setText(selected.getNickname());
		userPassField.setText(selected.getPassword());
		userNameTextField.setText(selected.getName());
		userCategory.setValue(selected.getCategory());
	}
	
	public void setComboBox() {
		ArrayList<String> categories = new ArrayList<String>();
		categories.add("Funcionário");
		categories.add("Gerente");
		userCategory.setItems(FXCollections.observableArrayList(categories));
	}
	
	@FXML
	void createUser(ActionEvent event){
		String userNick = userNickTextField.getText();
		String userPass = new String(userPassField.getText());
		String userName = userNameTextField.getText();
		String userCateg = (String) userCategory.getValue();
		
		try {
			
			if (selected == null) {
				UserFacade.create(userNick, userPass, userName, userCateg);
			} else {
				UserFacade.editUser(selected.getId(), userNick, userPass, userName, userCateg);
				//UserFacade.create("tes", "tan", "do", "Funcionário");
			}
    		
    	    Stage stage = (Stage) buttonCreate.getScene().getWindow();
    	    stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    	    stage.close();
    	    
		} catch(ExistentNicknameException loginExcept) {
			alertNick.setText("O nick digitado já existe!");
    	} catch (IdDoesntExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}