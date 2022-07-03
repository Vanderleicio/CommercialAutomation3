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
/**Controller criar item
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class manageItemController implements Initializable{
	/**
	 * Botao criar novo
	 */
    @FXML
    private Button buttonCreate;
    /**
     * Texto de nome
     */
    @FXML
    private TextField nameTxtFld;
    /**
     * Texto do preco
     */
    @FXML
    private TextField priceTxtFld;
    /**
     * Texto da quantidade
     */
    @FXML
    private TextField qntdTxtFld;
    /**
     * Texto categoria
     */
    @FXML
    private TextField categoryTxtFld;
    /**
     * Texto quantidade
     */
    @FXML
    private TextField qnttTxtFld;
    /**
     * Texto descricao
     */
    @FXML
    private TextArea descTxtArea;
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
     * Texto de alerta
     */
    @FXML
    private Label alertLabel;
    /**
     * Quantidade
     */
    @FXML
    private Label qnttLabel;
    /**
     * Tabela de produtos
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
     * Coluna quantidade produto
     */
    @FXML
    private TableColumn<Product, Integer> qnttDisCol;
    /**
     * Coluna validade produtos                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
     */
    @FXML
    private TableColumn<Product, String> valProdCol;
    /**
     * Tabela composidao tabela
     */
    @FXML
    private TableView<Product> prodsCompTable;
    /**
     * Coluna composicao id
     */
    @FXML
    private TableColumn<Product, String> idCompCol;
    /**
     * Coluna composicao nome
     */
    @FXML
    private TableColumn<Product, String> nameCompCol;
    
    /**
     * Coluna composicao validade
     */
    @FXML
    private TableColumn<Product, String> valCompCol;
    /**
     * Quantidade lista
     */
    @FXML
    private ListView<Integer> qnttListView;
    /**
     * Item selecionado
     */
    private Item selected = MenuFacade.chosenItem();
    /**
     * Lista de produtos
     */
    ArrayList<Product> prodsList = new ArrayList<Product>();
    /**
     * Lista de quantidade
     */
    ArrayList<Integer> qnttList = new ArrayList<Integer>();
    /**
     * Inicializador
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (selected != null) {
			setItemData();
		}
		setTables();
		buttonAdd.setDisable(true);
		buttonRemove.setDisable(true);
	}
	/**
	 * iniciando labels
	 */
	public void setLabels(){
		alertLabel.setText("");
		qnttLabel.setVisible(false);
	}
	/**
	 * atualizando tabelas
	 */
	public void refreshTables() {
		setTables();
		qnttListView.refresh();
		prodsCompTable.refresh();
		prodsTable.refresh();
	}
	/**
	 * Inserinddo dados nas tabelas
	 */
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
	
	/**
	 * Inserindo dados nas variaveis
	 */
	public void setItemData() {
		setLabels();
		nameTxtFld.setText(selected.getName());
		priceTxtFld.setText(selected.getPrice().toString());
		descTxtArea.setText(selected.getDescription());
		categoryTxtFld.setText(selected.getCategoryItems());
		prodsList = MenuFacade.getItemProds(selected.getId());
		qnttList = MenuFacade.getItemQntt(selected.getId());
	}
	/**
	 * Adicionando produtos
	 * @param event
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
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
	/**
	 * Removendo produtos
	 * @param event
	 */
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
	
	/**
	 * Nova quantidade atualizacao
	 * @param event
	 */
	@FXML
	public void newQnt(ActionEvent event) {
		setLabels();
		Integer selectQnt = Integer.valueOf(qnttTxtFld.getText());
		Integer pos = prodsCompTable.getSelectionModel().getSelectedIndex();
		qnttList.set(pos, selectQnt);
		refreshTables();
	}
	
	/**
	 * Selecionando um item
	 * @param event
	 */
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
    /**
     * Composicao selecionada
     */
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
    /**
     * Criando um item novo
     * @param event
     */
	@FXML
	void createItem(ActionEvent event){
		try {
			String name = nameTxtFld.getText();
			String price = priceTxtFld.getText();
			String description = descTxtArea.getText();
			String category = categoryTxtFld.getText();
			HashMap<String, Integer> composition = MenuFacade.doNewComposition(prodsList, qnttList);
			
			if(composition.size() == 0) {
				alertLabel.setText("Selecione pelo menos um produto!");
			} else {
				if (selected == null) {
					MenuFacade.createItem(name, description, new BigDecimal(price), category, composition);
				} else {
					MenuFacade.editItem(selected.getId(), name, description, new BigDecimal(price), category, composition);
				}
    		
    	    	Stage stage = (Stage) buttonCreate.getScene().getWindow();
    	    	stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    	    	stage.close();
			}
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