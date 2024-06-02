package br.edu.fescfafic.clicinaespecializadafx.view;

import br.edu.fescfafic.clicinaespecializadafx.domain.Agenda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AgendaController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<Agenda> tableView;

    @FXML
    private TableColumn<Agenda, String> tablePaciente;
    @FXML
    private TableColumn<Agenda, String> tableSexo;
    @FXML
    private TableColumn<Agenda, Integer> tableIdade;
    @FXML
    private TableColumn<Agenda, String> tableHora;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button cadastro;

    @FXML
    private void onVoltarButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/login.fxml"));
        Parent cadastroRoot = fxmlLoader.load();
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(cadastroRoot);
    }

    @FXML
    private void onCadastroButtonClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/editarCadastroMedico.fxml"));
        Parent cadastroRoot = fxmlLoader.load();
        Stage stage = (Stage) cadastro.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(cadastroRoot);
    }

}