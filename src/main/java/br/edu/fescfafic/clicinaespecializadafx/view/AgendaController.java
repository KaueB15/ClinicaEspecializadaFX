package br.edu.fescfafic.clicinaespecializadafx.view;

import br.edu.fescfafic.clicinaespecializadafx.domain.Agenda;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AgendaController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<Agenda> tableView;

    @FXML
    private TableColumn<Agenda, String> tablePaciente;
    @FXML
    private TableColumn<Agenda, String> tableSexo;
    @FXML
    private TableColumn<Agenda, Integer> tableIdade;
    @FXML
    private TableColumn<Agenda, String> tableHora;

}