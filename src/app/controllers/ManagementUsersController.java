package app.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.model.facades.UserFacade;
import app.model.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class ManagementUsersController implements Initializable{
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button buttonAddUser;

    @FXML
    private Button buttonEditUser;

    @FXML
    private Button buttonListUser;

    @FXML
    private Button buttonRemoveUser;
    
    @FXML
    private ListView<String> listUsers;
    
    private ObservableList<String> obsUsers;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listDataUsers();
	}
	
    public void listDataUsers() {
    	ArrayList<String> nomes = new ArrayList<String>();
    	
    	
    	nomes.add(UserFacade.listUser().get(0).getName());
    	obsUsers.add(UserFacade.listUser().get(0).getName());
    	System.out.println(nomes);
    	
    	obsUsers = FXCollections.observableArrayList(nomes);
    	listUsers.setItems(obsUsers);
    }


}
