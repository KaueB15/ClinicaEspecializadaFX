package br.edu.fescfafic.clicinaespecializadafx.view;

import br.edu.fescfafic.clicinaespecializadafx.dao.LoginDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.PacienteDAO;
import br.edu.fescfafic.clicinaespecializadafx.domain.Login;
import br.edu.fescfafic.clicinaespecializadafx.domain.Paciente;
import br.edu.fescfafic.clicinaespecializadafx.exceptions.FieldNullException;
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
    private PasswordField fieldPassword;
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

        Stage stage = (Stage) buttonReturnCadastro.getScene().getWindow();
        stage.setScene(cadastroScene);
        stage.setTitle("Cadastro");
        stage.show();
    }

    @FXML
    protected Login returnLogin(String password, String login){
        Login loginSenha = new Login();

        loginSenha.setLogin(login);
        loginSenha.setSenha(password);
        loginSenha.setTipo("Paciente");

        return loginSenha;
    }

    @FXML
    protected boolean fieldVerification(){

        boolean returnVerification;

        boolean nameVerification = fieldName.getText() == null;
        boolean cpfVerification = fieldCPF.getText() == null;
        boolean loginVerification = fieldLogin.getText() == null;
        boolean passwordVerification = fieldPassword.getText() == null;
        boolean phoneVerification = fieldPhone.getText() == null;
        boolean dateVerification = fieldDate.getValue() == null;
        boolean sexVerification = sexo.getSelectedToggle() == null;

        if(nameVerification || cpfVerification || loginVerification || passwordVerification ||
                phoneVerification || dateVerification || sexVerification){

            returnVerification = false;
        }else{
            returnVerification = true;
        }

        return returnVerification;
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
    protected void onCasdastrarPaciente(ActionEvent event) throws IOException {

        errorMessage.setText("");
        cadastroMessage.setText("");

        PacienteDAO pacienteDAO = new PacienteDAO();
        LoginDAO loginDAO = new LoginDAO();

        String pacienteName = fieldName.getText();
        String pacienteCPF = fieldCPF.getText();
        String pacienteLogin = fieldLogin.getText();
        String pacientePassword = fieldPassword.getText();
        String pacientePhone = fieldPhone.getText();
        LocalDate pacienteDate = fieldDate.getValue();
        String pacienteSexo = ((RadioButton) sexo.getSelectedToggle()).getText();

        boolean verification = fieldVerification();
        int insertsDataBase = 0;

        try {
            if (verification){
                Login pacienteLoginSenha = returnLogin(pacientePassword, pacienteLogin);

                loginDAO.inserirLogin(pacienteLoginSenha);

                insertsDataBase = 1;

                Paciente paciente = returnPaciente(pacienteName, pacienteCPF, pacienteLoginSenha, pacientePhone, pacienteSexo,
                        pacienteDate);

                pacienteDAO.cadastrarPaciente(paciente);

                insertsDataBase = 10;

                cadastroMessage.setText("Cadastrado com Sucesso!!!");
                System.out.println("Cadastrado");
            }else{
                throw new FieldNullException();
            }

        }catch (ConstraintViolationException e) {
            errorMessage.setText("Dados já Cadastrados!!!");
            System.err.println("Algum valor está duplicado no banco de dados");

            LoginDAO daoLogin = new LoginDAO();

            if (insertsDataBase == 1){
                daoLogin.deletarLogin(pacienteLogin);
            }


        }catch (FieldNullException e){
            errorMessage.setText("Campos Invalidos!!!");
            System.err.println(e);
        }

    }
}
