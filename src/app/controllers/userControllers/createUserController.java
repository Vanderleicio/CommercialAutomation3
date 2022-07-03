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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.model.exceptions.EmptyStringException;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.ExistentNicknameException;
import app.model.exceptions.IdDoesntExist;
import app.model.facades.UserFacade;
import app.model.models.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/** Classe criando usuario
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class createUserController implements Initializable{
	/**
	 * Botao criar
	 */
    @FXML
    private Button buttonCreate;
    /**
     * Caixa combo de categoria de usuario
     */
    @FXML
    private ComboBox<String> userCategory;
    /**
     * Texto de alerta
     */
    @FXML
    private Label alertNick;
   /**
    * Texto apelido
    */
    @FXML
    private Label nickname;
    /**
     * Senha
     */
    @FXML
    private PasswordField userPassField;
    /**
     * Caixa de texto de nome de usuario
     */
    @FXML
    private TextField userNameTextField;
    /**
     * Caixa de texto de apelido do usuario
     */
    @FXML
    private TextField userNickTextField;
    /**
     * Usuario selecionado
     */
    private User selected = UserFacade.chosenUser();
    /**
     * Inicializador
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		setComboBox();
		if (selected != null) {
			setUserData();
		}
	}
	/**
	 * Adicionar as informações nas variaveis correspondentes
	 */
	public void setUserData() {
		userNickTextField.setText(selected.getNickname());
		userPassField.setText(selected.getPassword());
		userNameTextField.setText(selected.getName());
		userCategory.setValue(selected.getCategory());
	}
	/**
	 * Selecionando dado do combobox
	 */
	public void setComboBox() {
		ArrayList<String> categories = new ArrayList<String>();
		categories.add("Funcionário");
		categories.add("Gerente");
		userCategory.setItems(FXCollections.observableArrayList(categories));
	}
	/**
	 * Criar usuario
	 * @param event
	 */
	@FXML
	void createUser(ActionEvent event){
		String userNick = userNickTextField.getText();
		String userPass = new String(userPassField.getText());
		String userName = userNameTextField.getText();
		String userCateg = (String) userCategory.getValue();
		
		try {
			
			if (selected == null) {
				UserFacade.create(userNick, userPass, userName, userCateg);
			} else {
				UserFacade.editUser(selected.getId(), userNick, userPass, userName, userCateg);
			}
    		
    	    Stage stage = (Stage) buttonCreate.getScene().getWindow();
    	    stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    	    stage.close();
    	    
		} catch(ExistentNicknameException loginExcept) {
			alertNick.setText("O nick digitado já existe!");
    	} catch (IdDoesntExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyStringException e) {
			alertNick.setText("Campos vazios!");
		} 
	}
}