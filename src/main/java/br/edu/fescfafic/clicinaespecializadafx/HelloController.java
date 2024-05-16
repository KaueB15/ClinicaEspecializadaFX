package br.edu.fescfafic.clicinaespecializadafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onHelloButtonClick() {
        String login = loginField.getText();
        String password = new String(this.passwordField.getText());
        welcomeText.setText("Login - " + login + "\n" + "Senha - " + password);
    }
}