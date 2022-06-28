package app.controllers.saleControllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
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

public class compSaleController implements Initializable{
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TableView<Item> compTable;
    
    @FXML
    private TableColumn<Item, String> idCol;
    
    @FXML
    private TableColumn<Item, String> nameCol;
    
    @FXML
    private TableColumn<Item, String> descCol;
    
    @FXML
    private TableColumn<Item, String> categCol;
    
    @FXML
    private TableColumn<Item, BigDecimal> priceCol;
    
    private Sale selected = SaleFacade.chosenSale();
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
	}
    
    public void initTableView() {
    	ArrayList<Item> items = SaleFacade.getSaleItems(selected.getId());
    	
    	ObservableList<Item> itemsList = FXCollections.observableArrayList(items);
    	
    	compTable.setItems(itemsList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		categCol.setCellValueFactory(new PropertyValueFactory<>("categoryItems"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    
    }
}
