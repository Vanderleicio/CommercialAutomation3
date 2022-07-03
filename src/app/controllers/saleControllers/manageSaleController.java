package app.controllers.saleControllers;

/***************************
Autores: Alana Sampaio e Vanderleicio Junior
Componente Curricular: Programacao II
Concluido em: 02/07/2022
Declaro que este codigo foi elaborado por mim de forma individual e nao contem nenhum
trecho de codigo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e paginas ou documentos eletronicos da Internet. Qualquer trecho de codigo
de outra autoria que nao a minha esta destacado com uma citacao para o autor e a fonte
do codigo, e estou ciente que estes trechos nao serao considerados para fins de avaliacao.
******************************/

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
/** Controller criar nova venda
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class manageSaleController implements Initializable{
	/**
	 * Botao criat
	 */
    @FXML
    private Button buttonCreate;
    /**
     * Data
     */
    @FXML
    private DatePicker dayDatePicker;
    /**
     * Hora
     */
    @FXML
    private TextField hourTxtFld;
    /**
     * Forma de pagamento
     */
    @FXML
    private TextField payMethTxtFld;
    /**
     * Botao adicionar
     */
    @FXML
    private Button buttonAdd;
    /**
     * Botao remover
     */
    @FXML
    private Button buttonRemove;
    /**
     * Texto alerta
     */
    @FXML
    private Label alertLabel;
    /**
     * Texto alerta
     */
    @FXML
    private Label editAlert;
    /**
     * Tabela
     */
    @FXML
    private TableView<Item> itemsTable;
    /**
     * Coluna id tabela
     */
    @FXML
    private TableColumn<Item, String> idCol;
    /**
     * Coluna nome tabela
     */
    @FXML
    private TableColumn<Item, String> nameCol;
    /**
     * Coluna categoria tabela
     */
    @FXML
    private TableColumn<Item, String> categCol;
    /**
     * Coluna descricao da tabela
     */
    @FXML
    private TableColumn<Item, String> descCol;
    /**
     * Coluna composicao da tabela
     */
    @FXML
    private TableView<Item> itemsCompTable;
    /**
     * Coluna id item da tabela
     */
    @FXML
    private TableColumn<Item, String> idCompCol;
    /**
     * Coluna nome item da tabela
     */
    @FXML
    private TableColumn<Item, String> nameCompCol;
    /**
     * Coluna categoria item da tabela
     */
    @FXML
    private TableColumn<Item, String> categCompCol;
    /**
     * Coluna cliente da tabela
     */
    @FXML
    private TableView<Client> clientTable;
    /**
     * Coluna cliente id
     */
    @FXML
    private TableColumn<Client, String> clientIdCol;
    /**
     * Coluna cliente nome
     */
    @FXML
    private TableColumn<Client, String> clientNameCol;
    /**
     * Usuario selecionado
     */
    private Sale selected = SaleFacade.chosenSale();
    /**
     * Lista cliente
     */
    private ArrayList<Client> clients = ClientFacade.listClient();
    /**
     * Lista item
     */
    private ArrayList<Item> itemsList = new ArrayList<Item>();
    
    private boolean editUpdateProds = true;
    /**
     * inicializador
     */
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
	/**
	 * Atualizar tabelas
	 */
	public void refreshTables() {
		setTables();
		itemsCompTable.refresh();
		itemsTable.refresh();
	}
	/**
	 * Inserindo listas nas tabelas
	 */
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
	
	/**
	 * Inserindo dados nas variaveis
	 */
	public void setSaleData() {
		dayDatePicker.setValue(selected.getDay());
		hourTxtFld.setText(selected.getHour().toString());
		payMethTxtFld.setText(selected.getPaymentMethod());
	}
	/**
	 * Adicionando item
	 * @param event
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	@FXML
	public void addItem(ActionEvent event) throws IdDoesntExist, EntitiesNotRegistred {
		editUpdateProds = true;
		Item selecItem = itemsTable.getSelectionModel().getSelectedItem();
		
		itemsList.add(selecItem);
		
		refreshTables();
	}
	/**
	 * Removendo item
	 * @param event
	 */
	@FXML
	public void removeItem(ActionEvent event) {
		editUpdateProds = true;
		Item selecItem = itemsCompTable.getSelectionModel().getSelectedItem();
		
		itemsList.remove(selecItem);
		
		refreshTables();
	}
	/**
	 * Celecionando item
	 * @param event
	 */
    @FXML
    public void itemSelected(MouseEvent event) {
    	Item selecItem = itemsTable.getSelectionModel().getSelectedItem();
    	if (selecItem != null) {
    		buttonAdd.setDisable(false);
    		buttonRemove.setDisable(true);
    	}
    }
    /** Selecione componente do item
     * 
     * @param event
     */
    @FXML
    public void itemCompSelected(MouseEvent event) {
    	Item selecItem = itemsCompTable.getSelectionModel().getSelectedItem();
    	if (selecItem != null) {
    		buttonAdd.setDisable(true);
    		buttonRemove.setDisable(false);
    	}
    }
    /**
     * Criando venda
     * @param event
     */
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