package app.controllers.userControllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.model.exceptions.CurrentUserException;
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

public class removeUserController implements Initializable{

    @FXML
    private Button buttonConfirm;
    
    @FXML
    private Button buttonCancel;
    
    @FXML
    private Label msgRemove;

    private User selected = UserFacade.chosenUser();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		msgRemove.setText(selected.getName());
	}
	
	@FXML
	void deleteUser(ActionEvent event){
		try {
			UserFacade.delUser(selected.getId());
    	    Stage stage = (Stage) buttonConfirm.getScene().getWindow();
    	    stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    	    stage.close();
		} catch (CurrentUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IdDoesntExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	void cancel(ActionEvent event) {
	    Stage stage = (Stage) buttonCancel.getScene().getWindow();
	    stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	    stage.close();
	}
}