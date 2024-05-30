package br.edu.fescfafic.clicinaespecializadafx.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "listarTodos", query = "select dh from Agendamento dh "),
        @NamedQuery(name = "buscarPorMedico", query = "select m from Medico m"),
        @NamedQuery(name = "buscarPorPaciente", query = "select p from Paciente p"),
        @NamedQuery(name = "verificarDisponibilidade", query = "select hm from Agendamento hm where hm.dataHoraInicio=:dataHora"),

        @NamedQuery(name = "valida.agendamento", query = "SELECT a FROM Agendamento a WHERE a.medico.id = :medico AND" +
                " (:inicioAtendimento BETWEEN a.dataHoraInicio AND a.dataHoraFim) OR" +
                " (:fimAtendimento BETWEEN a.dataHoraInicio AND a.dataHoraFim)"
        )

})
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private LocalDateTime dataHoraInicio;
    @Column(unique = true)
    private LocalDateTime dataHoraFim;

    @OneToOne
    private Medico medico;
    @OneToOne
    private Paciente paciente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
