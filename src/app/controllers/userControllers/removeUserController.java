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

import app.model.exceptions.CurrentUserException;
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
/** Controller responsavel por remover usuario
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class removeUserController implements Initializable{
	/**
	 * Botao de confirmar
	 */
    @FXML
    private Button buttonConfirm;
    /**
     * Botao de cancelar
     */
    @FXML
    private Button buttonCancel;
    /**
     * Usuario selecionado
     */
    private User selected = UserFacade.chosenUser();
    /**
     * inicializador da pagina
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
	}
	/** Metodo que deleta usuario
	 * 
	 * @param event
	 */
	@FXML
	void deleteUser(ActionEvent event){
		try {
			UserFacade.delUser(selected.getId());
    	    Stage stage = (Stage) buttonConfirm.getScene().getWindow();
    	    stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    	    stage.close();
		} catch (CurrentUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IdDoesntExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** Cancelar acao
	 * 
	 * @param event
	 */
	@FXML
	void cancel(ActionEvent event) {
	    Stage stage = (Stage) buttonCancel.getScene().getWindow();
	    stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	    stage.close();
	}
}