package br.edu.fescfafic.clicinaespecializadafx.view;

import br.edu.fescfafic.clicinaespecializadafx.dao.AgendamentoDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.MedicoDAO;
import br.edu.fescfafic.clicinaespecializadafx.domain.Agendamento;
import br.edu.fescfafic.clicinaespecializadafx.domain.Login;
import br.edu.fescfafic.clicinaespecializadafx.domain.Medico;
import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoController {
    @FXML
    private DatePicker dataConsulta;

    @FXML
    private ComboBox<String> boxEspecialidade;

    @FXML
    private ComboBox<String> boxMedicos;

    @FXML
    private ComboBox<LocalTime> boxHour;

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

    LoginController loginController = new LoginController();

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

    protected Agendamento returnAgendamento(LocalDateTime dateHour, Medico medico){
            Agendamento agendamento = new Agendamento();

            agendamento.setDataHoraInicio(dateHour);
            agendamento.setMedico(medico);


            return agendamento;
    }

    protected Medico returnMedico(String nome) {
        MedicoDAO medicoDAO = new MedicoDAO();

        List<Medico> medicos = medicoDAO.listarMedico();

        for (Medico medico : medicos) {
            if (medico.getNome().equals(nome)) {
                return medico;
            }
        }

        return null;
    }

    @FXML
    private void onEspecialidadeShowing(Event event) {

        boxEspecialidade.getItems().clear();

        MedicoDAO medicoDAO = new MedicoDAO();

        List<Medico> medicos = medicoDAO.listarMedico();

        for (Medico medico : medicos){
            String especialidade = medico.getEspecialidade();

            boxEspecialidade.getItems().add(especialidade);
        }
    }

    @FXML
    private void onMedicosShowing(Event event){

        boxMedicos.getItems().clear();

        String selectedEspecialidade = boxEspecialidade.getValue();

        MedicoDAO medicoDAO = new MedicoDAO();

        List<Medico> medicos = medicoDAO.listarMedico();

        for (Medico medico : medicos){
            boolean validationEspecialidade = medico.getEspecialidade().equals(selectedEspecialidade);
            if (validationEspecialidade) {
                String medicoName = medico.getNome();
                boxMedicos.getItems().add(medicoName);
            }
        }
    }

    @FXML
    private void onHourShowing(){

        LocalTime hourOne = LocalTime.of(13, 00, 00);
        LocalTime hourTwo = LocalTime.of(14, 00, 00);
        LocalTime hourThree = LocalTime.of(15, 00, 00);
        LocalTime hourFour = LocalTime.of(16, 00, 00);
        LocalTime hourFive = LocalTime.of(17, 00, 00);
        LocalTime hourSix = LocalTime.of(18, 00, 00);

        boxHour.getItems().clear();
        boxHour.getItems().add(hourOne);
        boxHour.getItems().add(hourTwo);
        boxHour.getItems().add(hourThree);
        boxHour.getItems().add(hourFour);
        boxHour.getItems().add(hourFive);
        boxHour.getItems().add(hourSix);
    }

    @FXML
    private void onAgendamentoClickButton(ActionEvent event){

        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

        String selectedEspecialidade = boxEspecialidade.getValue();
        String selectedNameMedico = boxMedicos.getValue();
        LocalDate selectedDate = dataConsulta.getValue();
        LocalTime selectedHour = boxHour.getValue();

        Medico selectedMedico = returnMedico(selectedNameMedico);
        LocalDateTime selectedHourDate = LocalDateTime.of(selectedDate, selectedHour);

        Agendamento agendamento = returnAgendamento(selectedHourDate, selectedMedico);

        System.out.println(agendamento);
        System.out.println(login);

    }

}





