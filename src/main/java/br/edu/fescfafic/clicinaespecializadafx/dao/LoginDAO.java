package br.edu.fescfafic.clicinaespecializadafx.dao;

import br.edu.fescfafic.clicinaespecializadafx.domain.Login;
import br.edu.fescfafic.clicinaespecializadafx.domain.Medico;
import br.edu.fescfafic.clicinaespecializadafx.domain.Paciente;
import br.edu.fescfafic.clicinaespecializadafx.persistence.EntityManagerConnection;

import java.util.List;

public class LoginDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }

    public void inserirLogin(Login login) {
        try {
            getEmc().getEntityManager().getTransaction().begin();
            // Realiza a percistencia na tabela
            getEmc().getEntityManager().persist(login);
            // Confirmação da transação
            getEmc().getEntityManager().getTransaction().commit();

        }finally {
            getEmc().getEntityManager().close();
        }
    }

    public List<Login> getAll() {
        getEmc().getEntityManager().getTransaction().begin();
        var query = getEmc().getEntityManager().createNamedQuery("listarLogins", Login.class);
        return query.getResultList();

    }

    public void deletarLogin(String string) {
        getEmc().getEntityManager().getTransaction().begin();
        var query = getEmc().getEntityManager().createNamedQuery("listarLogins", Login.class);

        List<Login> logins = query.getResultList();

        for (Login login : logins){
            if(login.getLogin().equals(string)){
                getEmc().getEntityManager().remove(login);
                getEmc().getEntityManager().getTransaction().commit();
                getEmc().getEntityManager().close();
            }
        }
    }

    public void atualizarLogin(Login login) {
        // Inicia a transação com o BD
        getEmc().getEntityManager().getTransaction().begin();
        // Realiza a percistencia na tabela
        getEmc().getEntityManager().merge(login);
        // Confirmação da transação
        getEmc().getEntityManager().getTransaction().commit();
        getEmc().getEntityManager().close();
    }
}


