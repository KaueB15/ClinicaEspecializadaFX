package br.edu.fescfafic.clicinaespecializadafx.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@NamedQueries({
        @NamedQuery(name = "listarTodos", query = "select dh from Agendamento dh "),
        @NamedQuery(name = "buscarPorMedico", query = "select m from Medico m"),
        @NamedQuery(name = "buscarPorPaciente", query = "select p from Paciente p"),
        @NamedQuery(name = "verificarDisponibilidade", query = "select hm from Agendamento hm where hm.dataHoraInicio=:dataHora"),
        @NamedQuery(name="valida.agendamento",query="select a from Agendamento a where a.dataHoraInicio>= :dataHoraInicio and a.dataHoraFim<= :dataHoraFim and a.idMedico.id= :idMedico")

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
    private Medico idMedico;
    @OneToOne
    private Paciente idPaciente;
}
