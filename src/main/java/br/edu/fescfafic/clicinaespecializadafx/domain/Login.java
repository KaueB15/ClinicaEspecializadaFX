package br.edu.fescfafic.clicinaespecializadafx.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String login;
    private String senha;

    public Login() {
    }

    public Login(int id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
