package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.model.facades.UserFacade;
import app.model.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    private TableView<User> usersTable;
    
    @FXML
    private TableColumn<User, String> idUCol;
    
    @FXML
    private TableColumn<User, String> nameUCol;
    
    @FXML
    private TableColumn<User, String> nickCol;
    
    @FXML
    private TableColumn<User, String> categoryCol;
    
    @FXML
    private ListView<String> listUsers;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
	}
	
    
    @FXML
    public void addUser(ActionEvent event) {
    	Stage addStage = new Stage();
        Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/app/views/CreateUser.fxml"));
	        Scene addScene = new Scene(root);
	        //addStage.initOwner(parentStage);
	        addStage.initModality(Modality.APPLICATION_MODAL);
	        addStage.setScene(addScene);
	        
	        addStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent close) {
                	initTableView();
                }
            });
	        addStage.show(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Teste");
    }
    
    public void initTableView() {
		ObservableList<User> usersList = FXCollections.observableArrayList(UserFacade.listUser());
		usersTable.setItems(usersList);
		
		idUCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameUCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		nickCol.setCellValueFactory(new PropertyValueFactory<>("nickname"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
    }
}
