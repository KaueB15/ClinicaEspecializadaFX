package br.edu.fescfafic.clicinaespecializadafx.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private int crm;
    private String nome, especialidade;
    @OneToOne
    private Login login;
    @Column(unique = true)
    private String cpf;
}
