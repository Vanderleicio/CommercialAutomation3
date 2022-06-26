package app.controllers.providerControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.model.facades.ProviderFacade;
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

public class ManagementProvidersController implements Initializable{
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonList;

    @FXML
    private Button buttonRemove;
    
    @FXML
    private TableView<Provider> provsTable;
    
    @FXML
    private TableColumn<Provider, String> idCol;
    
    @FXML
    private TableColumn<Provider, String> nameCol;
    
    @FXML
    private TableColumn<Provider, String> cnpjCol;
    
    @FXML
    private TableColumn<Provider, String> addressCol;
    
    @FXML
    private ListView<String> listProvs;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
		buttonEdit.setDisable(true);
		buttonRemove.setDisable(true);
	}
	
    
    @FXML
    public void add(ActionEvent event) {
    	ProviderFacade.chooseAProvider(null);
    	createScreens("ManageProvider.fxml");
    }
    
    @FXML
    public void edit(ActionEvent event) {
    	Provider selected = provsTable.getSelectionModel().getSelectedItem();
    	ProviderFacade.chooseAProvider(selected.getId());
    	createScreens("ManageProvider.fxml");
    }
    
    @FXML
    public void remove(ActionEvent event) {
    	Provider selected = provsTable.getSelectionModel().getSelectedItem();
    	ProviderFacade.chooseAProvider(selected.getId());
    	createScreens("DeleteProv.fxml");
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
                	provsTable.refresh();
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
    public void provSelected(MouseEvent event) {
    	Provider selected = provsTable.getSelectionModel().getSelectedItem();
    	if (selected != null) {
    		buttonRemove.setDisable(false);
    		buttonEdit.setDisable(false);
    	}
    }
    
    public void initTableView() {
    	ObservableList<Provider> providersList = FXCollections.observableArrayList(ProviderFacade.listProvider());
    	
    	provsTable.setItems(providersList);
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		cnpjCol.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
    
    }
}
