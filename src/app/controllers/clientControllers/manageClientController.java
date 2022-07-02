package app.controllers.clientControllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.model.exceptions.EmptyStringException;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.ExistentNicknameException;
import app.model.exceptions.IdDoesntExist;
import app.model.facades.ClientFacade;
import app.model.facades.UserFacade;
import app.model.models.Client;
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

public class manageClientController implements Initializable{

    @FXML
    private Button buttonCreate;
    
    @FXML
    private Label alertNick;
    
    @FXML
    private TextField nameTxtFld;
    
    @FXML
    private TextField cpfTxtFld;
    
    @FXML
    private TextField emailTxtFld;
    
    @FXML
    private TextField phoneTxtFld;
    
    private Client selected = ClientFacade.chosenClient();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		if (selected != null) {
			setClientData();
		}
	}
	
	public void setClientData() {
		nameTxtFld.setText(selected.getName());
		cpfTxtFld.setText(selected.getCpf());
		emailTxtFld.setText(selected.getEmail());
		phoneTxtFld.setText(selected.getPhoneNumber());
	}
	
	@FXML
	void create(ActionEvent event){
		String name = nameTxtFld.getText();
		String cpf = cpfTxtFld.getText();
		String email = emailTxtFld.getText();
		String phone = phoneTxtFld.getText();
		
		try {
			
			if (selected == null) {
				ClientFacade.createClient(name, cpf, email, phone);
			} else {
				ClientFacade.editClient(selected.getId(), name, cpf, email, phone);
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
		} catch (EmptyStringException e) {
			alertNick.setText("Campos vazios!");
		} 
	}
}