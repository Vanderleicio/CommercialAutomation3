package app.controllers.menuControllers;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.exceptions.InvalidDateException;
import app.model.exceptions.InvalidQuantityException;
import app.model.facades.MenuFacade;
import app.model.facades.ProductFacade;
import app.model.facades.ProviderFacade;
import app.model.models.Item;
import app.model.models.Product;
import app.model.models.Provider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class manageItemController implements Initializable{

    @FXML
    private Button buttonCreate;

    @FXML
    private TextField nameTxtFld;
    
    @FXML
    private TextField priceTxtFld;
    
    @FXML
    private TextField qntdTxtFld;
    
    @FXML
    private Label alert;
    
    @FXML
    private TableView<Product> prodsTable;
    
    @FXML
    private TableColumn<Product, String> idCol;
    
    @FXML
    private TableColumn<Product, String> nameCol;
    
    @FXML
    private TableColumn<Product, Boolean> compCol;
    
    @FXML
    private TableColumn<Product, String> qnttCol;
    
    private Item selected = MenuFacade.chosenItem();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setTable();
		if (selected != null) {
			setProdData();
		}
	}
	
	public void setTable() {
	    ObservableList<Product> productsList = FXCollections.observableArrayList(ProductFacade.listProduct());
	    	
	    prodsTable.setItems(productsList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		compCol.setCellFactory(CheckBoxTableCell.forTableColumn(compCol));
		qnttCol.setCellFactory(TextFieldTableCell.forTableColumn());
	}
	
	
	public void setProdData() {
		nameTxtFld.setText(selected.getName());
		priceTxtFld.setText(selected.getPrice().toString());
		//qntdTxtFld.setText(String.valueOf(selected.getQuantity()));
		//validityDatePicker.setValue(selected.getValidity());
		//providerComboBox.setValue(selected.getProvider());
	}
	
	@FXML
	public void compoe(TableColumn.CellEditEvent<Product, String> event) {
		System.out.println("Ok");
	}
	
	@FXML
	void createItem(ActionEvent event){
		/*try {
			
			String name = nameTxtFld.getText();
			String price = priceTxtFld.getText();
			Provider provider = providerComboBox.getValue();
			LocalDate validity = validityDatePicker.getValue();
			int quantity = Integer.parseInt(qntdTxtFld.getText());
			
			if (selected == null) {
				ProductFacade.createProduct(name, new BigDecimal(price), validity, quantity, provider);
			} else {
				ProductFacade.editProduct(selected.getId(), name, new BigDecimal(price), validity, quantity, provider);
			}
    		
    	    Stage stage = (Stage) buttonCreate.getScene().getWindow();
    	    stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    	    stage.close();
    	    
		} catch (IdDoesntExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDateException e) {
			alert.setText("A data digitada é inválida!");
		} catch (NumberFormatException e) {
			alert.setText("Valores digitados são inválidos");
		} catch (InvalidQuantityException e) {
			alert.setText("A quantidade digitada é inválida");
		}*/
	}
}