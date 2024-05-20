package br.edu.fescfafic.clicinaespecializadafx.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroPacienteController {
    @FXML
    private Button buttonReturnCadastro;
    @FXML
    private Button btnFinalizarCadastro;

    @FXML
    protected void onVoltarButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/cadastro.fxml"));
        Parent cadastroRoot = fxmlLoader.load();
        Scene cadastroScene = new Scene(cadastroRoot);

        Stage stage = (Stage) btnFinalizarCadastro.getScene().getWindow();
        stage.setScene(cadastroScene);
        stage.setTitle("Cadastro Paciente");
        stage.show();
    }
}
