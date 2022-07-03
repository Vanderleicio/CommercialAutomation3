package app.controllers.menuControllers;

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

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
/**Controller gerenciamento do item
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class ManagementItemController implements Initializable{
	/**
	 * Pacote de recursos
	 */
    @FXML
    private ResourceBundle resources;
    /**
     * url localizacao
     */
    @FXML
    private URL location;
    /**
     * Botao adicionar
     */
    @FXML
    private Button buttonAdd;
    /**
     * Botao editar
     */
    @FXML
    private Button buttonEdit;
    /**
     * Botao composicao
     */
    @FXML
    private Button buttonComp;
    /**
     * Botao remover
     */
    @FXML
    private Button buttonRemove;
    /**
     * Tabela item
     */
    @FXML
    private TableView<Item> itemsTable;
    /**
     * Coluna id item
     */
    @FXML
    private TableColumn<Item, String> idCol;
    /**
     * Coluna nome item
     */
    @FXML
    private TableColumn<Item, String> nameCol;
    /**
     * Coluna preco item
     */
    @FXML
    private TableColumn<Item, BigDecimal> priceCol;
    /**
     * Coluna categoria item
     */
    @FXML
    private TableColumn<Item, Integer> categoryCol;
    /**
     * Coluna descricao item
     */
    @FXML
    private TableColumn<Item, String> descCol;
    /**
     * Inicializador
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
		buttonEdit.setDisable(true);
		buttonRemove.setDisable(true);
		buttonComp.setDisable(true);
	}
	
    /**
     * Adicionar item
     * @param event
     */
    @FXML
    public void add(ActionEvent event) {
    	MenuFacade.chooseAItem(null);
    	createScreens("ManageItem.fxml");
    }
    /**
     * Editar item
     * @param event
     */
    @FXML
    public void edit(ActionEvent event) {
    	Item selected = itemsTable.getSelectionModel().getSelectedItem();
    	MenuFacade.chooseAItem(selected.getId());
    	createScreens("ManageItem.fxml");
    }
    /**
     * Remover item
     * @param event
     */
    @FXML
    public void remove(ActionEvent event) {
    	Item selected = itemsTable.getSelectionModel().getSelectedItem();
    	MenuFacade.chooseAItem(selected.getId());
    	createScreens("DeleteItem.fxml");
    }
    /**
     * Listar componentes
     * @param event
     */
    @FXML
    public void listComp(ActionEvent event) {
    	Item selected = itemsTable.getSelectionModel().getSelectedItem();
    	MenuFacade.chooseAItem(selected.getId());
    	createScreens("CompItem.fxml");
    }
    /**
     * Criar cena
     * @param viewName
     */
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
                	itemsTable.refresh();
                	initTableView();
                }
            });
	        addStage.show(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * Selecionar item
     * @param event
     */
    @FXML
    public void itemSelected(MouseEvent event) {
    	Item selected = itemsTable.getSelectionModel().getSelectedItem();
    	if (selected != null) {
    		buttonRemove.setDisable(false);
    		buttonEdit.setDisable(false);
    		buttonComp.setDisable(false);
    	}
    }
    /**
     * Inserir dados na tabela
     */
    public void initTableView() {
    	ObservableList<Item> itemsList = FXCollections.observableArrayList(MenuFacade.listItem());
    	
    	itemsTable.setItems(itemsList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("categoryItems"));
		descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    
    }
}
