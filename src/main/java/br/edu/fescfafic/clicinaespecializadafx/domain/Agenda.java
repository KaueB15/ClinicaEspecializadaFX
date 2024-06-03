package br.edu.fescfafic.clicinaespecializadafx.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NamedQueries({
        @NamedQuery(name = "listarTodasAgendas", query = "select dh from Agenda dh"),
})
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime dataConsulta;
    private LocalTime hora;
    private LocalDate dataNascimento;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Medico medico;
    private String sexo;
    private int idade;

    public String getNomePacienteNome() {
        return paciente != null ? paciente.getNome() : "Desconhecido";
    }

    public String getPacienteSexo() {
        return paciente != null ? paciente.getSexo() : "Desconhecido";
    }

    public String getData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataConsulta != null ? dataConsulta.toLocalDate().format(formatter) : "";
    }

    public String getHora() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return dataConsulta != null ? dataConsulta.toLocalTime().format(formatter) : "";
    }

    @Transient
    public int getIdade() {
        if (dataNascimento != null) {
            LocalDate today = LocalDate.now();
            return Period.between(dataNascimento, today).getYears();
        } else {
            return 0;
        }
    }
}