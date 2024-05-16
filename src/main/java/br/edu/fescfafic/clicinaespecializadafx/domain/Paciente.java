package br.edu.fescfafic.clicinaespecializadafx.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @OneToOne
    private Login login;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String telefonePaciente;
}