package br.edu.fescfafic.clicinaespecializadafx.dao;

import br.edu.fescfafic.clicinaespecializadafx.domain.Login;
import br.edu.fescfafic.clicinaespecializadafx.domain.Medico;
import br.edu.fescfafic.clicinaespecializadafx.persistence.EntityManagerConnection;

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
}
