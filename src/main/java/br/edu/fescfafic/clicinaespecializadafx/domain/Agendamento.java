package br.edu.fescfafic.clicinaespecializadafx.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private LocalDateTime dataHora;
    @OneToOne
    private Medico idMedico;
    @OneToOne
    private Paciente idPaciente;

    public Agendamento() {
    }

    public Agendamento(int id, LocalDateTime dataHora, Medico idMedico, Paciente idPaciente) {
        this.id = id;
        this.dataHora = dataHora;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Medico getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Medico idMedico) {
        this.idMedico = idMedico;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
    }
}
