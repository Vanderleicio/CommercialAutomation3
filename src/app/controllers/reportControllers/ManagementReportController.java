package app.controllers.reportControllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;

import java.net.URL;
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
	private ComboBox<Item> prodItem;

	@FXML
	private ComboBox<Product> prodStock;

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
		setComboBoxSale();
		if (prodItem != null) {
			setSaleData();
		}
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
    
	public void setSaleData() {
		dateInitial.setValue(selectedSale.getDay());
		dateEnd.setValue(selectedSale.getDay());
		
		prodItem.setValue(selectedMenu.getName());
	}
	
	public void setComboBoxSale() {
		prodItem.setItems(FXCollections.observableArrayList(MenuFacade.listItem()));
		//prodStock.setItems(FXCollections.observableArrayList(ProductFacade.listProduct()));
	}

}

