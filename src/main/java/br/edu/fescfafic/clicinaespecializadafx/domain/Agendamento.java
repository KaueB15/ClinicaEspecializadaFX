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
}
