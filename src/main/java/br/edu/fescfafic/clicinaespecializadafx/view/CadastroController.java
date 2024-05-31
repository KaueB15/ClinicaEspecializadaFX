package br.edu.fescfafic.clicinaespecializadafx.view;

import br.edu.fescfafic.clicinaespecializadafx.domain.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroController {

    @FXML
    private Rectangle pacienteButton;

    @FXML
    private Rectangle medicoButton;

    @FXML
    private Button voltarButton;

    @FXML
    protected void onPacienteButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/cadastroPaciente.fxml"));
        Parent cadastroRoot = fxmlLoader.load();
        Scene cadastroPacienteScene = new Scene(cadastroRoot);
        Stage stage = (Stage) pacienteButton.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(cadastroRoot);
    }

    @FXML
    protected void onMedicoButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/cadastroMedico.fxml"));
        Parent cadastroRoot = fxmlLoader.load();
        Scene cadastroMedicoScene = new Scene(cadastroRoot);
        Stage stage = (Stage) medicoButton.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(cadastroRoot);
    }

    @FXML
    protected void onVoltarButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/login.fxml"));
        Parent loginRoot = fxmlLoader.load();
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(loginRoot);

    }

}
