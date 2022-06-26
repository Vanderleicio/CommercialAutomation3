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
    private void openManagementProduct() {
        open("/app/views/ManagementProduct.fxml");
    }
    
    @FXML
    private void openManagementMenu() {
        open("/app/views/ManagementMenu.fxml");
    }
    
    @FXML
    private void openManagementClient() {
        open("/app/views/ManagementClient.fxml");
    }
    
    @FXML
    private void openManagementSales() {
        open("/app/views/ManagementSales.fxml");
    }
    
    @FXML
    private void openManagementReport() {
        open("/app/views/ManagementReport.fxml");
    }
    
    @FXML
    private void open(String url) {
    	Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(url));
        } catch (IOException ex) {
        	ex.printStackTrace();
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
    
    @FXML
    void openClient(MouseEvent event) {
    	titlePage.setText("Gerenciamento de Clientes");
    	openManagementClient();
    }

    @FXML
    void openMenu(MouseEvent event) {
    	titlePage.setText("Gerenciamento do Menu");
    	openManagementMenu();
    }

    @FXML
    void openProduct(MouseEvent event) {
    	titlePage.setText("Gerenciamento dos Produtos");
    	openManagementProduct();
    }

    @FXML
    void openReport(MouseEvent event) {
    	titlePage.setText("Relatórios");
    	openManagementReport();
    }

    @FXML
    void openSale(MouseEvent event) {
    	titlePage.setText("Gerenciamento de Vendas");
    	openManagementSales();
    }

}

