package br.edu.fescfafic.clicinaespecializadafx.view;

import br.edu.fescfafic.clicinaespecializadafx.dao.PacienteDAO;
import br.edu.fescfafic.clicinaespecializadafx.domain.Login;
import br.edu.fescfafic.clicinaespecializadafx.domain.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    protected void onCasdastrarPaciente(ActionEvent event) throws IOException{
        String pacienteName = fieldName.getText();
        String pacienteCPF = fieldCPF.getText();
        String pacienteLogin = fieldLogin.getText();
        String pacientePassword = fieldPassword.getText();
        String pacientePhone = fieldPhone.getText();
        LocalDate pacienteDate = fieldDate.getValue();


        Login pacienteLoginSenha = returnLogin(pacientePassword, pacienteLogin);

        Paciente paciente = new Paciente();

        paciente.setNome(pacienteName);
        paciente.setCpf(pacienteCPF);
        paciente.setLogin(pacienteLoginSenha);
        paciente.setTelefonePaciente(pacientePhone);
        paciente.setDataNascimento(pacienteDate);

        PacienteDAO pacienteDAO = new PacienteDAO();

        pacienteDAO.cadastrarPaciente(paciente);
    }
}
