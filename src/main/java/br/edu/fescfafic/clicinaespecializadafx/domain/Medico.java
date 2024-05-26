package br.edu.fescfafic.clicinaespecializadafx.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "medicos.getAll", query = "select m from Medico m"),
        @NamedQuery(name = "medicos.getByCrm", query = "select m from Medico m where m.crm=:crm"),
        @NamedQuery(name = "medicos.getByEspecialidade", query = "select m from Medico m where m.especialidade=:especialidade")})
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private int crm;
    private String nome, especialidade;
    @OneToOne(cascade = CascadeType.ALL)
    private Login login;
    @Column(unique = true)
    private String cpf;


}
