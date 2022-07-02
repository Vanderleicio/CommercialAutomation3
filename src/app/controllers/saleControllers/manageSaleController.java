package app.controllers.saleControllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import app.model.exceptions.EmptyStringException;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.exceptions.NotEnoughStock;
import app.model.facades.ClientFacade;
import app.model.facades.MenuFacade;
import app.model.facades.ProductFacade;
import app.model.facades.SaleFacade;
import app.model.models.Client;
import app.model.models.Item;
import app.model.models.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class manageSaleController implements Initializable{

    @FXML
    private Button buttonCreate;

    @FXML
    private DatePicker dayDatePicker;
    
    @FXML
    private TextField hourTxtFld;
    
    @FXML
    private TextField payMethTxtFld;
    
    @FXML
    private Button buttonAdd;
    
    @FXML
    private Button buttonRemove;
    
    @FXML
    private Label alertLabel;
    
    @FXML
    private Label editAlert;
    
    @FXML
    private TableView<Item> itemsTable;
    
    @FXML
    private TableColumn<Item, String> idCol;
    
    @FXML
    private TableColumn<Item, String> nameCol;
    
    @FXML
    private TableColumn<Item, String> categCol;
    
    @FXML
    private TableColumn<Item, String> descCol;
    
    @FXML
    private TableView<Item> itemsCompTable;
    
    @FXML
    private TableColumn<Item, String> idCompCol;
    
    @FXML
    private TableColumn<Item, String> nameCompCol;
    
    @FXML
    private TableColumn<Item, String> categCompCol;
    
    @FXML
    private TableView<Client> clientTable;
    
    @FXML
    private TableColumn<Client, String> clientIdCol;
    
    @FXML
    private TableColumn<Client, String> clientNameCol;
    
    private Sale selected = SaleFacade.chosenSale();
    
    private ArrayList<Client> clients = ClientFacade.listClient();
    
    private ArrayList<Item> itemsList = new ArrayList<Item>();
    
    private boolean editUpdateProds = true;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (selected != null) {
			setSaleData();
			itemsList = SaleFacade.getSaleItems(selected.getId());
			editAlert.setVisible(true);
			editUpdateProds = false;
		}
		setTables();
		buttonAdd.setDisable(true);
		buttonRemove.setDisable(true);
	}
	
	public void refreshTables() {
		setTables();
		itemsCompTable.refresh();
		itemsTable.refresh();
	}
	
	public void setTables() {
		// Tabela de items disponíveis.
	    ObservableList<Item> itemList = FXCollections.observableArrayList(MenuFacade.listItem());
	    	
	    itemsTable.setItems(itemList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		categCol.setCellValueFactory(new PropertyValueFactory<>("categoryItems"));
		descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		
		// Tabela de items usados nessa venda.
		ObservableList<Item> itemsCompList = FXCollections.observableArrayList(itemsList);
		
	    itemsCompTable.setItems(itemsCompList);
		idCompCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCompCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		categCompCol.setCellValueFactory(new PropertyValueFactory<>("categoryItems"));
		
		// Tabela de clientes.
		
		ObservableList<Client> clientsList = FXCollections.observableArrayList(clients);
		
		clientTable.setItems(clientsList);
		
		clientIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		clientNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
	}
	
	
	public void setSaleData() {
		dayDatePicker.setValue(selected.getDay());
		hourTxtFld.setText(selected.getHour().toString());
		payMethTxtFld.setText(selected.getPaymentMethod());
	}
	
	@FXML
	public void addItem(ActionEvent event) throws IdDoesntExist, EntitiesNotRegistred {
		editUpdateProds = true;
		Item selecItem = itemsTable.getSelectionModel().getSelectedItem();
		
		itemsList.add(selecItem);
		
		refreshTables();
	}
	
	@FXML
	public void removeItem(ActionEvent event) {
		editUpdateProds = true;
		Item selecItem = itemsCompTable.getSelectionModel().getSelectedItem();
		
		itemsList.remove(selecItem);
		
		refreshTables();
	}
	
    @FXML
    public void itemSelected(MouseEvent event) {
    	Item selecItem = itemsTable.getSelectionModel().getSelectedItem();
    	if (selecItem != null) {
    		buttonAdd.setDisable(false);
    		buttonRemove.setDisable(true);
    	}
    }
    
    @FXML
    public void itemCompSelected(MouseEvent event) {
    	Item selecItem = itemsCompTable.getSelectionModel().getSelectedItem();
    	if (selecItem != null) {
    		buttonAdd.setDisable(true);
    		buttonRemove.setDisable(false);
    	}
    }
    
	@FXML
	void createSale(ActionEvent event){
		try {
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
		    		.withResolverStyle(ResolverStyle.STRICT);
			
			LocalDate day = dayDatePicker.getValue();
			
			String payMeth = payMethTxtFld.getText();
			
			String hour = hourTxtFld.getText();
			LocalTime hourForm = LocalTime.parse(hour, timeFormatter);
			
			if(clientTable.getSelectionModel().getSelectedItem() != null) {
				Client client = clientTable.getSelectionModel().getSelectedItem();
				ArrayList<Item> composition = itemsList;
				
				if (editUpdateProds) {
					HashMap<String, Integer> allProductsUsed = SaleFacade.getAllProductsUsed(composition);
					ProductFacade.updateStock(allProductsUsed);
				}
				
				if (selected == null) {
					SaleFacade.createSale(day, hourForm, payMeth, composition, client);
				} else {
					SaleFacade.editSale(selected.getId(), day, hourForm, payMeth, composition, client);
				}
	    		
	    	    Stage stage = (Stage) buttonCreate.getScene().getWindow();
	    	    stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	    	    stage.close();
			} else {
				alertLabel.setText("Selecione um cliente.");
			}
		} catch (IdDoesntExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			alertLabel.setText("Valores digitados são inválidos");
		} catch (EmptyStringException e) {
			alertLabel.setText("Campos vazios!");
		} catch (NotEnoughStock e) {
			alertLabel.setText("Produtos insuficientes para realizar a venda!");
		} 
	}
}