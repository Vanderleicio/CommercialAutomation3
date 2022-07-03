package app.controllers.clientControllers;

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

import app.model.facades.ClientFacade;
import app.model.facades.UserFacade;
import app.model.models.Client;
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
/**Controller gerenciar cliente
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class ManagementClientController implements Initializable {
    /**
     * Pacotes de recursos
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
    private Button buttonAddClient;
    /**
     * Botao editar
     */
    @FXML
    private Button buttonEditClient;
    /**
     * Botao remover
     */
    @FXML
    private Button buttonRemoveClient;
    /**
     * Coluna cliente cpf
     */
    @FXML
    private TableColumn<Client, String> cpfCol;
    /**
     * Coluna cliente email
     */
    @FXML
    private TableColumn<Client, String> emailCol;
    /**
     * Coluna cliente id
     */
    @FXML
    private TableColumn<Client, String> idCol;
    /**
     * Coluna cliente nome
     */
    @FXML
    private TableColumn<Client, String> nameCol;
    /**
     * Coluna cliente numero de telefone
     */
    @FXML
    private TableColumn<Client, String> phoneNumberCol;
	/**
	 * Tabela cliente
	 */
    @FXML
    private TableView<Client> clientsTable;
    
    /**
     * Inicializando
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
		buttonEditClient.setDisable(true);
		buttonRemoveClient.setDisable(true);
	}
	/**
	 * Adicionando cliente
	 * @param event
	 */
    @FXML
    void addClient(ActionEvent event) {
    	ClientFacade.chooseAClient(null);
    	createScreens("CreateClient.fxml");
    }
    /**
     * Editando cliente
     * @param event
     */
    @FXML
    void editClient(ActionEvent event) {
    	Client selected = clientsTable.getSelectionModel().getSelectedItem();
    	ClientFacade.chooseAClient(selected.getId());
    	createScreens("CreateClient.fxml");
    }
/**
 * Removendo cliente
 * @param event
 */
    @FXML
    void removeClient(ActionEvent event) {
    	Client selected = clientsTable.getSelectionModel().getSelectedItem();
    	ClientFacade.chooseAClient(selected.getId());
    	createScreens("DeleteClient.fxml");
    }
    /**
     * Criando cena
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
                	clientsTable.refresh();
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
     * Selecionando cliente
     * @param event
     */
    @FXML
    public void clientSelected(MouseEvent event) {
    	Client selected = clientsTable.getSelectionModel().getSelectedItem();
    	if (selected != null) {
    		buttonEditClient.setDisable(false);
    		buttonRemoveClient.setDisable(false);
    	}
    }	
    /**
     * Inserindo dados na tabela
     */
    @FXML
    public void initTableView() {
    	ObservableList<Client> clientsList = FXCollections.observableArrayList(ClientFacade.listClient());
    	
		clientsTable.setItems(clientsList);
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

}