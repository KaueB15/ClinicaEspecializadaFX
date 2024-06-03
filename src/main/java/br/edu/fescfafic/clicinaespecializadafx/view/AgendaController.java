package br.edu.fescfafic.clicinaespecializadafx.view;

import br.edu.fescfafic.clicinaespecializadafx.dao.AgendaDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.AgendamentoDAO;
import br.edu.fescfafic.clicinaespecializadafx.domain.Agenda;
import br.edu.fescfafic.clicinaespecializadafx.domain.Agendamento;
import br.edu.fescfafic.clicinaespecializadafx.domain.Medico;
import br.edu.fescfafic.clicinaespecializadafx.domain.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class AgendaController {

    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<Agenda> tableAgenda;
    @FXML
    protected Label welcomeText;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnCadastro;

    @FXML
    private TableColumn<Agenda, String> tablePaciente;
    @FXML
    private TableColumn<Agenda, String> tableSexo;
    @FXML
    private TableColumn<Agenda, Integer> tableIdade;
    @FXML
    private TableColumn<Agenda, LocalTime> tableHora;


    protected Medico medicoLogado;

    @FXML
    public void initialize() {

        tablePaciente.setCellValueFactory(new PropertyValueFactory<>("pacienteNome"));
        tableIdade.setCellValueFactory(new PropertyValueFactory<>("idadePaciente"));
        tableSexo.setCellValueFactory(new PropertyValueFactory<>("sexoPaciente"));
        tableHora.setCellValueFactory(new PropertyValueFactory<>("horaPaciente"));

        carregarDadosNaTabela();
    }

    protected void setMedicoLogado(Medico medico){
        this.medicoLogado = medico;
    }


    protected void carregarDadosNaTabela() {
        tableAgenda.getItems().clear();
        Medico medicoLogin = medicoLogado;
        AgendaDAO agendaDAO = new AgendaDAO();
        List<Agenda> agendaGeral = agendaDAO.listarAgendas();
        for(Agenda agenda : agendaGeral){
            if (agenda.getMedico().equals(medicoLogin)){
                tableAgenda.getItems().add(agenda);
            }
        }
    }

//    @FXML
//    private void onVoltarButtonClick() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/login.fxml"));
//        Parent cadastroRoot = fxmlLoader.load();
//        Stage stage = (Stage) btnVoltar.getScene().getWindow();
//        Pane mainPane = (Pane) stage.getScene().getRoot();
//        mainPane.getChildren().clear();
//        mainPane.getChildren().add(cadastroRoot);
//    }

    @FXML
    private void onCadastroButtonClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/editarCadastroMedico.fxml"));
        Parent cadastroRoot = fxmlLoader.load();
        Stage stage = (Stage) btnCadastro.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(cadastroRoot);
    }

    @FXML
    private void onSairButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/login.fxml"));
        Parent cadastroRoot = fxmlLoader.load();
        Stage stage = (Stage) btnSair.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(cadastroRoot);
    }

}