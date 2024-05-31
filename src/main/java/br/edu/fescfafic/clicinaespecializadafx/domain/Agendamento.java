package br.edu.fescfafic.clicinaespecializadafx.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "listarTodos", query = "select dh from Agendamento dh"),
        @NamedQuery(name = "buscarPorMedico", query = "select m from Medico m"),
        @NamedQuery(name = "buscarPorPaciente", query = "select p from Paciente p"),
        @NamedQuery(name = "verificarDisponibilidade", query = "select hm from Agendamento hm where hm.dataHoraInicio=:dataHora"),
        @NamedQuery(name = "valida.agendamento", query = "SELECT a FROM Agendamento a WHERE a.medico.id = :medico AND" +
                " (:inicioAtendimento BETWEEN a.dataHoraInicio AND a.dataHoraFim) OR" +
                " (:fimAtendimento BETWEEN a.dataHoraInicio AND a.dataHoraFim)")
})
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private LocalDateTime dataHoraInicio;

    @Column(unique = true)
    private LocalDateTime dataHoraFim;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Paciente paciente;

    public String getMedicoNome() {
        return medico != null ? medico.getNome() : "Desconhecido";
    }

    public String getMedicoEspecialidade() {
        return medico != null ? medico.getEspecialidade() : "Desconhecido";
    }

    public String getData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataHoraInicio != null ? dataHoraInicio.toLocalDate().format(formatter) : "";
    }

    public String getHora() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return dataHoraInicio != null ? dataHoraInicio.toLocalTime().format(formatter) : "";
    }
}
