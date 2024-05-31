package br.edu.fescfafic.clicinaespecializadafx.view;

import br.edu.fescfafic.clicinaespecializadafx.dao.LoginDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.MedicoDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.PacienteDAO;
import br.edu.fescfafic.clicinaespecializadafx.domain.Login;
import br.edu.fescfafic.clicinaespecializadafx.domain.Medico;
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
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.time.LocalDate;

    public class CadastroMedicoController {
        @FXML
        private TextField fieldCPF;
        @FXML
        private TextField fieldName;
        @FXML
        private TextField fieldLogin;
        @FXML
        private PasswordField fieldPassword;
        @FXML
        private TextField fieldEspecialidade;
        @FXML
        private TextField fieldCRM;
        @FXML
        private Button buttonReturnCadastro;
        @FXML
        private Button btnFinalizarCadastro;
        @FXML
        private Label cadastroMessage;
        @FXML
        private Label errorMessage;
        @FXML
        private Button btnCadastrarMedico;

        @FXML
        protected void onVoltarButtonClick(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/cadastro.fxml"));
            Parent cadastroRoot = fxmlLoader.load();
            Stage stage = (Stage) btnFinalizarCadastro.getScene().getWindow();
            Pane mainPane = (Pane) stage.getScene().getRoot();
            mainPane.getChildren().clear();
            mainPane.getChildren().add(cadastroRoot);
        }

        @FXML
        protected Login returnLogin(String password, String login){
            Login loginSenha = new Login();

            loginSenha.setLogin(login);
            loginSenha.setSenha(password);
            loginSenha.setTipo("Medico");

            return loginSenha;
        }

        @FXML
        protected boolean fieldVerification(){

            boolean returnVerification;

            boolean nameVerification = fieldName.getText() == null;
            boolean cpfVerification = fieldCPF.getText() == null;
            boolean loginVerification = fieldLogin.getText() == null;
            boolean passwordVerification = fieldPassword.getText() == null;
            boolean especialidadeVerification = fieldEspecialidade.getText() == null;
            boolean crmVerification = fieldCRM.getText() == null;

            if(nameVerification || cpfVerification || loginVerification || passwordVerification ||
                   especialidadeVerification || crmVerification){

                returnVerification = false;
            }else{
                returnVerification = true;
            }

            return returnVerification;
        }

        @FXML
        protected Medico returnMedico(String medicoName, String medicoCPF, Login medicoLoginSenha,
                                          String medicoCRM, String medicoEspecialidade){
            Medico medico = new Medico();

            medico.setNome(medicoName);
            medico.setCpf(medicoCPF);
            medico.setLogin(medicoLoginSenha);
            medico.setCrm(Integer.parseInt(medicoCRM));
            medico.setEspecialidade(medicoEspecialidade);

            return medico;
        }

        @FXML
        protected void onCadastrarMedico(ActionEvent event) throws IOException{

            errorMessage.setText(" ");
            cadastroMessage.setText(" ");

            MedicoDAO medicoDAO = new MedicoDAO();
            LoginDAO loginDAO = new LoginDAO();

            String medicoName = fieldName.getText();
            String medicoCPF = fieldCPF.getText();
            String medicoLogin = fieldLogin.getText();
            String medicoPassword = fieldPassword.getText();
            String medicoCRM = fieldCRM.getText();
            String medicoEspecialidade = fieldEspecialidade.getText();

            boolean verificationField = fieldVerification();
            int insertsDataBase = 0;

            try {
                if (verificationField){
                    Login medicoLoginSenha = returnLogin(medicoPassword, medicoLogin);

                    loginDAO.inserirLogin(medicoLoginSenha);

                    insertsDataBase = 1;

                    Medico medico = returnMedico(medicoName, medicoCPF, medicoLoginSenha, medicoCRM, medicoEspecialidade);

                    medicoDAO.cadastrarMedico(medico);

                    insertsDataBase = 10;

                    cadastroMessage.setText("Cadastrado com Sucesso!!!");
                    System.out.println("Cadastrado");
                }else {
                    throw new FieldNullException();
                }
            }catch (ConstraintViolationException e) {
                errorMessage.setText("Dados já Cadastrados!!!");
                System.err.println("Algum valor está duplicado no banco de dados");

                LoginDAO daoLogin = new LoginDAO();

                if (insertsDataBase == 1){
                    daoLogin.deletarLogin(medicoLogin);
                }


            }catch (FieldNullException e){
                errorMessage.setText("Campos Invalidos!!!");
                System.err.println(e);
                LoginDAO daoLogin = new LoginDAO();

                if (insertsDataBase == 1){
                    daoLogin.deletarLogin(medicoLogin);
                }
            }catch (NumberFormatException e){
                errorMessage.setText("Campos Invalidos!!!");
                System.err.println(e);
                LoginDAO daoLogin = new LoginDAO();

                if (insertsDataBase == 1){
                    daoLogin.deletarLogin(medicoLogin);
                }
            }


        }
    }

