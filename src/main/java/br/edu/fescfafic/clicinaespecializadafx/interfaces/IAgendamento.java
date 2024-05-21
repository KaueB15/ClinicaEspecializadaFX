package br.edu.fescfafic.clicinaespecializadafx.interfaces;

import br.edu.fescfafic.clicinaespecializadafx.domain.Medico;
import br.edu.fescfafic.clicinaespecializadafx.domain.Paciente;

import java.time.LocalDateTime;

public interface IAgendamento {
    int getId();
    LocalDateTime getDataHora();
    Medico getIdMedico();
    Paciente getIdPaciente();
    void setId(int idAgendamento);
    void setDataHora(LocalDateTime dataHora);
    void setIdMedico(Medico idMedico);
    void setIdPaciente(Paciente idPaciente);
}
