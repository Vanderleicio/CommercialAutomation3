package app.controllers.reportControllers;

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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.facades.MenuFacade;
import app.model.facades.ProductFacade;
import app.model.facades.ProviderFacade;
import app.model.facades.SaleFacade;
import app.model.models.Item;
import app.model.models.Product;
import app.model.models.Sale;
import app.model.reports.ManagementReportProvider;
import app.model.reports.ManagementReportSale;
import app.model.reports.ManagementReportStock;
/**Controller gerador pdf
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class ManagementReportController implements Initializable{
	/**
	 * Valor de data
	 */
	@FXML
	private DatePicker dateInitial;
	/**
	 * Valor de data
	 */
	@FXML
	private DatePicker dateEnd;
	/**
	 * Tabela vendas
	 */
	@FXML
	private TableView<Item> tableSale;
	/**
	 * Coluna id vendas
	 */
	@FXML
    private TableColumn<Item, String> idSaleCol;
	/**
	 * Coluna nome vendas
	 */
	@FXML
    private TableColumn<Item, String> nameSaleCol;
	/**
	 * Tabela produto
	 */
	@FXML
	private TableView<Product> tableStock;
	/**
	 * Coluna id produto
	 */
    @FXML
    private TableColumn<Product, String> idStockCol;
    /**
     * Coluna nome produto
     */
    @FXML
    private TableColumn<Product, String> nameStockCol;
	/**
	 * Botao estoque
	 */
    @FXML
    private Button stock;
	/**
	 * Botao fornecedores
	 */
    @FXML
    private Button providers;
	/**
	 * Botao vendas
	 */
    @FXML
    private Button sales;
    /**
     * Produto selecionado
     */
    private Product prodSelected;
    /**
     * Item selecionado
     */
    private Item itemSelected;
    /**
     * Inicializando
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	initTableView();
    	stock.setDisable(true);
    	sales.setDisable(true);
	}
    /**
     * Gerando relatorio fornecedores em pfd
     * @param event
     * @throws IdDoesntExist
     * @throws EntitiesNotRegistred
     */
    @FXML
    public void generateReportProvider(ActionEvent event) throws IdDoesntExist, EntitiesNotRegistred {
    	ManagementReportProvider newReport = new ManagementReportProvider();
    	newReport.generatePDF();
    }
    /**
     * Selecionando produto
     * @param event
     */
    @FXML
    public void selectProd(MouseEvent event) {
    	prodSelected = tableStock.getSelectionModel().getSelectedItem();
    	if (prodSelected != null) {
    		stock.setDisable(false);
    	}
    }
    /**
     * Inserindo dados as variaveis
     * @param event
     */
    @FXML
    public void selectItem(MouseEvent event) {
    	itemSelected = tableSale.getSelectionModel().getSelectedItem();
    	LocalDate after = dateEnd.getValue();
    	LocalDate before = dateInitial.getValue();
    	if((after != null) && (before != null)) {
    		sales.setDisable(false);
    	}
    }
    /**
     * Inserindo valor de data
     * @param event
     */
    @FXML
    public void afterSelected(ActionEvent event) {
    	LocalDate after = dateEnd.getValue();
    	if((after != null) && (itemSelected != null)) {
    		sales.setDisable(false);
    	}
    }
    /**
     * Inserindo valor de data
     * @param event
     */
    @FXML
    public void beforeSelected(ActionEvent event) {
    	LocalDate before = dateInitial.getValue();
    	if((before != null) && (itemSelected != null)) {
    		sales.setDisable(false);
    	}
    }
    /**
     * Chamando relatorio vendas em pdf
     * @param event
     */
    @FXML
    public void generateReportSales(ActionEvent event) {
    	ManagementReportSale newSale = new ManagementReportSale();
    	try {
    		newSale.generatePDF(dateInitial.getValue(), dateEnd.getValue(), itemSelected.getId());
    	} catch (IdDoesntExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    /**
     * Gerando relatorio estoque em pdf
     * @param event
     */
    @FXML
    public void generateReportStock(ActionEvent event) {
    	ManagementReportStock newReport = new ManagementReportStock();
    	try {
			newReport.generatePDF(prodSelected.getId());
		} catch (IdDoesntExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    /**
     * Inserindo dados na tabela
     */
	public void initTableView() {
    	//Vendas
    	ObservableList<Item> itemsList = FXCollections.observableArrayList(MenuFacade.listItem());
    	
    	tableSale.setItems(itemsList);
    	idSaleCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	nameSaleCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    	
    	//Estoque
    	
    	ObservableList<Product> productsList = FXCollections.observableArrayList(ProductFacade.listProduct());
    	
		tableStock.setItems(productsList);
    	idStockCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	nameStockCol.setCellValueFactory(new PropertyValueFactory<>("name"));
	}
	

}

