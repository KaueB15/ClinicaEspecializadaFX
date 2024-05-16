package br.edu.fescfafic.clicinaespecializadafx.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Login {
    @Id
    private String login;
    private String senha;
}
