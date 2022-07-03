package app.controllers.productControllers;

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

import app.model.facades.ProductFacade;
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
/**Controller gerenciamento de produtos
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class ManagementProductControllers implements Initializable{
	/**
	 * Pacote de recursos
	 */
    @FXML
    private ResourceBundle resources;
    /**
     * url local
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
     * Botao listar
     */
    @FXML
    private Button buttonList;
    /**
     * Botao remover
     */
    @FXML
    private Button buttonRemove;
    /**
     * Tabela
     */
    @FXML
    private TableView<Product> prodsTable;
    /**
     * Coluna id produto
     */
    @FXML
    private TableColumn<Product, String> idCol;
    /**
     * Coluna nome produto
     */
    @FXML
    private TableColumn<Product, String> nameCol;
    /**
     * Coluna preco produto
     */
    @FXML
    private TableColumn<Product, BigDecimal> priceCol;
    /**
     * Coluna quantidade produto
     */
    @FXML
    private TableColumn<Product, Integer> quantityCol;
    /**
     * Coluna validade produto
     */
    @FXML
    private TableColumn<Product, String> validityCol;
    /**
     * Coluna fornecedor produto
     */
    @FXML
    private TableColumn<Product, String> providerCol;
    /**
     * Listar produtos
     */
    @FXML
    private ListView<String> listProds;
    /**
     * Inicializador
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
		buttonEdit.setDisable(true);
		buttonRemove.setDisable(true);
	}
	
    /**
     * Adicionar produto
     * @param event
     */
    @FXML
    public void add(ActionEvent event) {
    	ProductFacade.chooseAProduct(null);
    	createScreens("ManageProduct.fxml");
    }
    /**
     * Editar produto
     * @param event
     */
    @FXML
    public void edit(ActionEvent event) {
    	Product selected = prodsTable.getSelectionModel().getSelectedItem();
    	ProductFacade.chooseAProduct(selected.getId());
    	createScreens("ManageProduct.fxml");
    }
    /**
     * Remover produto
     * @param event
     */
    @FXML
    public void remove(ActionEvent event) {
    	Product selected = prodsTable.getSelectionModel().getSelectedItem();
    	ProductFacade.chooseAProduct(selected.getId());
    	createScreens("DeleteProd.fxml");
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
                	prodsTable.refresh();
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
     * Seleciona produto
     * @param event
     */
    @FXML
    public void prodSelected(MouseEvent event) {
    	Product selected = prodsTable.getSelectionModel().getSelectedItem();
    	if (selected != null) {
    		buttonRemove.setDisable(false);
    		buttonEdit.setDisable(false);
    	}
    }
    /*
     * Inserindo dados na tabela
     */
    public void initTableView() {
    	ObservableList<Product> productsList = FXCollections.observableArrayList(ProductFacade.listProduct());
    	
    	prodsTable.setItems(productsList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		validityCol.setCellValueFactory(new PropertyValueFactory<>("validity"));
		providerCol.setCellValueFactory(new PropertyValueFactory<>("provider"));
    
    }
}
