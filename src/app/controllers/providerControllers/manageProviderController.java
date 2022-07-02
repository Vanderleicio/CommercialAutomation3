package app.controllers.providerControllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.model.exceptions.EmptyStringException;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.ExistentNicknameException;
import app.model.exceptions.IdDoesntExist;
import app.model.facades.ProviderFacade;
import app.model.facades.UserFacade;
import app.model.models.Provider;
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

public class manageProviderController implements Initializable{

    @FXML
    private Button buttonCreate;

    @FXML
    private TextField nameTextField;
    
    @FXML
    private Label alert;
    
    @FXML
    private TextField cnpjTextField;
    
    @FXML
    private TextField addressTextField;
    
    private Provider selected = ProviderFacade.chosenProvider();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		if (selected != null) {
			setProvData();
		}
	}
	
	public void setProvData() {
		nameTextField.setText(selected.getName());
		cnpjTextField.setText(selected.getCnpj());
		addressTextField.setText(selected.getAddress());
	}
	
	@FXML
	void createProv(ActionEvent event){
		String name = nameTextField.getText();
		String cnpj = cnpjTextField.getText();
		String address = addressTextField.getText();
		
		try {
			
			if (selected == null) {
				ProviderFacade.createProvider(name, cnpj, address);
			} else {
				ProviderFacade.editProvider(selected.getId(), name, cnpj, address, null);
			}
    		
    	    Stage stage = (Stage) buttonCreate.getScene().getWindow();
    	    stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    	    stage.close();
    	    
		} catch (IdDoesntExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyStringException e) {
			alert.setText("Campos vazios!");
		} 
	}
}