package br.edu.fescfafic.clicinaespecializadafx.view;

import br.edu.fescfafic.clicinaespecializadafx.dao.MedicoDAO;
import br.edu.fescfafic.clicinaespecializadafx.domain.Medico;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoController {
    @FXML
    private DatePicker dataConsulta;

    @FXML
    private ComboBox<String> boxEspecialidade;

    @FXML
    private ComboBox<String> medico;

    @FXML
    private Spinner<Integer> hora;

    @FXML
    private Button buttonAgendar;

    @FXML
    private Button exitButton;

    @FXML
    private Button cadastro;

    @FXML
    private Label welcomeText;
    @FXML
    private TableColumn tableMedico;

    @FXML
    private TableView consultaTableView;
//    Talvez seja preciso criar uma classe consulta.

    @FXML
    private void onSairButtonClick(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/login.fxml"));
        Parent cadastroRoot = fxmlLoader.load();
        Scene cadastroScene = new Scene(cadastroRoot);

        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.setScene(cadastroScene);
        stage.setTitle("Cadastro");
        stage.show();
    }

    @FXML
    private void onAgendarButtonClick(Event event) {
        MedicoDAO medicoDAO = new MedicoDAO();

        List<Medico> medicos = medicoDAO.listarMedico();

        for (Medico medico : medicos){
            String especialidade = medico.getEspecialidade();

            boxEspecialidade.getItems().add(especialidade);
        }
    }

}





