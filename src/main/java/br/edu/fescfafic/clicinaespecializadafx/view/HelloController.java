package br.edu.fescfafic.clicinaespecializadafx.view;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {
    @FXML
    private Button loginButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button cadastroButton;

    @FXML
    protected void onHelloButtonClick() {
        String login = loginField.getText();
        String password = new String(this.passwordField.getText());
        loginButton.setText("Login - " + login + "\n" + "Senha - " + password);
//        Inserir aqui o código de validação do login
    }
    @FXML
    protected void onCadastroButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/cadastro.fxml"));
        Parent cadastroRoot = fxmlLoader.load();
        Scene cadastroScene = new Scene(cadastroRoot);

        Stage stage = (Stage) cadastroButton.getScene().getWindow();
        stage.setScene(cadastroScene);
        stage.setTitle("Cadastro");
        stage.show();
    }
}