package app.controllers.userControllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ManagementUsersController implements Initializable{
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label currentUserLabel;
    
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
		buttonEditUser.setDisable(true);
		buttonRemoveUser.setDisable(true);
	}
	
    
    @FXML
    public void addUser(ActionEvent event) {
    	UserFacade.chooseAUser(null);
    	createScreens("CreateUser.fxml");
    }
    
    @FXML
    public void editUser(ActionEvent event) {
    	User selected = usersTable.getSelectionModel().getSelectedItem();
    	UserFacade.chooseAUser(selected.getId());
    	createScreens("CreateUser.fxml");
    }
    
    @FXML
    public void removeUser(ActionEvent event) {
    	User selected = usersTable.getSelectionModel().getSelectedItem();
    	UserFacade.chooseAUser(selected.getId());
    	createScreens("DeleteUser.fxml");
    }
    
    public void createScreens(String viewName) {
    	Stage addStage = new Stage();
        Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/app/views/" + viewName));
	        Scene addScene = new Scene(root);
	        //addStage.initOwner(parentStage);
	        addStage.initModality(Modality.APPLICATION_MODAL);
	        addStage.setScene(addScene);
	        
	        addStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent close) {
                	usersTable.refresh();
                	initTableView();
                }
            });
	        addStage.show(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    public void userSelected(MouseEvent event) {
    	
    	User selected = usersTable.getSelectionModel().getSelectedItem();
    	
    	if (selected != null) {
	    	if (!selected.getId().equals(UserFacade.getIdCurrentUser())) {
	    		currentUserLabel.setVisible(false);
	    		buttonRemoveUser.setDisable(false);
	    	} else {
	    		buttonRemoveUser.setDisable(true);
	    		currentUserLabel.setVisible(true);
	    	}
	    	buttonEditUser.setDisable(false);
    	}
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
