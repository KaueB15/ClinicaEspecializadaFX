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

    public Medico() {
    }

    public Medico(int id, int crm, String nome, String especialidade, Login login, String cpf) {
        this.id = id;
        this.crm = crm;
        this.nome = nome;
        this.especialidade = especialidade;
        this.login = login;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
