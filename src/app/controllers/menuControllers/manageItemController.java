package app.controllers.menuControllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import java.math.BigDecimal;

import app.model.exceptions.EmptyStringException;
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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.IntegerStringConverter;

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
    private TextField categoryTxtFld;
    
    @FXML
    private TextField qnttTxtFld;
    
    @FXML
    private TextArea descTxtArea;
    
    @FXML
    private Button buttonAdd;
    
    @FXML
    private Button buttonRemove;
    
    @FXML
    private Label alertLabel;
    
    @FXML
    private Label qnttLabel;
    
    @FXML
    private TableView<Product> prodsTable;
    
    @FXML
    private TableColumn<Product, String> idCol;
    
    @FXML
    private TableColumn<Product, String> nameCol;
    
    @FXML
    private TableColumn<Product, Integer> qnttDisCol;
    
    @FXML
    private TableColumn<Product, String> valProdCol;
    
    @FXML
    private TableView<Product> prodsCompTable;
    
    @FXML
    private TableColumn<Product, String> idCompCol;
    
    @FXML
    private TableColumn<Product, String> nameCompCol;
    
    
    @FXML
    private TableColumn<Product, String> valCompCol;
    
    @FXML
    private ListView<Integer> qnttListView;
    
    private Item selected = MenuFacade.chosenItem();
    
    ArrayList<Product> prodsList = new ArrayList<Product>();
    
    ArrayList<Integer> qnttList = new ArrayList<Integer>();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (selected != null) {
			setProdData();
			prodsList = MenuFacade.getItemProds(selected.getId());
			qnttList = MenuFacade.getItemQntt(selected.getId());
		}
		setTables();
		buttonAdd.setDisable(true);
		buttonRemove.setDisable(true);
	}
	
	public void setLabels(){
		alertLabel.setText("");
		qnttLabel.setVisible(false);
	}
	
	public void refreshTables() {
		setTables();
		qnttListView.refresh();
		prodsCompTable.refresh();
		prodsTable.refresh();
	}
	
	public void setTables() {
		// Tabela de produtos disponíveis.
	    ObservableList<Product> productsList = FXCollections.observableArrayList(ProductFacade.listProduct());
	    	
	    prodsTable.setItems(productsList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		qnttDisCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		valProdCol.setCellValueFactory(new PropertyValueFactory<>("validity"));
		
		
		// Tabela de produtos usados nesse item.
		

		
		ObservableList<Product> productsCompList = FXCollections.observableArrayList(prodsList);
		
	    prodsCompTable.setItems(productsCompList);
		idCompCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCompCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		valCompCol.setCellValueFactory(new PropertyValueFactory<>("validity"));
		
		// Lista da quantidade necessária
		
		ObservableList<Integer> qnttCompList = FXCollections.observableArrayList(qnttList);
		
		qnttListView.setItems(qnttCompList);
		
		qnttListView.setCellFactory(TextFieldListCell.forListView(new IntegerStringConverter()));
		
	}
	
	
	public void setProdData() {
		setLabels();
		nameTxtFld.setText(selected.getName());
		priceTxtFld.setText(selected.getPrice().toString());
		descTxtArea.setText(selected.getDescription());
		categoryTxtFld.setText(selected.getCategoryItems());
	}
	
	@FXML
	public void addProd(ActionEvent event) throws IdDoesntExist, EntitiesNotRegistred {
		setLabels();
		Product selecProd = prodsTable.getSelectionModel().getSelectedItem();
		
		if (!prodsList.contains(selecProd)) {
			prodsList.add(selecProd);
			qnttList.add(0);
		} else {
			alertLabel.setText("ESSE PRODUTO JÁ FOI ADICIONADO!");
		}
		refreshTables();
	}
	
	@FXML
	public void removeProd(ActionEvent event) {
		setLabels();
		Product selecProd = prodsCompTable.getSelectionModel().getSelectedItem();
		int pos = prodsCompTable.getSelectionModel().getSelectedIndex();
		
		prodsList.remove(selecProd);
		qnttList.remove(pos);
		
		refreshTables();
	}
	
	@FXML
	public void startEdit(MouseEvent event) {
		qnttLabel.setVisible(true);
	}
	
	
	@FXML
	public void newQnt(ActionEvent event) {
		setLabels();
		Integer selectQnt = Integer.valueOf(qnttTxtFld.getText());
		Integer pos = prodsCompTable.getSelectionModel().getSelectedIndex();
		qnttList.set(pos, selectQnt);
		refreshTables();
	}
	
	
    @FXML
    public void prodSelected(MouseEvent event) {
    	setLabels();
    	Product selecProd = prodsTable.getSelectionModel().getSelectedItem();
    	if (selecProd != null) {
    		buttonAdd.setDisable(false);
    		buttonRemove.setDisable(true);
    		qnttTxtFld.setDisable(true);
    	}
    }
    
    @FXML
    public void prodCompSelected(MouseEvent event) {
    	setLabels();
    	Product selecProd = prodsCompTable.getSelectionModel().getSelectedItem();
    	if (selecProd != null) {
    		buttonAdd.setDisable(true);
    		buttonRemove.setDisable(false);
    		qnttTxtFld.setDisable(false);
    	}
    }
    
	@FXML
	void createItem(ActionEvent event){
		try {
			String name = nameTxtFld.getText();
			String price = priceTxtFld.getText();
			String description = descTxtArea.getText();
			String category = categoryTxtFld.getText();
			HashMap<String, Integer> composition = MenuFacade.doNewComposition(prodsList, qnttList);
			System.out.println(composition.get("P3"));
			
			if (selected == null) {
				MenuFacade.createItem(name, description, new BigDecimal(price), category, composition);
			} else {
				MenuFacade.editItem(selected.getId(), name, description, new BigDecimal(price), category, composition);
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
		} catch (NumberFormatException e) {
			alertLabel.setText("Valores digitados são inválidos");
		} catch (InvalidQuantityException e) {
			alertLabel.setText("A quantidade digitada é inválida");
		} catch (EmptyStringException e) {
			alertLabel.setText("Campos vazios!");
		}
	}
}