package app.controllers;

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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**Controller do menu geral.
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class mainController {
	/**
	 * imagem
	 */
    @FXML
    private ImageView iconLogo;
    /**
     * conteiner
     */
    @FXML
    private BorderPane containerMain;
    /**
     * Botao cliente
     */
    
    @FXML
    private Button exitButton;
    
    @FXML
    private Button titleClient;
    /**
     * Botao menu
     */
    @FXML
    private Button titleMenu;
    /**
     * Texto titulo da pagina
     */
    @FXML
    private Label titlePage;
    /**
     * Botao produtos
     */
    @FXML
    private Button titleProduct;
    /**
     * Botao fornecedor
     */
    @FXML
    private Button titleProvider;
    /**
     * Botao relatorio
     */
    @FXML
    private Button titleReport;
    /**
     * Botao usuario
     */
    @FXML
    private Button titleUser;
    /**
     * Inicializando
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
    }
    /**
     * Direcionando a tela 
     */
    @FXML
    private void openManagementUsers() {
        open("/app/views/ManagementUsers.fxml");
    }
    /**
     * Direcionando a tela 
     */
    @FXML
    private void openManagementProviders() {
        open("/app/views/ManagementProviders.fxml");
    }
    /**
     * Direcionando a tela 
     */
    @FXML
    private void openManagementProduct() {
        open("/app/views/ManagementProduct.fxml");
    }
    /**
     * Direcionando a tela 
     */
    @FXML
    private void openManagementMenu() {
        open("/app/views/ManagementMenu.fxml");
    }
    /**
     * Direcionando a tela 
     */
    @FXML
    private void openManagementClient() {
        open("/app/views/ManagementClient.fxml");
    }
    /**
     * Direcionando a tela 
     */
    @FXML
    private void openManagementSales() {
        open("/app/views/ManagementSales.fxml");
    }
    /**
     * Direcionando a tela 
     */
    @FXML
    private void openManagementReport() {
        open("/app/views/ManagementReport.fxml");
    }
    /**
     * Abrir tela
     * @param url
     */
  
    private void open(String url) {
    	Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(url));
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
        this.containerMain.setCenter(root);
    }
    /**
     * Acao para trocar a tela
     * @param event
     */
    @FXML
    void openUser(MouseEvent event) {
    	titlePage.setText("Gerenciamento de Usuários");
    	openManagementUsers();
    }
    /**
     * Acao para trocar a tela
     * @param event
     */
    @FXML
    void openProviders(MouseEvent event) {
    	titlePage.setText("Gerenciamento de Fornecedores");
    	openManagementProviders();
    }
    /**
     * Acao para trocar a tela
     * @param event
     */
    @FXML
    void openClient(MouseEvent event) {
    	titlePage.setText("Gerenciamento de Clientes");
    	openManagementClient();
    }
    /**
     * Acao para trocar a tela
     * @param event
     */
    @FXML
    void openMenu(MouseEvent event) {
    	titlePage.setText("Gerenciamento do Menu");
    	openManagementMenu();
    }
    /**
     * Acao para trocar a tela
     * @param event
     */
    @FXML
    void openProduct(MouseEvent event) {
    	titlePage.setText("Gerenciamento dos Produtos");
    	openManagementProduct();
    }
    /**
     * Acao para trocar a tela
     * @param event
     */
    @FXML
    void openReport(MouseEvent event) {
    	titlePage.setText("Relatórios");
    	openManagementReport();
    }
    /**
     * Acao para trocar a tela
     * @param event
     */
    @FXML
    void openSale(MouseEvent event) {
    	titlePage.setText("Gerenciamento de Vendas");
    	openManagementSales();
    }
    
}

