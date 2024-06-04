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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    public void initialize() {
        // Configurar o DatePicker para aceitar entrada de texto no formato "dd/MM/yyyy"
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        fieldDate.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    try {
                        return LocalDate.parse(string, dateFormatter);
                    } catch (DateTimeParseException e) {
                        errorMessage.setText("Formato de data inválido. Use dd/MM/yyyy.");
                        return null;
                    }
                } else {
                    return null;
                }
            }
        });
        // Adiciona um listener para tratar a entrada manual de data
        fieldDate.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            // Verifique se o novo valor não está vazio
            if (newValue != null && !newValue.isEmpty()) {
                try {
                    // Tente fazer o parse da data
                    LocalDate parsedDate = LocalDate.parse(newValue, dateFormatter);
                    // Se o parse der certo, define a nova data no DatePicker
                    fieldDate.setValue(parsedDate);
                } catch (DateTimeParseException e) {
                    // Se o parse falhar, imprime uma mensagem de erro
                    System.err.println("Erro ao fazer o parse da data: " + e.getMessage());
                }
            }
        });
    }


    @FXML
    protected void onVoltarButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/cadastro.fxml"));
        Parent cadastroRoot = fxmlLoader.load();
        Scene cadastroScene = new Scene(cadastroRoot);

        Stage stage = (Stage) buttonReturnCadastro.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(cadastroRoot);
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
        boolean nameVerification = fieldName.getText() == null || fieldName.getText().isEmpty();
        boolean cpfVerification = fieldCPF.getText() == null || fieldCPF.getText().isEmpty();
        boolean loginVerification = fieldLogin.getText() == null || fieldLogin.getText().isEmpty();
        boolean passwordVerification = fieldPassword.getText() == null || fieldPassword.getText().isEmpty();
        boolean phoneVerification = fieldPhone.getText() == null || fieldPhone.getText().isEmpty();
        boolean dateVerification = fieldDate.getValue() == null;
        boolean sexVerification = sexo.getSelectedToggle() == null;

        return !(nameVerification || cpfVerification || loginVerification || passwordVerification ||
                phoneVerification || dateVerification || sexVerification);
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
            if (verification) {
                System.out.println("Data informada: " + pacienteDate); // Log da data informada
                Login pacienteLoginSenha = returnLogin(pacientePassword, pacienteLogin);
                loginDAO.inserirLogin(pacienteLoginSenha);
                insertsDataBase = 1;

                Paciente paciente = returnPaciente(pacienteName, pacienteCPF, pacienteLoginSenha, pacientePhone, pacienteSexo, pacienteDate);
                pacienteDAO.cadastrarPaciente(paciente);
                insertsDataBase = 10;

                cadastroMessage.setText("Cadastrado com Sucesso!!!");
                System.out.println("Cadastrado");
            } else {
                throw new FieldNullException();
            }
        } catch (ConstraintViolationException e) {
            errorMessage.setText("Dados já Cadastrados!!!");
            System.err.println("Algum valor está duplicado no banco de dados");

            if (insertsDataBase == 1) {
                loginDAO.deletarLogin(pacienteLogin);
            }
        } catch (FieldNullException e) {
            errorMessage.setText("Campos Invalidos!!!");
            System.err.println(e);
        } catch (Exception e) {
            errorMessage.setText("Erro ao cadastrar o paciente.");
            System.err.println(e);
        }
    }
}
