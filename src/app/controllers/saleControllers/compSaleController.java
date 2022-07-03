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
/**Controller componentes de vendas
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class compSaleController implements Initializable{
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
     * Tabela
     */
    @FXML
    private TableView<Item> compTable;
    /**
     * Coluna id
     */
    @FXML
    private TableColumn<Item, String> idCol;
    /**
     * Coluna nome
     */
    @FXML
    private TableColumn<Item, String> nameCol;
    /**
     * Coluna descricao
     */
    @FXML
    private TableColumn<Item, String> descCol;
    /**
     * Coluna categoria
     */
    @FXML
    private TableColumn<Item, String> categCol;
    /**
     * Coluna preco
     */
    @FXML
    private TableColumn<Item, BigDecimal> priceCol;
    /**
     * Usuario selecionado
     */
    private Sale selected = SaleFacade.chosenSale();
    
    /**
     * Inicializando
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
	}
    /**
     * Inserindo dados na tabela
     */
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
