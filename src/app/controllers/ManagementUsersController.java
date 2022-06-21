package app.controllers;

import app.model.facades.UserFacade;
import app.model.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class ManagementUsersController {

    @FXML
    private Button buttonAddUser;

    @FXML
    private Button buttonEditUser;

    @FXML
    private Button buttonListUser;

    @FXML
    private Button buttonRemoveUser;
    
    @FXML
    private ListView<User> listUsers;
    
    private ObservableList<User> obsUsers;
    
    public void listDataUsers() {
    	obsUsers = FXCollections.observableArrayList(UserFacade.listUser());
    	
    	listUsers.setItems(obsUsers);
    }


}
