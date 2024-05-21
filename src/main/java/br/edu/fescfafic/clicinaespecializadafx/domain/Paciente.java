package br.edu.fescfafic.clicinaespecializadafx.domain;

import br.edu.fescfafic.clicinaespecializadafx.interfaces.IPaciente;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome, sexo;
    private LocalDate dataNascimento;
    @OneToOne
    private Login login;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String telefonePaciente;
}