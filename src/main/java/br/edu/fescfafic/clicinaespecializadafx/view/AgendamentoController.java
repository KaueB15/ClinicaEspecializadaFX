package br.edu.fescfafic.clicinaespecializadafx.view;

import br.edu.fescfafic.clicinaespecializadafx.dao.AgendaDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.AgendamentoDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.MedicoDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.SendSMSDAO;
import br.edu.fescfafic.clicinaespecializadafx.domain.*;
import br.edu.fescfafic.clicinaespecializadafx.exceptions.InvalidDateException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
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
    protected Label welcomeText;
    @FXML
    private TableView <Agendamento> tableAgendamento;
    @FXML
    private TableColumn<Agendamento, String> colunaMedico;

    @FXML
    private TableColumn<Agendamento, String> colunaEspecialidade;

    @FXML
    private TableColumn<Agendamento, String> colunaData;

    @FXML
    private TableColumn<Agendamento, String> colunaHora;
    @FXML
    private Label dataIndisponivel;
    @FXML
    private Label sucessoAgendamento;
    @FXML
    private Button cancelButton;
    Paciente pacienteLogado;

    @FXML
    public void initialize() {

        colunaMedico.setCellValueFactory(new PropertyValueFactory<>("medicoNome"));
        colunaEspecialidade.setCellValueFactory(new PropertyValueFactory<>("medicoEspecialidade"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));

//        carregarDadosNaTabela();

        // Inicialmente, o botão está invisível
        cancelButton.setVisible(false);

        // Adiciona listener para mudar a visibilidade do botão
        tableAgendamento.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            cancelButton.setVisible(newSelection != null);
        });

        // Adiciona um listener para o botão "Cancelar Consulta"
        cancelButton.setOnAction(event -> onCancelarConsultaButtonClick());
    }

    public boolean dateValidation(LocalDateTime date){
        LocalDateTime now = LocalDateTime.now();
        if(date.isBefore(now)){
            return false;
        }
        else{
            return true;
        }
    }

    @FXML
    private void onCancelarConsultaButtonClick() {
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        AgendaDAO agendaDAO = new AgendaDAO();
        Agendamento selectedAgendamento = tableAgendamento.getSelectionModel().getSelectedItem();
        List<Agenda> agendas = agendaDAO.listarAgendas();
        if (selectedAgendamento != null) {
            // Remove a consulta selecionada
            
            for(Agenda agenda : agendas){
                if (agenda.getAgendamento().getId() == selectedAgendamento.getId()){
                    agenda.setAgendamento(null);
                    agendaDAO.removerAgenda(agenda);
                    break;
                }
            }

            tableAgendamento.getItems().remove(selectedAgendamento);
            agendamentoDAO.removerAgendamento(selectedAgendamento);

            // Mostra uma mensagem de confirmação
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Consulta Cancelada");
            alert.setHeaderText(null);
            alert.setContentText("Consulta cancelada com sucesso!");
            alert.showAndWait();
        } else {
            // Mostra uma mensagem de erro se nenhuma consulta estiver selecionada
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Nenhuma consulta selecionada!");
            alert.showAndWait();
        }
    }




    @FXML
    private void onSairButtonClick(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/login.fxml"));
        Parent cadastroRoot = fxmlLoader.load();
        Stage stage = (Stage) exitButton.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(cadastroRoot);
    }

    @FXML
    private void onCadastroButtonClick(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/editarCadastroPaciente.fxml"));
        Parent cadastroRoot = fxmlLoader.load();

        EditarCadastroPacienteController editarCadastroPacienteController = fxmlLoader.getController();
        Paciente paciente = pacienteLogado;
        editarCadastroPacienteController.setPacienteLogado(paciente);
        String nomeCompleto = pacienteLogado.getNome();
        String[] nomeSeparado = nomeCompleto.split(" ");
        String primeiroNome = nomeSeparado[0];
        editarCadastroPacienteController.welcomeText.setText("Olá, " + primeiroNome + "!");
        editarCadastroPacienteController.returnDados();

        Stage stage = (Stage) cadastro.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(cadastroRoot);
    }


    protected void setPacienteLogado(Paciente paciente){
        this.pacienteLogado = paciente;
    }

    protected Agendamento returnAgendamento(LocalDateTime dateHour, Medico medico, Paciente paciente){
            Agendamento agendamento = new Agendamento();

            agendamento.setDataHoraInicio(dateHour);
            agendamento.setMedico(medico);
            agendamento.setPaciente(paciente);

            return agendamento;
    }

    protected Agenda returnAgenda(LocalDateTime dataConsulta, LocalTime horaConsulta, Paciente paciente, String sexo,
                                  LocalDate dataNascimento, int idade, Medico medico, Agendamento agendamento){
        Agenda agenda = new Agenda();

        agenda.setDataConsulta(dataConsulta);
        agenda.setHora(horaConsulta);
        agenda.setPaciente(paciente);
        agenda.setSexo(sexo);
        agenda.setDataNascimento(dataNascimento);
        agenda.setIdade(idade);
        agenda.setMedico(medico);
        agenda.setAgendamento(agendamento);

        return agenda;
    }

    protected void findAgenda(Agendamento agendamento){

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

        List<String> especialidades = medicoDAO.listarEspecialidades();

        for (String especialidade : especialidades){
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

        sucessoAgendamento.setText(" ");
        dataIndisponivel.setText(" ");

        AgendaDAO agendaDAO = new AgendaDAO();
        SendSMSDAO sendSMSDAO = new SendSMSDAO();
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

        String selectedNameMedico = boxMedicos.getValue();
        LocalDate selectedDate = dataConsulta.getValue();
        LocalTime selectedHour = boxHour.getValue();
        Paciente selectedPaciente = pacienteLogado;

        Medico selectedMedico = returnMedico(selectedNameMedico);
        LocalDateTime selectedHourDate = LocalDateTime.of(selectedDate, selectedHour);

        int idadePaciente = 2024 - selectedPaciente.getDataNascimento().getYear();

        System.out.println(idadePaciente);

        Agendamento agendamento = returnAgendamento(selectedHourDate, selectedMedico, selectedPaciente);
        Agenda agenda = returnAgenda(selectedHourDate, selectedHour, selectedPaciente, selectedPaciente.getSexo(),
                selectedPaciente.getDataNascimento(), idadePaciente, selectedMedico, agendamento);


        try {
            if (dateValidation(selectedHourDate)){
                agendamentoDAO.inserirAgendamento(agendamento);
                agendaDAO.inserirAgenda(agenda);

                sucessoAgendamento.setText("CONSULTA AGENDADA");

                carregarDadosNaTabela();

                String mensagemFormatada = "Consulta Agendada\n" + "Paciente - " + pacienteLogado.getNome() + "\nMedico - "
                        + selectedMedico.getNome() + "\nData - " + selectedDate + "\nHora - " + selectedHour;

                sendSMSDAO.sendSMS(mensagemFormatada);

            }else{
                throw new InvalidDateException();
            }
        }catch (ConstraintViolationException e){
            System.out.println("DATA INDISPONIVEL");
            dataIndisponivel.setText("DATA INDISPONIVEL");
        }catch (InvalidDateException e){
            System.out.println(e);
            dataIndisponivel.setText("DATA INVALIDA");
        }

    }

    protected void carregarDadosNaTabela() {
        tableAgendamento.getItems().clear();
        Paciente paciente = pacienteLogado;
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        List<Agendamento> agendamentosGeral = agendamentoDAO.listarAgendamentos();
        for(Agendamento agendamento : agendamentosGeral){
            if (agendamento.getPaciente().equals(paciente)){
                tableAgendamento.getItems().add(agendamento);
            }
        }
    }
}





