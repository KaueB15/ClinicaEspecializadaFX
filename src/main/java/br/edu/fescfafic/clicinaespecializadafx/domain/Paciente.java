package br.edu.fescfafic.clicinaespecializadafx.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
