package br.edu.fescfafic.clicinaespecializadafx.domain;

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

    public Paciente() {
    }

    public Paciente(int id, String nome, String sexo, LocalDate dataNascimento, Login login, String cpf, String telefonePaciente) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.login = login;
        this.cpf = cpf;
        this.telefonePaciente = telefonePaciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public String getTelefonePaciente() {
        return telefonePaciente;
    }

    public void setTelefonePaciente(String telefonePaciente) {
        this.telefonePaciente = telefonePaciente;
    }
}