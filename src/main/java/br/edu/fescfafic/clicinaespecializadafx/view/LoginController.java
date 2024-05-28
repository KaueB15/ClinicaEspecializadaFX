package br.edu.fescfafic.clicinaespecializadafx.view;

import br.edu.fescfafic.clicinaespecializadafx.dao.LoginDAO;
import br.edu.fescfafic.clicinaespecializadafx.domain.Login;
import br.edu.fescfafic.clicinaespecializadafx.exceptions.LoginNotFoundException;
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
import java.util.List;

public class LoginController {

    @FXML
    private Button loginButton;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button cadastroButton;
    @FXML
    private Label errorMessage;


    protected boolean loginValidation(String login, String password){

        boolean validation = false;

        LoginDAO loginDAO = new LoginDAO();

        List<Login> logins = loginDAO.getAll();

        for (Login loginList : logins){
            if(loginList.getLogin().equals(login) && loginList.getSenha().equals(password)){
                validation = true;
            }
        }

        return validation;
    }

    @FXML
    protected void onLoginButtonClick() throws IOException {

        String login = loginField.getText();
        String password = passwordField.getText();

        boolean loginPasswordValidation = loginValidation(login, password);

        try {
            if (loginPasswordValidation){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/agendamento.fxml"));
                Parent cadastroRoot = fxmlLoader.load();
                Scene cadastroScene = new Scene(cadastroRoot);

                Stage stage = (Stage) cadastroButton.getScene().getWindow();
                stage.setScene(cadastroScene);
                stage.setTitle("Agendamento");
                stage.show();
            }else{
                throw new LoginNotFoundException();
            }

        }catch (LoginNotFoundException e){
            System.err.println(e);
            errorMessage.setText("LOGIN INVALIDO");
        }

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