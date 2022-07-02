package app.controllers.reportControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.facades.MenuFacade;
import app.model.facades.ProductFacade;
import app.model.facades.ProviderFacade;
import app.model.facades.SaleFacade;
import app.model.models.Item;
import app.model.models.Product;
import app.model.models.Sale;
import app.model.reports.ManagementReportProvider;

public class ManagementReportController {
	
	@FXML
	private DatePicker dateEnd;

	@FXML
	private DatePicker dateInitial;

	@FXML
    private TableColumn<Item, String> idSaleCol;

    @FXML
    private TableColumn<Product, String> idStockCol;

    @FXML
    private TableColumn<Item, String> nameSaleCol;

    @FXML
    private TableColumn<Product, String> nameStockCol;

    @FXML
    private TableView<Item> tableSale;

    @FXML
    private TableView<Product> tableStock;

    @FXML
    private Button buttonAddUser;

    @FXML
    private Button buttonEditUser;

    @FXML
    private Button buttonRemoveUser;
    
    private Sale selectedSale = SaleFacade.chosenSale();
    private Item selectedMenu = MenuFacade.chosenItem();
    private Product selectedProd = ProductFacade.chosenProduct();
    
    static ProviderFacade pf;
    static ProductFacade prf;
    
    public void initialize(URL arg0, ResourceBundle arg1) {
    	initTableView();
	}
    
    @FXML
    void generateReportProvider(MouseEvent event) throws IdDoesntExist, EntitiesNotRegistred {
    	ManagementReportProvider managP = new ManagementReportProvider();
    	managP.generatePDF(pf, prf);
    }

    @FXML
    void generateReportSales(MouseEvent event) {

    }

    @FXML
    void generateReportStock(MouseEvent event) {

    }
    
    @FXML
	public void setSaleData() {
		dateInitial.setValue(selectedSale.getDay());
		dateEnd.setValue(selectedSale.getDay());
		
	}
	
    @FXML
	public void initTableView() {
    	//Vendas
    	ObservableList<Item> itemsList = FXCollections.observableArrayList(MenuFacade.listItem());
    	
    	tableSale.setItems(itemsList);
    	idSaleCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	nameSaleCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    	
    	//Estoque
    	
    	ObservableList<Product> productsList = FXCollections.observableArrayList(ProductFacade.listProduct());
    	
		tableStock.setItems(productsList);
    	idStockCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	nameStockCol.setCellValueFactory(new PropertyValueFactory<>("name"));
	}
	

}

