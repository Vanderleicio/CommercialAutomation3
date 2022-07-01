package app.controllers.saleControllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

import app.model.facades.MenuFacade;
import app.model.facades.ProductFacade;
import app.model.facades.SaleFacade;
import app.model.models.Item;
import app.model.models.Product;
import app.model.models.Sale;
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

public class ManagementSaleController implements Initializable{
	
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
    private Button buttonReceipt;
    
    @FXML
    private TableView<Sale> salesTable;
    
    @FXML
    private TableColumn<Sale, String> idCol;
    
    @FXML
    private TableColumn<Sale, String> dayCol;
    
    @FXML
    private TableColumn<Sale, String> hourCol;
    
    @FXML
    private TableColumn<Sale, String> clientCol;
    
    @FXML
    private TableColumn<Sale, BigDecimal> priceCol;
    
    @FXML
    private TableColumn<Sale, String> payMethCol;
    
    Sale selected;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
		buttonEdit.setDisable(true);
		buttonRemove.setDisable(true);
		buttonComp.setDisable(true);
		buttonReceipt.setDisable(true);
	}
	
    
    @FXML
    public void add(ActionEvent event) {
    	SaleFacade.chooseASale(null);
    	createScreens("ManageSale.fxml");
    }
    
    @FXML
    public void edit(ActionEvent event) {
    	createScreens("ManageSale.fxml");
    }
    
    @FXML
    public void remove(ActionEvent event) {
    	createScreens("DeleteSale.fxml");
    }
    
    @FXML
    public void listComp(ActionEvent event) {
    	createScreens("CompSale.fxml");
    }
    
    @FXML
    public void receipt(ActionEvent event) {
    	
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
                	salesTable.refresh();
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
    public void saleSelected(MouseEvent event) {
    	selected = salesTable.getSelectionModel().getSelectedItem();
    	if (selected != null) {
    		buttonRemove.setDisable(false);
    		buttonEdit.setDisable(false);
    		buttonComp.setDisable(false);
    		buttonReceipt.setDisable(false);
    		SaleFacade.chooseASale(selected.getId());
    	}
    }
    
    public void initTableView() {
    	ObservableList<Sale> SalesList = FXCollections.observableArrayList(SaleFacade.listSale());
    	
    	salesTable.setItems(SalesList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		dayCol.setCellValueFactory(new PropertyValueFactory<>("day"));
		hourCol.setCellValueFactory(new PropertyValueFactory<>("hour"));
		clientCol.setCellValueFactory(new PropertyValueFactory<>("clientId"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("priceTotal"));
		payMethCol.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
    
    }
}
