package br.edu.fescfafic.clicinaespecializadafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cadastroPaciente.fxml"));
        Parent cadastroRoot = fxmlLoader.load();
        Scene cadastroScene = new Scene(cadastroRoot);

        Stage stage = (Stage) pacienteButton.getScene().getWindow();
        stage.setScene(cadastroScene);
        stage.setTitle("Cadastro Paciente");
        stage.show();
    }

    @FXML
    protected void onMedicoButtonClick(ActionEvent event) {
        // Lógica para lidar com o clique no botão do médico
        System.out.println("Botão do Médico clicado!");
    }

    @FXML
    protected void onVoltarButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent loginRoot = fxmlLoader.load();
        Scene loginScene = new Scene(loginRoot);

        Stage stage = (Stage) pacienteButton.getScene().getWindow();
        stage.setScene(loginScene);
        stage.setTitle("Clinica Especializada");
        stage.show();
    }
}
