package app.controllers;

import app.model.daos.UserDAO;
import javafx.collections.FXCollections;
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
    private ListView<UserDAO> listUsers;
    
    public void listDataUsers() {
    	listUsers = FXCollections.observableArrayList();
    }


}
