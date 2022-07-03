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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.model.exceptions.EmptyStringException;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.ExistentNicknameException;
import app.model.exceptions.IdDoesntExist;
import app.model.facades.ClientFacade;
import app.model.facades.UserFacade;
import app.model.models.Client;
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
/**Controller adicionar cliente
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class manageClientController implements Initializable{
	/**
	 * Botao criar
	 */
    @FXML
    private Button buttonCreate;
    /**
     * Texto alerta
     */
    @FXML
    private Label alertNick;
    /**
     * Texto nome
     */
    @FXML
    private TextField nameTxtFld;
    /**
     * Texto cpf
     */
    @FXML
    private TextField cpfTxtFld;
    /**
     * Texto email
     */
    @FXML
    private TextField emailTxtFld;
    /**
     * Texto numero de telefone
     */
    @FXML
    private TextField phoneTxtFld;
    /**
     * Cliente seleionado
     */
    private Client selected = ClientFacade.chosenClient();
    /**
     * Inicializador
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		if (selected != null) {
			setClientData();
		}
	}
	/**
	 * Inserindo dados nas variaveis
	 */
	public void setClientData() {
		nameTxtFld.setText(selected.getName());
		cpfTxtFld.setText(selected.getCpf());
		emailTxtFld.setText(selected.getEmail());
		phoneTxtFld.setText(selected.getPhoneNumber());
	}
	/**
	 * Criando cliente
	 * @param event
	 */
	@FXML
	void create(ActionEvent event){
		String name = nameTxtFld.getText();
		String cpf = cpfTxtFld.getText();
		String email = emailTxtFld.getText();
		String phone = phoneTxtFld.getText();
		
		try {
			
			if (selected == null) {
				ClientFacade.createClient(name, cpf, email, phone);
			} else {
				ClientFacade.editClient(selected.getId(), name, cpf, email, phone);
			}
    		
    	    Stage stage = (Stage) buttonCreate.getScene().getWindow();
    	    stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    	    stage.close();
    	    
		}  catch (IdDoesntExist e) {
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