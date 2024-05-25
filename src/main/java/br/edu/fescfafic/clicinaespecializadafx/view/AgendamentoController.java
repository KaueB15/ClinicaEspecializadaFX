package br.edu.fescfafic.clicinaespecializadafx.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class AgendamentoController {
    @FXML
    private DatePicker dataConsulta;

    @FXML
    private ComboBox<String> especialidade;

    @FXML
    private ComboBox<String> medico;

    @FXML
    private Spinner<Integer> hora;

    @FXML
    private Button agendar;

    @FXML
    private Button sair;

    @FXML
    private Button cadastro;

    @FXML
    private Label welcomeText;

    @FXML
    private TableView consultaTableView;
//    Talvez seja preciso criar uma classe consulta.

    @FXML
    private void onSairButtonClick(ActionEvent event) {
        // Adicione aqui o código para a funcionalidade de sair
    }

    @FXML
    private void onCadastroButtonClick(ActionEvent event) {
        // Adicione aqui o código para a funcionalidade de cadastro
    }

}





