package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class mainController {

    @FXML
    private ImageView iconLogo;

    @FXML
    private BorderPane containerMain;

    @FXML
    private Button titleClient;

    @FXML
    private Button titleMenu;

    @FXML
    private Label titlePage;

    @FXML
    private Button titleProduct;

    @FXML
    private Button titleProvider;

    @FXML
    private Button titleReport;

    @FXML
    private Button titleUser;
    
    public void initialize(URL url, ResourceBundle rb) {
    	
    }

    @FXML
    private void openManagementUsers() {
        open("/app/views/ManagementUsers.fxml");
    }
    
    @FXML
    private void openManagementProviders() {
        open("/app/views/ManagementProviders.fxml");
    }
    
    @FXML
    private void open(String url) {
    	Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(url));
        } catch (IOException ex) {

        }
        this.containerMain.setCenter(root);
    }
    
    @FXML
    void openUser(MouseEvent event) {
    	titlePage.setText("Gerenciamento de Usuários");
    	openManagementUsers();
    }
    
    @FXML
    void openProviders(MouseEvent event) {
    	titlePage.setText("Gerenciamento de Fornecedores");
    	openManagementProviders();
    }

}

