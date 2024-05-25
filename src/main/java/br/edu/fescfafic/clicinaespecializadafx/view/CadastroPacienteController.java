package br.edu.fescfafic.clicinaespecializadafx.view;

import br.edu.fescfafic.clicinaespecializadafx.dao.LoginDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.PacienteDAO;
import br.edu.fescfafic.clicinaespecializadafx.domain.Login;
import br.edu.fescfafic.clicinaespecializadafx.domain.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.time.LocalDate;

public class CadastroPacienteController {
    @FXML
    private TextField fieldCPF;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldLogin;
    @FXML
    private TextField fieldPassword;
    @FXML
    private DatePicker fieldDate;
    @FXML
    private TextField fieldPhone;
    @FXML
    private Button buttonReturnCadastro;
    @FXML
    private Button btnFinalizarCadastro;
    @FXML
    private ToggleGroup sexo;
    @FXML
    private Label errorMessage;
    @FXML
    private Label cadastroMessage;

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

    @FXML
    protected Login returnLogin(String password, String login){
        Login loginSenha = new Login();

        loginSenha.setLogin(login);
        loginSenha.setSenha(password);

        return loginSenha;
    }

    @FXML
    protected Paciente returnPaciente(String pacienteName, String pacienteCPF, Login pacienteLoginSenha,
                                  String pacientePhone, String pacienteSexo, LocalDate pacienteDate){
        Paciente paciente = new Paciente();

        paciente.setNome(pacienteName);
        paciente.setCpf(pacienteCPF);
        paciente.setLogin(pacienteLoginSenha);
        paciente.setTelefonePaciente(pacientePhone);
        paciente.setDataNascimento(pacienteDate);
        paciente.setSexo(pacienteSexo);

        return paciente;
    }

    @FXML
    protected void onCasdastrarPaciente(ActionEvent event) throws IOException{
        errorMessage.setText("");
        PacienteDAO pacienteDAO = new PacienteDAO();
        LoginDAO loginDAO = new LoginDAO();

        String pacienteName = fieldName.getText();
        String pacienteCPF = fieldCPF.getText();
        String pacienteLogin = fieldLogin.getText();
        String pacientePassword = fieldPassword.getText();
        String pacientePhone = fieldPhone.getText();
        LocalDate pacienteDate = fieldDate.getValue();
        String pacienteSexo = ((RadioButton)sexo.getSelectedToggle()).getText();

        try {
            Login pacienteLoginSenha = returnLogin(pacientePassword, pacienteLogin);

            loginDAO.inserirLogin(pacienteLoginSenha);

            Paciente paciente = returnPaciente(pacienteName, pacienteCPF, pacienteLoginSenha, pacientePhone, pacienteSexo,
                    pacienteDate);

            pacienteDAO.cadastrarPaciente(paciente);

            cadastroMessage.setText("Cadastrado com sucesso!!!");
            System.out.println("Cadastrado");
        }catch (ConstraintViolationException e){
            System.err.println("Algum valor está duplicado no banco de dados");
            mostrarAlertaDadosJaCadastrados();

        }

}
    private void mostrarAlertaDadosJaCadastrados() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de Cadastro");
        alert.setHeaderText("Dados já cadastrados");
        alert.setContentText("Os dados fornecidos já estão cadastrados no sistema.");
    }
}