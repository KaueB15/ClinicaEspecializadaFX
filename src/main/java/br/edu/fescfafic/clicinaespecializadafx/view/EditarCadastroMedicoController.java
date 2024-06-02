package br.edu.fescfafic.clicinaespecializadafx.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class EditarCadastroMedicoController {

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldCPF;

    @FXML
    private TextField fieldLogin;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private TextField fieldEspecialidade;

    @FXML
    private TextField fieldCrm;

    @FXML
    private Label labelCPF;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelName;

    @FXML
    private Label labelLogin;

    @FXML
    private Label labelEspecialidade;

    @FXML
    private Label labelCrm;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Label welcomeText;

    @FXML
    private Button editNameButton;

    @FXML
    private Button editCPFButton;

    @FXML
    private Button editLoginButton;

    @FXML
    private Button editPasswordButton;

    @FXML
    private Button editEspecialidadeButton;

    @FXML
    private Button editCrmButton;

    @FXML
    void onEditName() {
        toggleEdit(fieldName, labelName, editNameButton);
    }

    @FXML
    void onEditCPF() {
        toggleEdit(fieldCPF, labelCPF, editCPFButton);
    }

    @FXML
    void onEditLogin() {
        toggleEdit(fieldLogin, labelLogin, editLoginButton);
    }

    @FXML
    void onEditPassword() {
        toggleEdit(fieldPassword, labelPassword, editPasswordButton);
    }

    @FXML
    void onEditEspecialidade() {
        toggleEdit(fieldEspecialidade, labelEspecialidade, editEspecialidadeButton);
    }

    @FXML
    void onEditCrm() {
        toggleEdit(fieldCrm, labelCrm, editCrmButton);
    }

    private void toggleEdit(Control control, Label label, Button editButton) {
        if (control.isVisible()) {
            label.setVisible(true);
            control.setVisible(false);
            editButton.setText("Editar");
        } else {
            label.setVisible(false);
            control.setVisible(true);
            control.requestFocus();
            editButton.setText("Salvar");
        }
    }

    @FXML
    private void onSalvarButtonClick() {
        System.out.println("Salvar Alterações no Banco de Dados");
//        Lógica para salvar as alterações

    }

    @FXML
    private void onVoltarButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/agenda.fxml"));
        Parent cadastroRoot = fxmlLoader.load();
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(cadastroRoot);
    }
}
