package br.edu.fescfafic.clicinaespecializadafx.view;

import br.edu.fescfafic.clicinaespecializadafx.dao.MedicoDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.PacienteDAO;
import br.edu.fescfafic.clicinaespecializadafx.domain.Login;
import br.edu.fescfafic.clicinaespecializadafx.domain.Medico;
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

    public class CadastroMedicoController {
        @FXML
        private TextField fieldCPF;
        @FXML
        private TextField fieldName;
        @FXML
        private TextField fieldLogin;
        @FXML
        private TextField fieldPassword;
        @FXML
        private TextField fieldEspecialidade;
        @FXML
        private TextField fieldCRM;
        @FXML
        private Button buttonReturnCadastro;
        @FXML
        private Button btnFinalizarCadastro;

        @FXML
        private Button btnCadastrarMedico;

        @FXML
        protected void onVoltarButtonClick(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/fescfafic/clicinaespecializadafx/cadastro.fxml"));
            Parent cadastroRoot = fxmlLoader.load();
            Scene cadastroScene = new Scene(cadastroRoot);

            Stage stage = (Stage) btnFinalizarCadastro.getScene().getWindow();
            stage.setScene(cadastroScene);
            stage.setTitle("Cadastro Medico");
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
        protected void onCadastrarMedico(ActionEvent event) throws IOException{
            String medicoName = fieldName.getText();
            String medicoCPF = fieldCPF.getText();
            String medicoLogin = fieldLogin.getText();
            String medicoPassword = fieldPassword.getText();
            String medicoCRM = fieldCRM.getText();
            String medicoEspecialidade = fieldEspecialidade.getText();


            Login medicoLoginSenha = returnLogin(medicoPassword, medicoLogin);

            Medico medico = new Medico();

            medico.setNome(medicoName);
            medico.setCpf(medicoCPF);
            medico.setLogin(medicoLoginSenha);
            medico.setCrm(Integer.parseInt(medicoCRM));
            medico.setEspecialidade(medicoEspecialidade);

            MedicoDAO medicoDAO = new MedicoDAO();

            medicoDAO.cadastrarMedico(medico);
        }
    }

