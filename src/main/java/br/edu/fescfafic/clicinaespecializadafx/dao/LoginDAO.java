package br.edu.fescfafic.clicinaespecializadafx.dao;

import br.edu.fescfafic.clicinaespecializadafx.domain.Login;
import br.edu.fescfafic.clicinaespecializadafx.domain.Medico;
import br.edu.fescfafic.clicinaespecializadafx.persistence.EntityManagerConnection;

import java.util.List;

public class LoginDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }

    public void inserirLogin(Login login) {
        // Inicia a transação com o BD
        getEmc().getEntityManager().getTransaction().begin();
        // Realiza a percistencia na tabela
        getEmc().getEntityManager().persist(login);
        // Confirmação da transação
        getEmc().getEntityManager().getTransaction().commit();
    }

    public void validarLogin(String user, String password) {
        List<Login> logins = getAll();
        boolean isValid = false;

        for (Login login : logins) {
            if (login.getLogin().equals(user) && login.getSenha().equals(password)) {
                isValid = true;
                break;
            }
        }

        if (isValid) {
            System.out.println("Login realizado com sucesso!");

        } else {
            System.out.println("Login ou senha inválidos.");

        }
    }

    public List<Login> getAll() {
        getEmc().getEntityManager().getTransaction().begin();
        var query = getEmc().getEntityManager().createNamedQuery("listarLogins", Login.class);
        return query.getResultList();

    }
}

