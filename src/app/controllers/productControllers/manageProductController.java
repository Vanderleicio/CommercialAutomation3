package app.controllers.productControllers;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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

public class manageProductController implements Initializable{

    @FXML
    private Button buttonCreate;

    @FXML
    private TextField nameTxtFld;
    
    @FXML
    private TextField priceTxtFld;
    
    @FXML
    private TextField qntdTxtFld;
    
    @FXML
    private Label alert;
    
    @FXML
    private ComboBox<Provider> providerComboBox;
    
    @FXML
    private DatePicker validityDatePicker;
    
    private Product selected = ProductFacade.chosenProduct();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setComboBox();
		if (selected != null) {
			setProdData();
		}
	}
	
	public void setProdData() {
		nameTxtFld.setText(selected.getName());
		priceTxtFld.setText(selected.getPrice().toString());
		qntdTxtFld.setText(String.valueOf(selected.getQuantity()));
		validityDatePicker.setValue(selected.getValidity());
		providerComboBox.setValue(selected.getProvider());
	}
	
	public void setComboBox() {
		providerComboBox.setItems(FXCollections.observableArrayList(ProviderFacade.listProvider()));
	}
	
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
		}
	}
}