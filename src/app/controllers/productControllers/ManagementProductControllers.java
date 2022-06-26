package app.controllers.productControllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

import app.model.facades.ProductFacade;
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

public class ManagementProductControllers implements Initializable{
	
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
    private TableView<Product> prodsTable;
    
    @FXML
    private TableColumn<Product, String> idCol;
    
    @FXML
    private TableColumn<Product, String> nameCol;
    
    @FXML
    private TableColumn<Product, BigDecimal> priceCol;
    
    @FXML
    private TableColumn<Product, Integer> quantityCol;
    
    @FXML
    private TableColumn<Product, String> validityCol;
    
    @FXML
    private TableColumn<Product, String> providerCol;
    
    @FXML
    private ListView<String> listProds;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
		buttonEdit.setDisable(true);
		buttonRemove.setDisable(true);
	}
	
    
    @FXML
    public void add(ActionEvent event) {
    	ProductFacade.chooseAProduct(null);
    	createScreens("ManageProduct.fxml");
    }
    
    @FXML
    public void edit(ActionEvent event) {
    	Product selected = prodsTable.getSelectionModel().getSelectedItem();
    	ProductFacade.chooseAProduct(selected.getId());
    	createScreens("ManageProduct.fxml");
    }
    
    @FXML
    public void remove(ActionEvent event) {
    	Product selected = prodsTable.getSelectionModel().getSelectedItem();
    	ProductFacade.chooseAProduct(selected.getId());
    	createScreens("DeleteProd.fxml");
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
                	prodsTable.refresh();
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
    public void prodSelected(MouseEvent event) {
    	Product selected = prodsTable.getSelectionModel().getSelectedItem();
    	if (selected != null) {
    		buttonRemove.setDisable(false);
    		buttonEdit.setDisable(false);
    	}
    }
    
    public void initTableView() {
    	ObservableList<Product> productsList = FXCollections.observableArrayList(ProductFacade.listProduct());
    	
    	prodsTable.setItems(productsList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		validityCol.setCellValueFactory(new PropertyValueFactory<>("validity"));
		providerCol.setCellValueFactory(new PropertyValueFactory<>("provider"));
    
    }
}
