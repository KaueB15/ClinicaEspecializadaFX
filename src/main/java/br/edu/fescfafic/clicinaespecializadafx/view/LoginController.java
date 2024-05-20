package br.edu.fescfafic.clicinaespecializadafx.view;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;

    public void pegarInfo(){
        String user = userField.getText();
        String senha = new String(passwordField.getText());
    }
}
