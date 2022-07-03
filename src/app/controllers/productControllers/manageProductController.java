package app.controllers.productControllers;

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

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import app.model.exceptions.EmptyStringException;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.exceptions.InvalidDateException;
import app.model.exceptions.InvalidQuantityException;
import app.model.facades.ProductFacade;
import app.model.facades.ProviderFacade;
import app.model.models.Product;
import app.model.models.Provider;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**Controller criar produtos
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class manageProductController implements Initializable{
	/**
	 * Botao criar
	 */
    @FXML
    private Button buttonCreate;
    /**
     * Texto de nome
     */
    @FXML
    private TextField nameTxtFld;
    /**
     * Texto de preco
     */
    @FXML
    private TextField priceTxtFld;
    /**
     * Texto de quantidade
     */
    @FXML
    private TextField qntdTxtFld;
    /**
     * Texto de alerta
     */
    @FXML
    private Label alert;
    /**
     * Caixa combo de fornecedores
     */
    @FXML
    private ComboBox<Provider> providerComboBox;
    /**
     * Data de validade
     */
    @FXML
    private DatePicker validityDatePicker;
    /**
     * Seleciona produto
     */
    private Product selected = ProductFacade.chosenProduct();
    /**
     * Inicializador
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setComboBox();
		if (selected != null) {
			setProdData();
		}
	}
	/**
	 * Inserindo dados nas variaveis
	 */
	public void setProdData() {
		nameTxtFld.setText(selected.getName());
		priceTxtFld.setText(selected.getPrice().toString());
		qntdTxtFld.setText(String.valueOf(selected.getQuantity()));
		validityDatePicker.setValue(selected.getValidity());
		providerComboBox.setValue(selected.getProvider());
	}
	/**
	 * Inserindo lista na combobox
	 */
	public void setComboBox() {
		providerComboBox.setItems(FXCollections.observableArrayList(ProviderFacade.listProvider()));
	}
	/**
	 * Criando produto
	 * @param event
	 */
	@FXML
	void createProd(ActionEvent event){
		try {
			
			String name = nameTxtFld.getText();
			String price = priceTxtFld.getText();
			Provider provider = providerComboBox.getValue();
			LocalDate validity = validityDatePicker.getValue();
			int quantity = Integer.parseInt(qntdTxtFld.getText());
			
			if (selected == null) {
				ProductFacade.createProduct(name, new BigDecimal(price), validity, quantity, provider);
			} else {
				ProductFacade.editProduct(selected.getId(), name, new BigDecimal(price), validity, quantity, provider);
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
		} catch (InvalidDateException e) {
			alert.setText("A data digitada é inválida!");
		} catch (NumberFormatException e) {
			alert.setText("Valores digitados são inválidos");
		} catch (InvalidQuantityException e) {
			alert.setText("A quantidade digitada é inválida");
		} catch (EmptyStringException e) {
			alert.setText("Campos vazios!");
		}
	}
}