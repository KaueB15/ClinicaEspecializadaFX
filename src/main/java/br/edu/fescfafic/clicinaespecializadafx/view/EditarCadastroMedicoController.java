package br.edu.fescfafic.clicinaespecializadafx.view;
import br.edu.fescfafic.clicinaespecializadafx.dao.LoginDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.MedicoDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.PacienteDAO;
import br.edu.fescfafic.clicinaespecializadafx.domain.Login;
import br.edu.fescfafic.clicinaespecializadafx.domain.Medico;
import br.edu.fescfafic.clicinaespecializadafx.domain.Paciente;
import jakarta.persistence.RollbackException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

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
    protected Label welcomeText;

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
    private Label errorMessage;

    @FXML
    private Label cadastroMessage;


    protected Medico medicoLogado;

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

    protected void setMedico(Medico medico){
        this.medicoLogado = medico;
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
        cadastroMessage.setText("");
        errorMessage.setText("");

        MedicoDAO medicoDAO = new MedicoDAO();
        LoginDAO loginDAO = new LoginDAO();

        Medico medicoAtualizado = medicoLogado;

        Login medicoLogin = medicoLogado.getLogin();

        String nameEdit = fieldName.getText();
        String cpfEdit = fieldCPF.getText();
        String loginEdit = fieldLogin.getText();
        String senhaEdit = fieldPassword.getText();
        String especialidadeEdit = fieldEspecialidade.getText();
        String crmEdit = fieldCrm.getText();



        if (!nameEdit.isEmpty()){
            medicoAtualizado.setNome(nameEdit);
        }

        if (!senhaEdit.isEmpty()){
            medicoLogin.setSenha(senhaEdit);
        }

        if (!loginEdit.isEmpty()){
            medicoLogin.setLogin(loginEdit);
        }

        if (!cpfEdit.isEmpty()){
            medicoAtualizado.setCpf(cpfEdit);
        }

        if (!crmEdit.isEmpty()){
            int crmEditado = Integer.parseInt(crmEdit);
            medicoAtualizado.setCrm(crmEditado);
        }

        if (!especialidadeEdit.isEmpty()){
            medicoAtualizado.setEspecialidade(especialidadeEdit);
        }


        try{
            medicoDAO.atualizarMedico(medicoAtualizado);
            loginDAO.atualizarLogin(medicoLogin);

            cadastroMessage.setText("ATUALIZADO");
        }catch (RollbackException e){
            System.out.println("Dados já existem no banco de dados");
            errorMessage.setText("DADOS EXISTENTES");
        }
    }

    @FXML
    private void onVoltarButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/agenda.fxml"));
        Parent cadastroRoot = fxmlLoader.load();

        AgendaController agendaController = fxmlLoader.getController();
        String nomeCompleto = medicoLogado.getNome();
        String[] nomeSeparado = nomeCompleto.split(" ");
        String primeiroNome = nomeSeparado[0];
        agendaController.welcomeText.setText("Olá, " + primeiroNome + "!");
        agendaController.setMedicoLogado(medicoLogado);
        agendaController.carregarDadosNaTabela();

        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(cadastroRoot);
    }
}
