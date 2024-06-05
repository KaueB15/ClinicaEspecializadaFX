package br.edu.fescfafic.clicinaespecializadafx.view;

import br.edu.fescfafic.clicinaespecializadafx.dao.LoginDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.PacienteDAO;
import br.edu.fescfafic.clicinaespecializadafx.domain.Login;
import br.edu.fescfafic.clicinaespecializadafx.domain.Paciente;
import jakarta.persistence.RollbackException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditarCadastroPacienteController {

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldCPF;

    @FXML
    private TextField fieldLogin;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private TextField fieldPhone;

    @FXML
    private DatePicker fieldDate;

    @FXML
    private Label labelCPF;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelName;

    @FXML
    private Label labelLogin;

    @FXML
    private Label labelPhone;

    @FXML
    private ToggleGroup sexoRadio;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Label errorMessage;

    @FXML
    private Label cadastroMessage;
    @FXML
    private Button editNameButton;

    @FXML
    private Button editCPFButton;

    @FXML
    private Button editLoginButton;

    @FXML
    private Button editPasswordButton;

    @FXML
    private Button editPhoneButton;

    @FXML
    private Button editDateButton;

    @FXML
    protected Label welcomeText;

    protected Paciente pacienteLogado;

    public void setPacienteLogado(Paciente pacienteLogado) {
        this.pacienteLogado = pacienteLogado;
    }

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
    void onEditPhone() {
        toggleEdit(fieldPhone, labelPhone, editPhoneButton);
    }

    @FXML
    void onEditDate() {
        toggleEdit(fieldDate, labelDate, editDateButton);
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

        PacienteDAO pacienteDAO = new PacienteDAO();
        LoginDAO loginDAO = new LoginDAO();

        Paciente pacienteAtualizado = pacienteLogado;

        Login pacienteLogin = pacienteLogado.getLogin();

        String nameEdit = fieldName.getText();
        String cpfEdit = fieldCPF.getText();
        String loginEdit = fieldLogin.getText();
        String telefoneEdit = fieldPhone.getText();
        String senhaEdit = fieldPassword.getText();
        LocalDate dateEdit = fieldDate.getValue();
        String sexoEdit = ((RadioButton) sexoRadio.getSelectedToggle()).getText();


        if (!nameEdit.isEmpty()){
            pacienteAtualizado.setNome(nameEdit);
        }

        if (!senhaEdit.isEmpty()){
            pacienteLogin.setSenha(senhaEdit);
        }

        if (!loginEdit.isEmpty()){
            pacienteLogin.setLogin(loginEdit);
        }

        if (!cpfEdit.isEmpty()){
            pacienteAtualizado.setCpf(cpfEdit);
        }

        if (!telefoneEdit.isEmpty()){
            pacienteAtualizado.setTelefonePaciente(telefoneEdit);
        }

        if (dateEdit != null){
            pacienteAtualizado.setDataNascimento(dateEdit);
        }

        if (!sexoEdit.isEmpty()){
            pacienteAtualizado.setSexo(sexoEdit);
        }

        try{
            pacienteDAO.atualizarPaciente(pacienteAtualizado);
            loginDAO.atualizarLogin(pacienteLogin);
            returnDados();
            cadastroMessage.setText("ATUALIZADO");
        }catch (RollbackException e){
            System.out.println("Dados já existem no banco de dados");
            errorMessage.setText("DADOS EXISTENTES");
        }



    }

    protected void returnDados(){
        Paciente pacienteLogin = pacienteLogado;

        labelName.setText(pacienteLogin.getNome());
        labelCPF.setText(pacienteLogin.getCpf());
        labelDate.setText(pacienteLogin.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        labelLogin.setText(pacienteLogin.getLogin().getLogin());
        labelPassword.setText("**********");
        labelPhone.setText(pacienteLogin.getTelefonePaciente());

    }

    @FXML
    private void onVoltarButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/agendamento.fxml"));
        Parent cadastroRoot = fxmlLoader.load();

        AgendamentoController agendamentoController = fxmlLoader.getController();
        String nomeCompleto = pacienteLogado.getNome();
        String[] nomeSeparado = nomeCompleto.split(" ");
        String primeiroNome = nomeSeparado[0];
        agendamentoController.welcomeText.setText("Olá, " + primeiroNome + "!");
        agendamentoController.setPacienteLogado(pacienteLogado);
        agendamentoController.carregarDadosNaTabela();

        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(cadastroRoot);
    }
}
