package br.edu.fescfafic.clicinaespecializadafx.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@NamedQueries({
        @NamedQuery(name = "pacientes.getAll", query = "select p from Paciente p"),
        @NamedQuery(name = "pacientes.getByCpf", query = "select p from Paciente p where p.cpf=:cpf")
})
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @OneToOne(cascade = CascadeType.ALL)
    private Login login;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String telefonePaciente;
}