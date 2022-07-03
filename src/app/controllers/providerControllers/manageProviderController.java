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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.model.exceptions.EmptyStringException;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.ExistentNicknameException;
import app.model.exceptions.IdDoesntExist;
import app.model.facades.ProviderFacade;
import app.model.facades.UserFacade;
import app.model.models.Provider;
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
/**Controller criar fornecedor
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class manageProviderController implements Initializable{
	/**
	 * Botao adicionar
	 */
    @FXML
    private Button buttonCreate;
    /**
     * Texto do nome
     */
    @FXML
    private TextField nameTextField;
    /**
     * Texto de alerta
     */
    @FXML
    private Label alert;
    /**
     * Texto de cnpj
     */
    @FXML
    private TextField cnpjTextField;
    /**
     * Texto de endereco
     */
    @FXML
    private TextField addressTextField;
    /**
     * Seleciona fornecedor
     */
    private Provider selected = ProviderFacade.chosenProvider();
    /**
     * Inicializando
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		if (selected != null) {
			setProvData();
		}
	}
	/**
	 * Inserindo dados nas variaveis
	 */
	public void setProvData() {
		nameTextField.setText(selected.getName());
		cnpjTextField.setText(selected.getCnpj());
		addressTextField.setText(selected.getAddress());
	}
	/**
	 * Criando fornecedor
	 * @param event
	 */
	@FXML
	void createProv(ActionEvent event){
		String name = nameTextField.getText();
		String cnpj = cnpjTextField.getText();
		String address = addressTextField.getText();
		
		try {
			
			if (selected == null) {
				ProviderFacade.createProvider(name, cnpj, address);
			} else {
				ProviderFacade.editProvider(selected.getId(), name, cnpj, address, null);
			}
    		
    	    Stage stage = (Stage) buttonCreate.getScene().getWindow();
    	    stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    	    stage.close();
    	    
		} catch (IdDoesntExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyStringException e) {
			alert.setText("Campos vazios!");
		} 
	}
}