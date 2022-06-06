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
       abrirManutencoes();
    }

    @FXML
    private void abrirManutencoes() {
        abrir("/app/views/login.fxml");
    }
    
    @FXML
    private void abrir(String url) {
    	Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(url));
        } catch (IOException ex) {

        }
        this.containerMain.setCenter(root);
    }
    
    @FXML
    void abrirUser(MouseEvent event) {
    	titlePage.setText("Gerenciamento de Usuários");
    	System.out.print("foi caralho");
    	abrirManutencoes();
    }

}

