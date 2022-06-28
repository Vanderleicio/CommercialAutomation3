package app.controllers.menuControllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
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

public class compItemController implements Initializable{
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TableView<Product> compTable;
    
    @FXML
    private TableColumn<Product, String> idCol;
    
    @FXML
    private TableColumn<Product, String> nameCol;
    
    @FXML
    private TableColumn<Product, Integer> qnttCol;
    
    @FXML
    private TableColumn<Product, String> valCol;
    
    @FXML
    private TableColumn<Product, String> provCol;
    
    private Item selected = MenuFacade.chosenItem();
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
	}
    
    public void initTableView() {
    	ArrayList<Product> prods = MenuFacade.getItemProds(selected.getId());
    	
    	ObservableList<Product> productsList = FXCollections.observableArrayList(prods);
    	
    	compTable.setItems(productsList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		qnttCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		valCol.setCellValueFactory(new PropertyValueFactory<>("validity"));
		provCol.setCellValueFactory(new PropertyValueFactory<>("provider"));
    
    }
}
