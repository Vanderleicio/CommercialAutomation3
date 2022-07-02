package app.controllers.reportControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
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
import app.model.reports.ManagementReportSale;
import app.model.reports.ManagementReportStock;

public class ManagementReportController implements Initializable{
	
	@FXML
	private DatePicker dateInitial;
	
	@FXML
	private DatePicker dateEnd;
	
	@FXML
	private TableView<Item> tableSale;

	@FXML
    private TableColumn<Item, String> idSaleCol;
	
	@FXML
    private TableColumn<Item, String> nameSaleCol;
	
	@FXML
	private TableView<Product> tableStock;

    @FXML
    private TableColumn<Product, String> idStockCol;

    @FXML
    private TableColumn<Product, String> nameStockCol;

    @FXML
    private Button stock;

    @FXML
    private Button providers;

    @FXML
    private Button sales;
    
    private Product prodSelected;
    
    private Item itemSelected;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	initTableView();
    	stock.setDisable(true);
    	sales.setDisable(true);
	}
    
    @FXML
    public void generateReportProvider(ActionEvent event) throws IdDoesntExist, EntitiesNotRegistred {
    	ManagementReportProvider newReport = new ManagementReportProvider();
    	newReport.generatePDF();
    }
    
    @FXML
    public void selectProd(MouseEvent event) {
    	prodSelected = tableStock.getSelectionModel().getSelectedItem();
    	if (prodSelected != null) {
    		stock.setDisable(false);
    	}
    }
    
    @FXML
    public void selectItem(MouseEvent event) {
    	itemSelected = tableSale.getSelectionModel().getSelectedItem();
    	LocalDate after = dateEnd.getValue();
    	LocalDate before = dateInitial.getValue();
    	if((after != null) && (before != null)) {
    		sales.setDisable(false);
    	}
    }
    
    @FXML
    public void afterSelected(ActionEvent event) {
    	LocalDate after = dateEnd.getValue();
    	if((after != null) && (itemSelected != null)) {
    		sales.setDisable(false);
    	}
    }
    
    @FXML
    public void beforeSelected(ActionEvent event) {
    	LocalDate before = dateInitial.getValue();
    	if((before != null) && (itemSelected != null)) {
    		sales.setDisable(false);
    	}
    }
    
    @FXML
    public void generateReportSales(ActionEvent event) {
    	System.out.println("OKOK");
    	System.out.println(dateInitial.getValue() + "---" + dateEnd.getValue());
    	ManagementReportSale newSale = new ManagementReportSale();
    	try {
    		newSale.generatePDF(dateInitial.getValue(), dateEnd.getValue(), itemSelected.getId());
    	} catch (IdDoesntExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    public void generateReportStock(ActionEvent event) {
    	ManagementReportStock newReport = new ManagementReportStock();
    	try {
			newReport.generatePDF(prodSelected.getId());
		} catch (IdDoesntExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    
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

