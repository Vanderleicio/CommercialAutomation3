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

import app.EnumSequence;
import app.Main;
import app.model.exceptions.LoginDoesntMatch;
import app.model.exceptions.NickNonexistent;
import app.model.facades.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
/**Controller login
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class loginController {
	/**
	 * Botao login
	 */
    @FXML
    private Button buttonLogin;
    /**
     * Texto alerta
     */
    @FXML
    private Label alertLogin;
    /**
     * Conteiner
     */
    @FXML
    private AnchorPane containerLogin;
    /**
     * Imagem
     */
    @FXML
    private ImageView imageLogo;
    /**
     * conteiner
     */
    @FXML
    private AnchorPane nickFieldAndNickText;
    /**
     * Texto apelido
     */
    @FXML
    private Label nickname;
    /**
     * Conteiner
     */
    @FXML
    private AnchorPane passFieldAndTextField;
    /**
     * Texto
     */
    @FXML
    private Label password;
    /**
     * Senha
     */
    @FXML
    private PasswordField passwordField;
    /**
     * Texto
     */
    @FXML
    private TextField textField;
    /**
     * Liberar acesso para login
     * @param event
     */
    @FXML
    void freeAcess(MouseEvent event) {
    	try {
    		String userNick = textField.getText();
    		String userPass = new String( passwordField.getText());
    		UserFacade.login(userNick, userPass);
    		Main.changeScene("Acesso Liberado");
    	} catch(LoginDoesntMatch | NickNonexistent loginExcept) {
    		alertLogin.setText("Dados incorreto, tente novamente!");
    	}
    }
}