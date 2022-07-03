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

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.facades.MenuFacade;
import app.model.facades.ProductFacade;
import app.model.facades.SaleFacade;
import app.model.models.Item;
import app.model.models.Product;
import app.model.models.Sale;
import app.model.reports.Receipt;
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
/**Controller gerenciar vendas
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class ManagementSaleController implements Initializable{
	/**
	 * Pacote de recursos
	 */
    @FXML
    private ResourceBundle resources;
    /**
     * url de localicao
     */
    @FXML
    private URL location;
    /**
     * Botao adicionar
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
     * Botao recibo
     */
    @FXML
    private Button buttonReceipt;
    /**
     * Tabela
     */
    @FXML
    private TableView<Sale> salesTable;
    /**
     * Coluna id
     */
    @FXML
    private TableColumn<Sale, String> idCol;
    /**
     * Coluna dia
     */
    @FXML
    private TableColumn<Sale, String> dayCol;
    /**
     * Coluna hora
     */
    @FXML
    private TableColumn<Sale, String> hourCol;
    /**
     * Coluna cliente
     */
    @FXML
    private TableColumn<Sale, String> clientCol;
    /**
     * Coluna preco
     */
    @FXML
    private TableColumn<Sale, BigDecimal> priceCol;
    /**
     * Coluna metodo de pagamento
     */
    @FXML
    private TableColumn<Sale, String> payMethCol;
    
    Sale selected;
    /**
     * Inicializando
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
		buttonEdit.setDisable(true);
		buttonRemove.setDisable(true);
		buttonComp.setDisable(true);
		buttonReceipt.setDisable(true);
	}
	
    /**
     * Chamando tela de adicionar
     * @param event
     */
    @FXML
    public void add(ActionEvent event) {
    	SaleFacade.chooseASale(null);
    	createScreens("ManageSale.fxml");
    }
    /**
     * Chamando tela de editar
     * @param event
     */
    @FXML
    public void edit(ActionEvent event) {
    	createScreens("ManageSale.fxml");
    }
    /**
     * Chamando tela de delete
     * @param event
     */
    @FXML
    public void remove(ActionEvent event) {
    	createScreens("DeleteSale.fxml");
    }
    /**
     * Chamando tela de composicao
     * @param event
     */
    @FXML
    public void listComp(ActionEvent event) {
    	createScreens("CompSale.fxml");
    }
    /**
     * Gerando recibo
     */
    @FXML
    public void receipt(ActionEvent event) {
    	Receipt recep = new Receipt();
    	try {
			recep.generatePDF(selected);
		} catch (IdDoesntExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * Criando telas
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
                	salesTable.refresh();
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
     * Selecionando venda
     * @param event
     */
    @FXML
    public void saleSelected(MouseEvent event) {
    	selected = salesTable.getSelectionModel().getSelectedItem();
    	if (selected != null) {
    		buttonRemove.setDisable(false);
    		buttonEdit.setDisable(false);
    		buttonComp.setDisable(false);
    		buttonReceipt.setDisable(false);
    		SaleFacade.chooseASale(selected.getId());
    	}
    }
    /**
     * Inserindo dados na tabela
     */
    public void initTableView() {
    	ObservableList<Sale> SalesList = FXCollections.observableArrayList(SaleFacade.listSale());
    	
    	salesTable.setItems(SalesList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		dayCol.setCellValueFactory(new PropertyValueFactory<>("day"));
		hourCol.setCellValueFactory(new PropertyValueFactory<>("hour"));
		clientCol.setCellValueFactory(new PropertyValueFactory<>("client"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("priceTotal"));
		payMethCol.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
    
    }
}
