package app.controllers.clientControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.model.facades.ClientFacade;
import app.model.facades.UserFacade;
import app.model.models.Client;
import app.model.models.Provider;
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

public class ManagementClientController implements Initializable {
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonAddClient;

    @FXML
    private Button buttonEditClient;

    @FXML
    private Button buttonRemoveClient;

    @FXML
    private TableColumn<Client, String> cpfCol;
    
    @FXML
    private TableColumn<Client, String> emailCol;

    @FXML
    private TableColumn<Client, String> idCol;

    @FXML
    private TableColumn<Client, String> nameCol;

    @FXML
    private TableColumn<Client, String> phoneNumberCol;

    @FXML
    private TableView<Client> clientsTable;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
		buttonEditClient.setDisable(true);
		buttonRemoveClient.setDisable(true);
	}

    @FXML
    void addClient(ActionEvent event) {
    	ClientFacade.chooseAClient(null);
    	createScreens("CreateClient.fxml");
    }

    @FXML
    void editClient(ActionEvent event) {
    	Client selected = clientsTable.getSelectionModel().getSelectedItem();
    	ClientFacade.chooseAClient(selected.getId());
    	createScreens("CreateClient.fxml");
    }

    @FXML
    void removeClient(ActionEvent event) {
    	Client selected = clientsTable.getSelectionModel().getSelectedItem();
    	ClientFacade.chooseAClient(selected.getId());
    	createScreens("DeleteClient.fxml");
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
                	clientsTable.refresh();
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
    public void clientSelected(MouseEvent event) {
    	Client selected = clientsTable.getSelectionModel().getSelectedItem();
    	if (selected != null) {
    		buttonEditClient.setDisable(false);
    		buttonRemoveClient.setDisable(false);
    	}
    }	
    
    @FXML
    public void initTableView() {
    	ObservableList<Client> clientsList = FXCollections.observableArrayList(ClientFacade.listClient());
    	
		clientsTable.setItems(clientsList);
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

}