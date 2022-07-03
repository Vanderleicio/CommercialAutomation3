package app.controllers.userControllers;

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

import app.model.facades.UserFacade;
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
/** Controller responsavel por gerenciar usuario
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class ManagementUsersController implements Initializable{
	/**
	 * Recursos
	 */
    @FXML
    private ResourceBundle resources;
    /**
     * URL de localizacao
     */
    @FXML
    private URL location;
    /**
     * Texto
     */
    @FXML
    private Label currentUserLabel;
    /**
     * Botao adicionar
     */
    @FXML
    private Button buttonAddUser;
    /**
     * Botao editar
     */
    @FXML
    private Button buttonEditUser;
    /**
     * Botao listar
     */
    @FXML
    private Button buttonListUser;
    /**
     * Botao remover
     */
    @FXML
    private Button buttonRemoveUser;
    /**
     * Tabela
     */
    @FXML
    private TableView<User> usersTable;
    /**
     * Coluna id da tabela
     */
    @FXML
    private TableColumn<User, String> idUCol;
    /**
     * Coluna nome da tela
     */
    @FXML
    private TableColumn<User, String> nameUCol;
    /**
     * Coluna apelido da tabela
     */
    @FXML
    private TableColumn<User, String> nickCol;
    /**
     * Coluna categoria da tabela
     */
    @FXML
    private TableColumn<User, String> categoryCol;
    /**
     * Lista usuario
     */
    @FXML
    private ListView<String> listUsers;
    /**
     * Inicializador da pagina
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
		buttonEditUser.setDisable(true);
		buttonRemoveUser.setDisable(true);
	}
	
    /** Adicionar usuario
     * 
     * @param event
     */
    @FXML
    public void addUser(ActionEvent event) {
    	UserFacade.chooseAUser(null);
    	createScreens("CreateUser.fxml");
    }
    /** Editar usuario
     * 
     * @param event
     */
    @FXML
    public void editUser(ActionEvent event) {
    	User selected = usersTable.getSelectionModel().getSelectedItem();
    	UserFacade.chooseAUser(selected.getId());
    	createScreens("CreateUser.fxml");
    }
    /** Remover usuario
     * 
     * @param event
     */
    @FXML
    public void removeUser(ActionEvent event) {
    	User selected = usersTable.getSelectionModel().getSelectedItem();
    	UserFacade.chooseAUser(selected.getId());
    	createScreens("DeleteUser.fxml");
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
                	usersTable.refresh();
                	initTableView();
                }
            });
	        addStage.show(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /** Seleciona usuario
     * 
     * @param event
     */
    @FXML
    public void userSelected(MouseEvent event) {
    	
    	User selected = usersTable.getSelectionModel().getSelectedItem();
    	
    	if (selected != null) {
	    	if (!selected.getId().equals(UserFacade.getIdCurrentUser())) {
	    		currentUserLabel.setVisible(false);
	    		buttonRemoveUser.setDisable(false);
	    	} else {
	    		buttonRemoveUser.setDisable(true);
	    		currentUserLabel.setVisible(true);
	    	}
	    	buttonEditUser.setDisable(false);
    	}
    }
    /**
     * Inicializar a tabela
     */
    public void initTableView() {
    	ObservableList<User> usersList = FXCollections.observableArrayList(UserFacade.listUser());
    	
    	usersTable.setItems(usersList);
		
		idUCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameUCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		nickCol.setCellValueFactory(new PropertyValueFactory<>("nickname"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
    }
}
