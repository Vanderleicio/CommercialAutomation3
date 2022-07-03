package app.controllers.providerControllers;

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
import java.net.URL;
import java.util.ResourceBundle;

import app.model.facades.ProviderFacade;
import app.model.models.Provider;
import app.model.models.User;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**Controller gerenciar fornecedor
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class ManagementProvidersController implements Initializable{
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
    private TableView<Provider> provsTable;
    /**
     * Coluna fornecedor id
     */
    @FXML
    private TableColumn<Provider, String> idCol;
    /**
     * Coluna fornecedor nome
     */
    @FXML
    private TableColumn<Provider, String> nameCol;
    /**
     * Coluna fornecedor cnpj
     */
    @FXML
    private TableColumn<Provider, String> cnpjCol;
    /**
     * Coluna fornecedor endereco
     */
    @FXML
    private TableColumn<Provider, String> addressCol;
    /**
     * Lista de fornecedor
     */
    @FXML
    private ListView<String> listProvs;
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
     * Adicionar fornecedor
     * @param event
     */
    @FXML
    public void add(ActionEvent event) {
    	ProviderFacade.chooseAProvider(null);
    	createScreens("ManageProvider.fxml");
    }
    /**
     * Editar
     * @param event
     */
    @FXML
    public void edit(ActionEvent event) {
    	Provider selected = provsTable.getSelectionModel().getSelectedItem();
    	ProviderFacade.chooseAProvider(selected.getId());
    	createScreens("ManageProvider.fxml");
    }
    /**
     * Remover fornecedor
     * @param event
     */
    @FXML
    public void remove(ActionEvent event) {
    	Provider selected = provsTable.getSelectionModel().getSelectedItem();
    	ProviderFacade.chooseAProvider(selected.getId());
    	createScreens("DeleteProv.fxml");
    }
    /**
     * Criar cenas
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
                	provsTable.refresh();
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
     * Selecionando fornecedor
     * @param event
     */
    @FXML
    public void provSelected(MouseEvent event) {
    	Provider selected = provsTable.getSelectionModel().getSelectedItem();
    	if (selected != null) {
    		buttonRemove.setDisable(false);
    		buttonEdit.setDisable(false);
    	}
    }
    /**
     * Inserindo dados na tabela
     */
    public void initTableView() {
    	ObservableList<Provider> providersList = FXCollections.observableArrayList(ProviderFacade.listProvider());
    	
    	provsTable.setItems(providersList);
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		cnpjCol.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
    
    }
}
