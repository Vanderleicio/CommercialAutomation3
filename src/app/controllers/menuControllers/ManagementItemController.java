package app.controllers.menuControllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

import app.model.facades.MenuFacade;
import app.model.facades.ProductFacade;
import app.model.models.Item;
import app.model.models.Product;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ManagementItemController implements Initializable{
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonComp;

    @FXML
    private Button buttonRemove;
    
    @FXML
    private TableView<Item> itemsTable;
    
    @FXML
    private TableColumn<Item, String> idCol;
    
    @FXML
    private TableColumn<Item, String> nameCol;
    
    @FXML
    private TableColumn<Item, BigDecimal> priceCol;
    
    @FXML
    private TableColumn<Item, Integer> categoryCol;
    
    @FXML
    private TableColumn<Item, String> descCol;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
		buttonEdit.setDisable(true);
		buttonRemove.setDisable(true);
		buttonComp.setDisable(true);
	}
	
    
    @FXML
    public void add(ActionEvent event) {
    	MenuFacade.chooseAItem(null);
    	createScreens("ManageItem.fxml");
    }
    
    @FXML
    public void edit(ActionEvent event) {
    	Item selected = itemsTable.getSelectionModel().getSelectedItem();
    	MenuFacade.chooseAItem(selected.getId());
    	createScreens("ManageItem.fxml");
    }
    
    @FXML
    public void remove(ActionEvent event) {
    	Item selected = itemsTable.getSelectionModel().getSelectedItem();
    	MenuFacade.chooseAItem(selected.getId());
    	createScreens("DeleteItem.fxml");
    }
    
    @FXML
    public void listComp(ActionEvent event) {
    	
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
                	itemsTable.refresh();
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
    public void itemSelected(MouseEvent event) {
    	Item selected = itemsTable.getSelectionModel().getSelectedItem();
    	if (selected != null) {
    		buttonRemove.setDisable(false);
    		buttonEdit.setDisable(false);
    		buttonComp.setDisable(false);
    	}
    }
    
    public void initTableView() {
    	ObservableList<Item> itemsList = FXCollections.observableArrayList(MenuFacade.listItem());
    	
    	itemsTable.setItems(itemsList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("categoryItems"));
		descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    
    }
}
